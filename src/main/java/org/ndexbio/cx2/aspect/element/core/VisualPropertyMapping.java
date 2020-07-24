package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VisualPropertyMapping {
	
	//public final static String PASSTHROUGH = "PASSTHROUGH";
	//public final static String DISCRETE = "DISCRETE";
	//public final static String CONTINUOUS = "CONTINUOUS";
	
	@JsonProperty("type")
	private VPMappingType type;
	
	public VPMappingType getType() {
		return type;
	}

	public void setType(VPMappingType type) {
		this.type = type;
	}

	public MappingDefinition getMappingDef() {
		return mappingDef;
	}

	public void setMappingDef(MappingDefinition mappingDef) {
		this.mappingDef = mappingDef;
	}

	@JsonProperty("definition")
	private MappingDefinition mappingDef;
	

}
