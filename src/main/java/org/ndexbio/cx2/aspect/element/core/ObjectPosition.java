package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class ObjectPosition {
	
	public static final String MARGIN_X = "MARGIN_X";
	public static final String MARGIN_Y = "MARGIN_Y";
	
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
	
	@JsonProperty(MARGIN_X)
	private float marginX;
	
	@JsonProperty(MARGIN_Y)
	private float marginY;
 
	public ObjectPosition() {
		horizontalAlign = HorizontalAlignment.center;
		verticalAlign = VerticalAlignment.center;
		horizontalAnchor = HorizontalAlignment.center;
		verticalAnchor = VerticalAlignment.center;
		marginX = 0;
		marginY = 0;
	}

	
	public String toCX1String() {
		
		// create anchor first, alignment second, and then write out the marginX and Y
		return createCX1AnchorValue() + "," + createCX1AlignmentValue() + ",c," + marginX + "," + marginY;
		 
	}
	/**
	 * Create an LabelPosition object from cx1 value. 
	 * @param cx1Value a serialized cx1 value string like this "SE,NW,l,0.00,27.00" 
	 * @return
	 */
	public static ObjectPosition createFromCX1Value(String cx1Value) {
		ObjectPosition result = new ObjectPosition();
		String[] values = cx1Value.split(",");
		populateFromCX1Values(result,values);
		return result;
	}

	
	protected String createCX1AnchorValue() {
		if ( verticalAnchor == VerticalAlignment.center) {
			return horizontalAlignmentToCX1(this.horizontalAnchor);
		} 
		
		String result = verticalAlignmentToCX1(verticalAnchor);
		if ( horizontalAnchor == HorizontalAlignment.center)
			return result;
		 
		return result + horizontalAlignmentToCX1(horizontalAnchor);
		
	}
	
	protected String createCX1AlignmentValue() {
		if ( verticalAlign == VerticalAlignment.center) {
			return horizontalAlignmentToCX1(this.horizontalAlign);
		} 
		
		String result = verticalAlignmentToCX1(verticalAlign);
		if ( horizontalAlign == HorizontalAlignment.center)
			return result;
		 
		return result + horizontalAlignmentToCX1(horizontalAlign);
		
	}

	protected static void populateFromCX1Values(ObjectPosition postion, String[] values) {
		
		postion.setAnchorFromCX1(values[0]);
		postion.setAlignmentFromCX1(values[1]);		
		postion.setMarginsFromCX1(values[3], values[4]);
	}
	
	public static ObjectPosition createFromMap(Map<String,Object> m) {
		
		ObjectPosition result = new ObjectPosition();
		populateFromMap(result,m);
		return result;
		
	}
	
	protected static void populateFromMap(ObjectPosition p, Map<String,Object>m) {
		
		p.setHorizontalAlign(HorizontalAlignment.valueOf((String)m.get("HORIZONTAL_ALIGN")));
		p.setVerticalAlign(VerticalAlignment.valueOf((String)m.get("VERTICAL_ALIGN")));
		p.setHorizontalAnchor(HorizontalAlignment.valueOf((String)m.get("HORIZONTAL_ANCHOR")));
		p.setVerticalAnchor(VerticalAlignment.valueOf((String)m.get("VERTICAL_ANCHOR")));	
		p.setMarginX(((Number)m.get("MARGIN_X")).floatValue());
		p.setMarginY(((Number)m.get("MARGIN_Y")).floatValue());
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
	
	private static String horizontalAlignmentToCX1 (HorizontalAlignment a) {
		if ( a == HorizontalAlignment.center )
			return "C";
		if ( a == HorizontalAlignment.left)
			return "W";
		return "E";
	}
	
	private static String verticalAlignmentToCX1 (VerticalAlignment a) {
		if ( a == VerticalAlignment.center)
			return "C";
		if ( a == VerticalAlignment.top)
			return "N";
		return "S";
	}


	
	protected void setMarginsFromCX1(String x,String y) {
		marginX = Float.parseFloat(x);
		marginY = Float.parseFloat(y);
	}
}
