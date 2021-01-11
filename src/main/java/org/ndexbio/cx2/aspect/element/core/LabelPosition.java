package org.ndexbio.cx2.aspect.element.core;

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
		
		result.setAnchorFromCX1(values[0]);
		result.setAlignmentFromCX1(values[1]);
		result.setJustificationFromCX1(values[2]);
		
		result.setMarginsFromCX1(values[3], values[4]);
		return result;
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
