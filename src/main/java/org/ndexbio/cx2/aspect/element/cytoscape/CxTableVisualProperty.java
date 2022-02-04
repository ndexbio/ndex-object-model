package org.ndexbio.cx2.aspect.element.cytoscape;

import org.ndexbio.cx2.aspect.element.core.CxAspectElement;
import org.ndexbio.cxio.aspects.datamodels.CyTableVisualPropertiesElement;

public class CxTableVisualProperty extends AbstractTableVisualProperty implements CxAspectElement<CxTableVisualProperty> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CxTableVisualProperty() {
	}
	
	public CxTableVisualProperty (CyTableVisualPropertiesElement tableStyle) {
		setSubnetId(tableStyle.getSubnetId());
		this.setTableStyles(tableStyle.getTableStyles());
	}
	
	@Override
	public int compareTo(CxTableVisualProperty o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
