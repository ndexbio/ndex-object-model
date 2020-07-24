package org.ndexbio.cx2.aspect.element.cytoscape;

import java.util.HashMap;
import java.util.Map;

import org.ndexbio.cx2.aspect.element.core.CxAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)

public class VisualEditorProperties implements CxAspectElement {
	
	public static final String ASPECT_NAME = "visualEditorProperties";

	@JsonProperty("properties")
	private Map<String, Object> properties;
	
	public VisualEditorProperties() { setProperties(new HashMap<>());}
	
	@Override
	public String getAspectName() {
		return ASPECT_NAME;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
