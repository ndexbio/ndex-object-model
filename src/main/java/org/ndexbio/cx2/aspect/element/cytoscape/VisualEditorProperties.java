package org.ndexbio.cx2.aspect.element.cytoscape;

import java.util.HashMap;
import java.util.Map;

import org.ndexbio.cx2.aspect.element.core.CxAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)

public class VisualEditorProperties implements CxAspectElement<VisualEditorProperties> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ASPECT_NAME = "visualEditorProperties";

	@JsonProperty("properties")
	private Map<String, Object> properties;
	
	public VisualEditorProperties() { setProperties(new HashMap<>());}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	@Override
	public int compareTo(VisualEditorProperties o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
