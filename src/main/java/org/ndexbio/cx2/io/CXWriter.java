package org.ndexbio.cx2.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.ndexbio.cx2.aspect.element.core.CxAspectElement;
import org.ndexbio.cx2.aspect.element.core.CxMetadata;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * This Class is not thread safe. User should not use the same writer in different threads.
 */
public class CXWriter {

	private static final int  INIT = 0;
	private static final int  BEFORE_ASPECT = 1;
	private static final int  ASPECT_STARTED = 2;
	private static final int  POST_METATDATA = 3;
	private static final int  FINISHED = 4;
		
	private static Charset charset = Charset.forName("UTF-8");
	private static 	byte[] elmtDivider = ",".getBytes(charset);
	private static 	byte[] newline = "\n".getBytes(charset);

	
	private OutputStream out;
	private String currentAspectName;
	
	
	private int state;
	
	private boolean hasFragments;
	
	// tracking aspects that has been written in this writer.
	private Set<String> finishedAspects;
	
	private ObjectMapper om;
	
	private int metadataCount ;
	
	private int currentElmtCounter; // counter for the current aspect fragment.
	
	/**
	 * Writer class for writing a network to an output stream in CX2 format. 
	 * Typical sequence of writing CX2 data using this class is:
	 *   w = new CXWriter(s,true);
	 *   w.writMetadata(metadata);
	 *   startAspectFragment(AttributeDelcaration.ASPECT_NAME)
	 *   writeElementInFragment(element1);
	 *   endAspectFragment();
	 *   startAspectFragment(CxNode.ASPECT_NAME);
	 *   writeElementInFragment(node1);
	 *   writeElementInFragment(node2);
	 *   ...
	 *   endAspectFragment();
	 *   ...
	 *   finish();
	 *   
	 * @param s
	 * @param hasFragments
	 */
	public CXWriter (OutputStream s, boolean hasFragments) {
		this.out = s;
		
		currentAspectName = null;
		state = INIT;
		this.hasFragments = hasFragments;
		metadataCount = 0;
		JsonFactory factory = new JsonFactory();
		factory.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
		om = new ObjectMapper(factory);
	}
	
	
	private void writeStr (String s) throws IOException {
		out.write(s.getBytes(charset));
	}
	
	private void init() throws NdexException, IOException {
		if (state != INIT) 
			throw new NdexException ("Writer state is not INIT.");
		
		out.write(("[{\"CXVersion\":\"2.0\",\"hasFragments\":"+ hasFragments + "},").getBytes(charset));
		out.flush();
		finishedAspects = new TreeSet<>();
		
		state = BEFORE_ASPECT;
	}
	
	public void writeMetadata(List<CxMetadata> metadata) throws NdexException, IOException {
		if( state == INIT )
			init();
		if ( metadataCount >=2)
			throw new NdexException ("To many metadata fragements");

		if ( state != BEFORE_ASPECT)
			throw new NdexException ("Writing metadata is not allowed in current state.");

		writeStr("{\"" + CXReader.metadataAspectName + "\":");
		om.writeValue(out, metadata);
		writeStr("}");
		out.write(elmtDivider);
		out.write(newline);
		out.flush();
		metadataCount ++;
		
		// check if we this is the post metadata.
		if (metadataCount == 2 || ( metadataCount == 1 && currentAspectName != null)) 
			state = POST_METATDATA;
	}
	
	public void writeFullAspectFragment(List<? extends CxAspectElement<?>> fragment) throws JsonGenerationException, JsonMappingException, IOException, NdexException {
		if ( state == INIT)
			init();
		
		if ( fragment.isEmpty()) return;
		
		String aspectName = fragment.get(0).getAspectName();
		checkAspectName(aspectName);
		if ( fragment.size() > 0 ) {
			Map<String,List<? extends CxAspectElement<?>>> holder = new HashMap<>(1);
			holder.put( aspectName, fragment);
			//writeStr("{\"" + fragment.get(0).getAspectName() + "\":");
			om.writeValue(out, holder);
			//writeStr("}");
			out.write(elmtDivider);
			out.write(newline);
			out.flush();
		}
	}

	
	private void checkAspectName(String aspectName) throws NdexException {
		if ( !hasFragments && finishedAspects.contains(aspectName)) 
			throw new NdexException("Writing more than one " + aspectName + " aspect fragment is not allowed in no-fragment mode.");
	}
	
	/**
	 * Wrap up the CX data. Note: the output stream is NOT closed by this function. 
	 * @throws IOException
	 * @throws NdexException
	 */
	public void finish() throws IOException, NdexException {
		if ( state == FINISHED) 
			throw new NdexException("CX stream is already finished.");
		
		if  (state != BEFORE_ASPECT && state != POST_METATDATA)
			throw new NdexException ("Can't finish a CX document in current state.");
		
		state = FINISHED;
		writeStr("{\"status\":[{\"success\":true}]}]");
		out.flush();
	}
	
	/**
	 * Print the error message in the output stream and stop the writer. No more data can be written to the stream after calling this function.
	 * @param errorMsg
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws NdexException
	 */
	public void printError(String errorMsg) throws JsonGenerationException, JsonMappingException, IOException, NdexException {
		if ( state == FINISHED) 
			throw new NdexException("CX stream is already finished.");
		
		Map<String,Object> v = new HashMap<>();
		state = FINISHED;
		v.put("success", Boolean.FALSE);
		v.put("error", errorMsg);
		om.writeValue(out, v);
		writeStr("]");
		out.flush();
		
	}
	
	/**
	 * Write the aspect name, the array is not started yet.
	 * 
	 * @param aspectName
	 * @throws IOException
	 * @throws NdexException
	 */
	public void startAspectFragment ( String aspectName) 
			throws IOException, NdexException {
		currentAspectName = aspectName;
		
		if ( state == INIT)
			init();
		
		checkAspectName(aspectName);
		
		if ( state != BEFORE_ASPECT ) 
			throw new NdexException ("Starting new aspect fragment for " + currentAspectName + " is not allowed here.");
				
		if ( currentAspectName.indexOf('"')!= -1)
			throw new NdexException ("Having '\"' in aspect name is not allowed.");
		writeStr("{\"" + currentAspectName + "\":[");
		state = ASPECT_STARTED;
		currentElmtCounter = 0;
	}
	
	public void endAspectFragment () throws NdexException, IOException {
		
		if ( state != ASPECT_STARTED) 
			throw new NdexException ("Ending aspect fragment is not allowed in current state.");
		
		writeStr("]}");
		out.write(elmtDivider);
		out.write(newline);
		out.flush();
		
		state = BEFORE_ASPECT;
	}
	
	/**
	 * Write the aspect from a file. The File object should point to a file that is a json array of aspect elements.
	 * @param aspectName
	 * @param aspectElementArrayFile
	 * @throws IOException 
	 * @throws NdexException 
	 */
	public void writeAspectFromAspectFile (String aspectName, String aspectElementArrayFilePath) throws NdexException, IOException {
		
		if ( state == INIT)
			init();
		
		if ( state != BEFORE_ASPECT ) 
			throw new NdexException ("Starting new aspect fragment for " + aspectName + " is not allowed here.");
				
		if ( aspectName.indexOf('"')!= -1)
			throw new NdexException ("Having '\"' in aspect name is not allowed.");
		writeStr("{\"" + aspectName + "\":");

		Path p = Paths.get(aspectElementArrayFilePath);
		Files.copy(p, out);		
		
		writeStr("}");
		out.write(elmtDivider);
		out.flush();
	}
	
	
	/**
	 * 
	 * @param aspectName
	 * @param JSONString  A String that was serialized from a list of objects, which is a serialized version of an aspect fragment
	 * @throws NdexException
	 * @throws IOException
	 */
	public void writeAspectFromJSONString (String aspectName, String JSONString) throws NdexException, IOException {
		
		if ( state == INIT)
			init();
		
		if ( state != BEFORE_ASPECT ) 
			throw new NdexException ("Starting new aspect fragment for " + aspectName + " is not allowed here.");
				
		if ( aspectName.indexOf('"')!= -1)
			throw new NdexException ("Having '\"' in aspect name is not allowed.");
		writeStr("{\"" + aspectName + "\":");

		writeStr(JSONString);
		
		writeStr("}");
		out.write(elmtDivider);
		out.flush();
	}
	
	public void writeElementInFragment(CxAspectElement<?> element) throws NdexException, JsonGenerationException, JsonMappingException, IOException {
		String aspect = element.getAspectName();
		if ( state != ASPECT_STARTED)
			throw new NdexException( "Aspect frament for " + aspect + " hasn't been started yet.");
	
		if ( currentElmtCounter != 0)
			out.write(elmtDivider);
		
		om.writeValue(out, element);
		
		currentElmtCounter++;
		if ( currentElmtCounter % 500 == 0)
			out.write(newline);
	}

	public void writeCx1ElementInFragment(AspectElement element) throws NdexException, JsonGenerationException, JsonMappingException, IOException {
		String aspect = element.getAspectName();
		if ( state != ASPECT_STARTED)
			throw new NdexException( "Aspect frament for " + aspect + " hasn't been started yet.");
	
		if ( currentElmtCounter != 0)
			out.write(elmtDivider);
		
		om.writeValue(out, element);
		
		currentElmtCounter++;
		if ( currentElmtCounter % 500 == 0)
			out.write(newline);
	}
	

	
}
