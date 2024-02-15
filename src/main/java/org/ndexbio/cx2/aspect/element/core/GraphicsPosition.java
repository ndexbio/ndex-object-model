package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)

public class GraphicsPosition extends CyObjectPosition implements ComplexVPValue{
		
	
	public final static String ENTITY_ANCHOR = "ENTITY_ANCHOR";  
	public final static String GRAPHICS_ANCHOR = "GRAPHICS_ANCHOR";
	
	// node or edge
	@JsonProperty(ENTITY_ANCHOR)
	private String entityAnchorPoints;  
	
	// label or custom graphics
	@JsonProperty(GRAPHICS_ANCHOR)
	private String graphicsAnchorPoints;

 
	public GraphicsPosition() {
		super();
		entityAnchorPoints = "C";
		graphicsAnchorPoints = "C";
	}

	/**
	 * Create an GraphicsPosition object from cx1 value. 
	 * @param cx1Value a serialized cx1 value string like this "SE,NW,l,0.00,27.00" 
	 * @return
	 */
	public static GraphicsPosition createFromCX1Value(String cx1Value) {
		GraphicsPosition result = new GraphicsPosition();
		String[] values = cx1Value.split(",");
		
		result.setEntityAnchorPoints(values[0]);
		result.setGraphicsAnchorPoints(values[1]);
		result.setJustification(HorizontalAlignment.fromCX1(values[2]));
		result.setMarginX(Float.parseFloat(values[3]));
		result.setMarginY(Float.parseFloat(values[4]));
		return result;
	}
	
	@Override
	public String toCX1String() {
		
		// create anchor first, alignment second, and then write out the marginX and Y
		return entityAnchorPoints+ "," + graphicsAnchorPoints + ","
				+ creatPartialCX1ValueString();
		 
	}

	public String getEntityAnchorPoints() {
		return entityAnchorPoints;
	}

	public void setEntityAnchorPoints(String edgeAnchorPoints) {
		this.entityAnchorPoints = edgeAnchorPoints;
	}

	public String getGraphicsAnchorPoints() {
		return graphicsAnchorPoints;
	}

	public void setGraphicsAnchorPoints(String labelAnchor) {
		this.graphicsAnchorPoints = labelAnchor;
	}
	
	public static GraphicsPosition createFromMap(Map<String,Object> m){
		
		GraphicsPosition result = new GraphicsPosition();
		
		result.populateFromMap(m);
		result.setEntityAnchorPoints((String)m.get(ENTITY_ANCHOR));
		result.setGraphicsAnchorPoints((String)m.get(GRAPHICS_ANCHOR));
		
		return result;
		
	}

	
	
}
