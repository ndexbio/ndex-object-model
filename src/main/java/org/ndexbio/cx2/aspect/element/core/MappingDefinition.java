package org.ndexbio.cx2.aspect.element.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;

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
	
	/**
	 * Data type of the attribute. This value can be omitted if the attribute is defined in the Attribute declaration.
	 * We are adding this to support the use case of storing a mapping that is on a non-exists data column. When we convert it to cx1
	 * or import it to Cytoscape Desktop, we will need that information.
	 */
	@JsonProperty("type")
	private ATTRIBUTE_DATA_TYPE attributeType;
	
	
    public ATTRIBUTE_DATA_TYPE getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(ATTRIBUTE_DATA_TYPE attributeType) {
		this.attributeType = attributeType;
	}

	public MappingDefinition() {this.mapppingList = new ArrayList<>();}
    
    public MappingDefinition(String attributeName) {
    	this.attributeName = attributeName;
    	this.mapppingList = new ArrayList<>();
    }
 	
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
