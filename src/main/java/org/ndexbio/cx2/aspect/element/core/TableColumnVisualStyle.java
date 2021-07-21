package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)

public class TableColumnVisualStyle {

	@JsonProperty("default")
	private Object defaultValue;
	
	
	@JsonProperty("mapping")
	private VisualPropertyMapping mapping;


	public Object getDefaultValue() {
		return defaultValue;
	}


	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}


	public VisualPropertyMapping getMapping() {
		return mapping;
	}


	public void setMapping(VisualPropertyMapping mapping) {
		this.mapping = mapping;
	}

}
