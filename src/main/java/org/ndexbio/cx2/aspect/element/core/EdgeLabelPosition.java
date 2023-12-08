package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class EdgeLabelPosition implements ComplexVPValue{
		
	@JsonProperty(LabelPosition.JUSTIFICATION)	
	private HorizontalAlignment justification;
	
	public final static String EDGE_ANCHOR = "EDGE_ANCHOR";  
	public final static String LABEL_ANCHOR = "LABEL_ANCHOR";
	
	@JsonProperty(EDGE_ANCHOR)
	private String edgeAnchorPoints;
	
	@JsonProperty(LABEL_ANCHOR)
	private String labelAnchorPoints;

	@JsonProperty(ObjectPosition.MARGIN_X)
	private float marginX;
	
	public float getMarginX() {
		return marginX;
	}

	public void setMarginX(float marginX) {
		this.marginX = marginX;
	}

	@JsonProperty(ObjectPosition.MARGIN_Y)
	private float marginY;
	
	public float getMarginY() {
		return marginY;
	}

	public void setMarginY(float marginY) {
		this.marginY = marginY;
	}

	public HorizontalAlignment getJustification() {
		return justification;
	}

	public void setJustification(HorizontalAlignment justification) {
		this.justification = justification;
	}

 
	public EdgeLabelPosition() {
		super();
		justification = HorizontalAlignment.center;
		edgeAnchorPoints = "C";
		labelAnchorPoints = "C";
	}

	/**
	 * Create an LabelPosition object from cx1 value. 
	 * @param cx1Value a serialized cx1 value string like this "SE,NW,l,0.00,27.00" 
	 * @return
	 */
	public static EdgeLabelPosition createFromCX1Value(String cx1Value) {
		EdgeLabelPosition result = new EdgeLabelPosition();
		String[] values = cx1Value.split(",");
		
		result.setEdgeAnchorPoints(values[0]);
		result.setLabelAnchorPoints(values[1]);
		result.setJustification(HorizontalAlignment.fromCX1(values[2]));
		result.marginX = Float.parseFloat(values[3]);
		result.marginY = Float.parseFloat(values[4]);
		return result;
	}
	
	public String toCX1String() {
		
		// create anchor first, alignment second, and then write out the marginX and Y
		return edgeAnchorPoints+ "," + labelAnchorPoints + ","
				+ justification.toCX1() + "," + marginX + "," + marginY;
		 
	}


	public String getEdgeAnchorPoints() {
		return edgeAnchorPoints;
	}

	public void setEdgeAnchorPoints(String edgeAnchorPoints) {
		this.edgeAnchorPoints = edgeAnchorPoints;
	}

	public String getLabelAnchorPoints() {
		return labelAnchorPoints;
	}

	public void setLabelAnchorPoints(String labelAnchor) {
		this.labelAnchorPoints = labelAnchor;
	}
	
	public static EdgeLabelPosition createFromMap(Map<String,Object> m){
		
		EdgeLabelPosition result = new EdgeLabelPosition();
		
		result.setMarginX(((Number)m.get("MARGIN_X")).floatValue());
		result.setMarginY(((Number)m.get("MARGIN_Y")).floatValue());
		result.setJustification(HorizontalAlignment.valueOf((String)m.get(LabelPosition.JUSTIFICATION)));
		result.setEdgeAnchorPoints((String)m.get(EDGE_ANCHOR));
		result.setLabelAnchorPoints((String)m.get(LABEL_ANCHOR));
		
		return result;
		
	}

	
	
}
