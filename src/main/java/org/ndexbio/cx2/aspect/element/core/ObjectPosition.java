package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class ObjectPosition {
	
	public HorizontalAlignment getHorizontalAlign() {
		return horizontalAlign;
	}

	public void setHorizontalAlign(HorizontalAlignment horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	public VerticalAlignment getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(VerticalAlignment verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	public HorizontalAlignment getHorizontalAnchor() {
		return horizontalAnchor;
	}

	public void setHorizontalAnchor(HorizontalAlignment horizontalAnchor) {
		this.horizontalAnchor = horizontalAnchor;
	}

	public VerticalAlignment getVerticalAnchor() {
		return verticalAnchor;
	}

	public void setVerticalAnchor(VerticalAlignment verticalAnchor) {
		this.verticalAnchor = verticalAnchor;
	}

	public float getMarginX() {
		return marginX;
	}

	public void setMarginX(float marginX) {
		this.marginX = marginX;
	}

	public float getMarginY() {
		return marginY;
	}

	public void setMarginY(float marginY) {
		this.marginY = marginY;
	}

	@JsonProperty("HORIZONTAL_ALIGN")
	HorizontalAlignment horizontalAlign;
	
	@JsonProperty("VERTICAL_ALIGN")	
	private VerticalAlignment verticalAlign;
	
	@JsonProperty("HORIZONTAL_ANCHOR")
	private HorizontalAlignment horizontalAnchor;
	
	@JsonProperty("VERTICAL_ANCHOR")
	private VerticalAlignment verticalAnchor;
	
	@JsonProperty("MARGIN_X")
	private float marginX;
	
	@JsonProperty("MARGIN_Y")
	private float marginY;
 
	public ObjectPosition() {
		horizontalAlign = HorizontalAlignment.center;
		verticalAlign = VerticalAlignment.center;
		horizontalAnchor = HorizontalAlignment.center;
		verticalAnchor = VerticalAlignment.center;
		marginX = 0;
		marginY = 0;
	}

	/**
	 * Create an LabelPosition object from cx1 value. 
	 * @param cx1Value a serialized cx1 value string like this "SE,NW,l,0.00,27.00" 
	 * @return
	 */
	public static ObjectPosition createFromCX1Value(String cx1Value) {
		ObjectPosition result = new ObjectPosition();
		String[] values = cx1Value.split(",");
		
		result.setAnchorFromCX1(values[0]);
		result.setAlignmentFromCX1(values[1]);		
		result.setMarginsFromCX1(values[3], values[4]);
		return result;
	}

	
	/* only for createFromCX1Value function. Be careful when using 
	 * this function in other places because it assumes other attributes have 
	 * the correct default values.
	 */
	protected void setAnchorFromCX1(String cx1NodeAnchorValue) {
		if (cx1NodeAnchorValue.length()==1 ) {
			setAnchorFromSingleLeter(cx1NodeAnchorValue.charAt(0));
		} else {
			setAnchorFromSingleLeter(cx1NodeAnchorValue.charAt(0));
			setAnchorFromSingleLeter(cx1NodeAnchorValue.charAt(1));
		}
	}
	
	protected void setAlignmentFromCX1(String cx1NodeAnchorValue) {
		if (cx1NodeAnchorValue.length()==1 ) {
			setAlignmentFromSingleLeter(cx1NodeAnchorValue.charAt(0));
		} else {
			setAlignmentFromSingleLeter(cx1NodeAnchorValue.charAt(0));
			setAlignmentFromSingleLeter(cx1NodeAnchorValue.charAt(1));
		}
	}
	
	private void setAnchorFromSingleLeter(char c) {
		switch ( c ) {
		case 'C':
			break;
		case 'N':
			verticalAnchor = VerticalAlignment.top;
			break;
		case 'S':
			verticalAnchor = VerticalAlignment.bottom;
			break;
		case 'W':
			horizontalAnchor = HorizontalAlignment.left;
			break;
		case 'E':
			horizontalAnchor = HorizontalAlignment.right;
			break;
		default: 
			break;
		}

	}
	
	private void setAlignmentFromSingleLeter(char c) {
		switch ( c ) {
		case 'C':
			break;
		case 'N':
			verticalAlign = VerticalAlignment.top;
			break;
		case 'S':
			verticalAlign = VerticalAlignment.bottom;
			break;
		case 'W':
			horizontalAlign = HorizontalAlignment.left;
			break;
		case 'E':
			horizontalAlign = HorizontalAlignment.right;
			break;
		default: 
			break;
		}

	}
	
	protected void setMarginsFromCX1(String x,String y) {
		marginX = Float.parseFloat(x);
		marginY = Float.parseFloat(y);
	}
}
