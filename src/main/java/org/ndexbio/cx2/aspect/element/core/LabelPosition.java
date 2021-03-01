package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class LabelPosition extends ObjectPosition {
	
	@JsonProperty("JUSTIFICATION")	
	private HorizontalAlignment justification;
	
	public HorizontalAlignment getJustification() {
		return justification;
	}

	public void setJustification(HorizontalAlignment justification) {
		this.justification = justification;
	}

 
	public LabelPosition() {
		super();
		justification = HorizontalAlignment.center;
		horizontalAlign = HorizontalAlignment.center;
	}

	/**
	 * Create an LabelPosition object from cx1 value. 
	 * @param cx1Value a serialized cx1 value string like this "SE,NW,l,0.00,27.00" 
	 * @return
	 */
	public static LabelPosition createFromCX1Value(String cx1Value) {
		LabelPosition result = new LabelPosition();
		String[] values = cx1Value.split(",");
		
		ObjectPosition.populateFromCX1Values(result, values);
		result.setJustificationFromCX1(values[2]);
		return result;
	}
	
	public static LabelPosition createFromMap(Map<String,Object> m) {
		
		LabelPosition result = new LabelPosition();
		populateFromMap(result, m);
		
		result.setJustification(HorizontalAlignment.valueOf((String)m.get("JUSTIFICATION")));
		return result;
		
	}
	
	@Override
	public String toCX1String() {
		
		// create anchor first, alignment second, and then write out the marginX and Y
		return createCX1AnchorValue() + "," + createCX1AlignmentValue() + ","
				+ justificationToCX1() + "," + getMarginX() + "," + getMarginY();
		 
	}

	private String justificationToCX1() {
		switch (justification) {
		case center:
			return "c";
		case left:
			return "l";
		default:
			return "r";
		}
	}
	
	private void setJustificationFromCX1(String c) {
		switch ( c ) {
		case "c":
			break;
		case "l":
			justification = HorizontalAlignment.left;
			break;
		case "r":
			justification = HorizontalAlignment.right;
			break;
		default: 
			break;
		}

	}
	
}
