package org.ndexbio.model.cx;

import org.ndexbio.model.object.NdexPropertyValuePair;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CXSimpleAttribute {

	@JsonProperty("n")
	private String name;
	
	@JsonProperty("v")
	private String value;

	@JsonProperty("t")
	private String dataType;
	
	public CXSimpleAttribute() {
	}
	
	public CXSimpleAttribute(NdexPropertyValuePair p ) {
		name = p.getPredicateString();
		value = p.getValue();
		dataType = p.getDataType();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
