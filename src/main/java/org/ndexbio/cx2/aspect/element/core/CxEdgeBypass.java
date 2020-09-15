package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CxEdgeBypass extends BypassVisualProperties<CxEdgeBypass> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ASPECT_NAME = "edgeBypasses"; 
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	@Override
	public int compareTo(CxEdgeBypass o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
