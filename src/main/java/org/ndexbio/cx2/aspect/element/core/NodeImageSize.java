package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeImageSize {
	
	private static final String HEIGHT="HEIGHT";
	private static final String WIDTH = "WIDTH";
	
	@JsonProperty(WIDTH)
	private float width;
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	@JsonProperty(HEIGHT)
	private float height;
	
	public static NodeImageSize createFromCX1Str(String v) {
		float s = Float.parseFloat(v);
		NodeImageSize result = new NodeImageSize();
		result.setHeight(s);
		result.setWidth(s);
		return result;
	}
	
	public static NodeImageSize createFromMap(Map<String,Object> map) throws NdexException {
		NodeImageSize result = new NodeImageSize();
		Object h = map.get(HEIGHT);
		if ( h==null)
			throw new NdexException ("Attribute HEIGHT is missing in node image size visual property.");
		if ( h instanceof Number)
			result.setHeight(((Number) h).floatValue());
		else 
			throw new NdexException("Attribute HEIGHT in node image size visual property is not a number.");
		
		Object w = map.get(WIDTH);
		if ( w==null)
			throw new NdexException ("Attribute WIDTH is missing in node image size visual property." );
		if ( w instanceof Number)
			result.setWidth(((Number) w).floatValue());
		else 
			throw new NdexException("Attribute WIDTH in node image size visual property is not a number.");
		return result;
	}
	
	public String toCX1String() {
		;
		return (width+height)/2 +"";
	} 

}
