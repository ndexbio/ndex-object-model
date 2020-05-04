package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CxEdgeBypass extends BypassVisualProperties {

	public static final String ASPECT_NAME = "edgeBypasses"; 
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}
	
	

}
