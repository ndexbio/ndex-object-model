package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeImageSize {
	
	@JsonProperty("WIDTH")
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

	@JsonProperty("HEIGHT")
	private float height;
	
	public static NodeImageSize createFromCX1Str(String v) {
		float s = Float.parseFloat(v);
		NodeImageSize result = new NodeImageSize();
		result.setHeight(s);
		result.setWidth(s);
		return result;
	}
	
	public String toCX1String() {
		;
		return (width+height)/2 +"";
	} 

}
