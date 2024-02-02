package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class EdgeLabelPosition extends CyObjectPosition implements ComplexVPValue{
		
	public final static String EDGE_ANCHOR = "EDGE_ANCHOR";  
	public final static String LABEL_ANCHOR = "LABEL_ANCHOR";
	
	@JsonProperty(EDGE_ANCHOR)
	private String edgeAnchorPoints;
	
	@JsonProperty(LABEL_ANCHOR)
	private String labelAnchorPoints;

 
	public EdgeLabelPosition() {
		super();
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
		result.setMarginX(Float.parseFloat(values[3]));
		result.setMarginY(Float.parseFloat(values[4]));
		return result;
	}
	
	@Override
	public String toCX1String() {
		
		// create anchor first, alignment second, and then write out the marginX and Y
		return edgeAnchorPoints+ "," + labelAnchorPoints + "," + creatPartialCX1ValueString();
		 
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
		
		result.populateFromMap(m);
		result.setEdgeAnchorPoints((String)m.get(EDGE_ANCHOR));
		result.setLabelAnchorPoints((String)m.get(LABEL_ANCHOR));
		
		return result;
		
	}

	
	
}
