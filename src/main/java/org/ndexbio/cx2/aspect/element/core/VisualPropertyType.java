package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VisualPropertyType {
	
	@JsonProperty("style")
	style,
	@JsonProperty("n")
	nodeByPass,
	
	@JsonProperty("e")
	edgeByPass;
	
}
