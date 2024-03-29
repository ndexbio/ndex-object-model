package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class LabelPosition extends ObjectPosition implements ComplexVPValue{
	
	public static final String JUSTIFICATION = "JUSTIFICATION";
	
	@JsonProperty(JUSTIFICATION)	
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
		result.setJustification(HorizontalAlignment.fromCX1(values[2]));
		return result;
	}
	
	public static LabelPosition createFromLabelPositionMap(Map<String,Object> m) throws NdexException {
		
		LabelPosition result = new LabelPosition();
		populateFromMap(result, m);
		
		String j = (String)m.get(JUSTIFICATION);
		if ( j == null) {
			throw new NdexException("Attribute " + JUSTIFICATION + " is missing in label position object.");
		} 
		result.setJustification(HorizontalAlignment.valueOf(j));
		return result;
		
	}
	
	@Override
	public String toCX1String() {
		
		// create anchors first, alignment second, and then write out the marginX and Y
		return createCX1AnchorValue() + "," + createCX1AlignmentValue() + ","
				+ justification.toCX1() + "," + getMarginX() + "," + getMarginY();
		 
	}
	
}
