package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class VisualPropertyTable {
	
	private Map<String, Object> visualProperties;
	
	private static final String imagePositionPattern = "^NODE_IMAGE_[0-9]+_POSITION$";
	
	@JsonAnyGetter
	public Map<String, Object> getVisualProperties() {
		return visualProperties;
	}
	
	@JsonAnySetter
	public void addRaw(String key, Object e) {
		if 	( e instanceof Map<?,?>) {
			if (key.equals("EDGE_LABEL_FONT_FACE") ||
					key.equals("NODE_LABEL_FONT_FACE")) {
				visualProperties.put(key, FontFace.createFromMap((Map<String,String>)e));
			} else if ( key.equals("NODE_LABEL_POSITION")) {
				visualProperties.put(key,LabelPosition.createFromMap((Map<String,Object>)e));
			} else if ( key.matches(imagePositionPattern)) {
				visualProperties.put(key, ObjectPosition.createFromMap((Map<String,Object>)e));
			}
		} else 
		    visualProperties.put(key,e);
	}

	public VisualPropertyTable() {
		visualProperties = new HashMap<>();
	}
	
	@JsonIgnore() 
	public Object get(String visualPropertyName) {
		return visualProperties.get(visualPropertyName);
	}
	
}
