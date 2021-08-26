package org.ndexbio.cxio.aspects.datamodels;

import java.io.IOException;

import org.ndexbio.cx2.aspect.element.cytoscape.AbstractTableVisualProperty;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

public class CyTableVisualPropertiesElement extends AbstractTableVisualProperty implements AspectElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(AspectElement o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void write(JsonWriter out) throws IOException {
		out.writeObject(this);
		out.flush();
	}

}
