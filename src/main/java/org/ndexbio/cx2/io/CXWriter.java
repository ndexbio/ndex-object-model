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

import org.ndexbio.cx2.aspect.element.core.CxAspectElement;
import org.ndexbio.cx2.aspect.element.core.CxMetadata;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CXWriter {

	private static final int  INIT = 0;
	private static final int  BEFORE_ASPECT = 1;
	private static final int  ASPECT_STARTED = 2;
	private static final int  POST_METATDATA = 3;
	private static final int  FINISHED = 4;
		
	private static Charset charset = Charset.forName("UTF-8");
	private static 	byte[] elmtDivider = ",\n".getBytes(charset);

	
	private OutputStream out;
	private String currentAspectName;
	
	
	private int state;
	
	private boolean hasFragments;
	
	private ObjectMapper om;
	
	private int metadataCount ;
	
	private int currentElmtCounter; // counter for the current aspect fragment.
	
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
		out.flush();
		metadataCount ++;
		
		// check if we this is the post metadata.
		if (metadataCount == 2 || ( metadataCount == 1 && currentAspectName != null)) 
			state = POST_METATDATA;
	}
	
	public void writeFullAspectFragment(List<? extends CxAspectElement> fragment) throws JsonGenerationException, JsonMappingException, IOException, NdexException {
		if ( state == INIT)
			init();
		
		if ( fragment.size() > 0 ) {
			Map<String,List<? extends CxAspectElement>> holder = new HashMap<>(1);
			holder.put( fragment.get(0).getAspectName(), fragment);
			//writeStr("{\"" + fragment.get(0).getAspectName() + "\":");
			om.writeValue(out, holder);
			//writeStr("}");
			out.write(elmtDivider);
			out.flush();
		}
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
		writeStr("{\"" + aspectName + "\":[");

		Path p = Paths.get(aspectElementArrayFilePath);
		Files.copy(p, out);		
		
		writeStr("]}");
		out.write(elmtDivider);
		out.flush();
	}
	
	public void writeElementInFragment(CxAspectElement element) throws NdexException, JsonGenerationException, JsonMappingException, IOException {
		String aspect = element.getAspectName();
		if ( state != ASPECT_STARTED)
			throw new NdexException( "Aspect frament for " + aspect + " hasn't been started yet.");
	
		if ( currentElmtCounter != 0)
			out.write(elmtDivider);
		
		om.writeValue(out, element);
		
		currentElmtCounter++;
	}
	
	
}
