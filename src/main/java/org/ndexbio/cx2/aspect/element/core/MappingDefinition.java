package org.ndexbio.cx2.aspect.element.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)

public class MappingDefinition {

	@JsonProperty("map")
	private List<Map<String,Object>> mapppingList;
	
	@JsonProperty("attribute")
	private String attributeName;
	
    public MappingDefinition() {this.mapppingList = new ArrayList<>();}
 	
	public List<Map<String, Object>> getMapppingList() {
		return mapppingList;
	}

	public void setMapppingList(List<Map<String, Object>> mapppingList) {
		this.mapppingList = mapppingList;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}


}
