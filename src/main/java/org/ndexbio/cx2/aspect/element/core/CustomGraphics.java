package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)

public class CustomGraphics implements ComplexVPValue {

	//prefix of the image type custom graphics in the full name.
	private static final String imagePrefix = "org.cytoscape.ding.customgraphics.";
	
	//type of the custom graphics. Can be image, chart, or gradient.
	@JsonProperty("type")
	private String type;
	
	// fully qualified name of the custom graphics.
	@JsonProperty("name")
	private String fullName;
	
	// properties of the custom graphics.
	@JsonProperty("properties")
	private Map<String,Object> properties;
	
	@Override
	public String toCX1String() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Map<String,Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String,Object> properties) {
		this.properties = properties;
	}

}
