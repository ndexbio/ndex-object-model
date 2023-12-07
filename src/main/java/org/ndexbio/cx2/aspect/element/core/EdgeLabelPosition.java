package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class EdgeLabelPosition implements CX1ValueSeerializable{
		
	@JsonProperty(LabelPosition.JUSTIFICATION)	
	private HorizontalAlignment justification;
	
	@JsonProperty("edgeAnchor")
	private String edgeAnchorPoints;
	
	@JsonProperty("labelAnchor")
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
	
}
