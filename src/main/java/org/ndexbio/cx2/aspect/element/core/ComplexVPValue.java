package org.ndexbio.cx2.aspect.element.core;

import org.ndexbio.model.exceptions.NdexException;

/* It's value can be sererialized to a Cytoscape 3.x compatible string value
 * 
 */
public interface ComplexVPValue {

	public String toCX1String() throws NdexException;
	
}
