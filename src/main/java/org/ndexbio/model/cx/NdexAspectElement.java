package org.ndexbio.model.cx;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.AbstractAspectElement;
import org.ndexbio.cxio.util.JsonWriter;

public abstract class NdexAspectElement extends AbstractAspectElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2575730498443013089L;

	@Override
	public void write(JsonWriter out) throws IOException {
		out.writeObject(this);
		out.flush();
	}
	
}
