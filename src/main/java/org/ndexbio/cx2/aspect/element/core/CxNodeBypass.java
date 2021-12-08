package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CxNodeBypass extends BypassVisualProperties<CxNodeBypass> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ASPECT_NAME = "nodeBypasses"; 
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}
	
	public CxNodeBypass() {super();}
	
	public CxNodeBypass(Long nodeId, VisualPropertyTable values) { 
		super();
		this.setId(nodeId);
		this.setVisualProperties(values);
	}

	@Override
	public int compareTo(CxNodeBypass o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
