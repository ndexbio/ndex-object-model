package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class VisualPropertyTable {
	
	private Map<String, Object> visualProperties;
	
	public static final String imagePositionPattern = "^NODE_IMAGE_[0-9]+_POSITION$";
	
	public static final String imageSizePattern = "^NODE_IMAGE_[0-9]+_SIZE$";

	
	@JsonAnyGetter
	public Map<String, Object> getVisualProperties() {
		return visualProperties;
	}
	
	
	public static Object castVPValue(String vpName, Object e) throws NdexException {
		if 	( e instanceof Map<?,?>) {
			if (vpName.equals("EDGE_LABEL_FONT_FACE") ||
					vpName.equals("NODE_LABEL_FONT_FACE")) {
				return FontFace.createFromMap((Map<String,String>)e);
			} else if ( vpName.equals("NODE_LABEL_POSITION")) {
				return LabelPosition.createFromLabelPositionMap((Map<String,Object>)e);
			} else if ( vpName.matches(imagePositionPattern)) {
				return ObjectPosition.createFromMap((Map<String,Object>)e);
			} else if ( vpName.matches(imageSizePattern))
				return NodeImageSize.createFromMap((Map<String,Object>)e);			
			else if (vpName.equals("EDGE_LABEL_POSITION"))
				return EdgeLabelPosition.createFromMap((Map<String,Object>)e);
			else	
				return e;
		} else if ( e instanceof List<?>) {
			if ( vpName.equals("EDGE_CONTROL_POINTS")) {
				List<Map<String,Object>> rawPoints = (List<Map<String,Object>>)e;
				return  rawPoints.stream()
						.map(p -> { return EdgeControlPoint.createFromMap(p);})
						.collect(Collectors.toList());
			} else
				return e;
		} else 
		   return e;
	}
	
	

	@JsonAnySetter
	public void addRaw(String key, Object e) throws NdexException {
		   visualProperties.put(key,castVPValue(key,e));
	}

	public VisualPropertyTable() {
		visualProperties = new HashMap<>();
	}
	
	@JsonIgnore() 
	public Object get(String visualPropertyName) {
		return visualProperties.get(visualPropertyName);
	}
	
	@JsonIgnore()
	public void setVisualProperties(Map<String,Object> props) {
		this.visualProperties = props;
	}
}
