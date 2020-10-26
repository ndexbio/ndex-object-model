package org.ndexbio.cxio.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Encapsulate the output stream, writer, JsonWriters that we need to write
 * individual aspects into a file.
 * @author chenjing
 *
 */
public class CXAspectWriter implements AutoCloseable{
	
	private OutputStream out;
	private JsonWriter jwriter;
	private long count;
	private boolean isClosed;
	
	private static final byte[] start = {'['};
	private static final byte[] comma = {','};
	private static final byte[] end = {']'};
	
	@SuppressWarnings("resource")
	public CXAspectWriter(String aspectFileName) throws IOException {
		this(new FileOutputStream(aspectFileName,false));
	}

	public CXAspectWriter(OutputStream output) throws IOException {
		out = output;
		jwriter = JsonWriter.createInstance(out,false);
	    jwriter.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
	    jwriter.configure(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM, false);
		count = 0;
		isClosed=false;
		out.write(start);
	}


	@Override
	public void close () throws IOException {
		if ( !isClosed) { 
			out.write(end);
			jwriter.close();
			out.close();
			isClosed = true;
		}
	}


	
	public void writeCXElement(AspectElement e) throws IOException {
		if ( count != 0 ) 
			out.write(comma);
		e.write(jwriter);
		count++;
	}
	
	public void flush() throws IOException { out.flush();}
	
	
	public long getElementCount() {return count;}

}
