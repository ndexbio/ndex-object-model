package org.ndexbio.cx2.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.ndexbio.cx2.aspect.element.core.CxAspectElement;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CX2AspectWriter<T extends CxAspectElement<?>> implements AutoCloseable {

	private OutputStream out;
	private ObjectMapper om;
	private long count;
	private boolean isClosed;
	
	private static final byte[] start = {'['};
	private static final byte[] comma = {','};
	private static final byte[] end = {']'};
	
	@SuppressWarnings("resource")
	public CX2AspectWriter(String aspectFileName) throws IOException {
		this(new FileOutputStream(aspectFileName,false));
	}

	public CX2AspectWriter(OutputStream output) {
		out = output;
		JsonFactory factory = new JsonFactory();
		factory.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
		om = new ObjectMapper(factory);
		count = 0;
		isClosed=false;
	}


	@Override
	public void close () throws IOException {
		if ( !isClosed) { 
			out.write(end);
			out.close();
			isClosed = true;
		}
	}


	
	public void writeCXElement(T e) throws IOException {
		if ( count == 0 ) 
			out.write(start);
		else 
			out.write(comma);
		om.writeValue(out, e);
		count++;
	}
	
	//public void flush() throws IOException { out.flush();}
	
	
	public long getElementCount() {return count;}

}
