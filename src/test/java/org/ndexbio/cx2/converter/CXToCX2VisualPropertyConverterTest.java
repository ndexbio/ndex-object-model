package org.ndexbio.cx2.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ndexbio.cx2.aspect.element.core.CustomGraphics;
import org.ndexbio.cx2.aspect.element.core.GraphicsPosition;
import org.ndexbio.cx2.aspect.element.core.HorizontalAlignment;
import org.ndexbio.model.exceptions.NdexException;

class CXToCX2VisualPropertyConverterTest {

	 private CXToCX2VisualPropertyConverter converter;

	    @BeforeEach
	    void setUp() {
	        converter = CXToCX2VisualPropertyConverter.getInstance();
	    }

	    @Test
	    void testConvertNetworkVPs() {
	        // Prepare a sample input map for network visual properties
	        // Call the convertNetworkVPs method
	        // Assert the expected results
	    }

	    @Test
	    void testConvertEdgeOrNodeVPNames() {
	    	assertEquals("NODE_BORDER_COLOR",converter.getNewEdgeOrNodeProperty("NODE_BORDER_PAINT"));
	    	
	    	assertEquals("NODE_CUSTOMGRAPHICS_1",converter.getNewEdgeOrNodeProperty("NODE_CUSTOMGRAPHICS_1"));
	    	assertEquals("NODE_CUSTOMGRAPHICS_9",converter.getNewEdgeOrNodeProperty("NODE_CUSTOMGRAPHICS_9"));
	    	

	    	assertEquals("NODE_CUSTOMGRAPHICS_POSITION_1",converter.getNewEdgeOrNodeProperty("NODE_CUSTOMGRAPHICS_POSITION_1"));
	    	assertEquals("NODE_CUSTOMGRAPHICS_POSITION_9",converter.getNewEdgeOrNodeProperty("NODE_CUSTOMGRAPHICS_POSITION_9"));

	    	
	    }

	    @Test
	    void testConvertEdgeOrNodeVPValus() throws NdexException {
	    	assertEquals("#006699",converter.getNewEdgeOrNodePropertyValue("NODE_BORDER_PAINT", "#006699"));
	    	
	    	Object l1 = converter.getNewEdgeOrNodePropertyValue("NODE_CUSTOMGRAPHICS_1", 
	    			"org.cytoscape.LinearGradient:{\"cy_gradientFractions\":[0.0,1.0],\"cy_gradientColors\":[\"#E0ECF4\",\"#8856A7\"]}");
	    	
	    	assertTrue(l1 instanceof CustomGraphics);
	    	CustomGraphics cg = (CustomGraphics) l1;
	    	assertEquals("chart", cg.getType());
	    	assertEquals("org.cytoscape.LinearGradient", cg.getFullName());
	    	assertEquals(2, cg.getProperties().size());
	    	assertEquals(0.0, ((List)cg.getProperties().get("cy_gradientFractions")).get(0));
	    	assertEquals(1.0, ((List)cg.getProperties().get("cy_gradientFractions")).get(1));
	    	
	    	Object p1 = converter.getNewEdgeOrNodePropertyValue("NODE_CUSTOMGRAPHICS_POSITION_1", "SE,NW,l,0.00,27.00");
	    	assertTrue(p1 instanceof GraphicsPosition);
	    	GraphicsPosition gp = (GraphicsPosition) p1;
	    	assertEquals("SE", gp.getEntityAnchorPoints());
	    	assertEquals("NW", gp.getGraphicsAnchorPoints());
	    	assertEquals(HorizontalAlignment.left, gp.getJustification());
	    	assertEquals(0.0, gp.getMarginX());
	    	assertEquals(27.0, gp.getMarginY());
	    	
	    	
	    }

	    @Test
	    void testConvertEdgeArrowShapeValues() throws NdexException {
			// Prepare a sample input map for edge arrow shape values
	    	Map<String, String> edgeArrowShapeValues = new HashMap<>();
	    	edgeArrowShapeValues.put("ARROW", "arrow");
	    	edgeArrowShapeValues.put("ARROW_SHORT", "arrow_short");
	    	edgeArrowShapeValues.put("CIRCLE", "circle");
	    	edgeArrowShapeValues.put("CROSS_DELTA", "triangle-cross");
	    	edgeArrowShapeValues.put("CROSS_OPEN_DELTA", "cross_open_delta");
	    	edgeArrowShapeValues.put("DELTA", "triangle");
	    	edgeArrowShapeValues.put("DELTA_SHORT_1", "delta_short_1");
	    	edgeArrowShapeValues.put("DELTA_SHORT_2", "delta_short_2");
	    	edgeArrowShapeValues.put("DIAMOND", "diamond");
	    	edgeArrowShapeValues.put("DIAMOND_SHORT_1", "diamond_short_1");
	    	edgeArrowShapeValues.put("DIAMOND_SHORT_2", "diamond_short_2");
	    	edgeArrowShapeValues.put("HALF_BOTTOM", "half_bottom");
	    	edgeArrowShapeValues.put("HALF_CIRCLE", "half_circle");
	    	edgeArrowShapeValues.put("HALF_TOP", "half_top");
	    	edgeArrowShapeValues.put("NONE", "none");
	    	edgeArrowShapeValues.put("OPEN_CIRCLE", "open_circle");
	    	edgeArrowShapeValues.put("OPEN_DELTA", "open_delta");
	    	edgeArrowShapeValues.put("OPEN_DIAMOND", "open_diamond");
	    	edgeArrowShapeValues.put("OPEN_HALF_CIRCLE", "open_half_circle");
	    	edgeArrowShapeValues.put("OPEN_SQUARE", "open_square");
	    	edgeArrowShapeValues.put("SQUARE", "square");
	    	edgeArrowShapeValues.put("T", "tee");
	    	
	    	String vpName = "EDGE_TARGET_ARROW_SHAPE";
	    	
			// Call the convertEdgeArrowShapeValues method
			// Assert the expected
			for (Map.Entry<String, String> entry : edgeArrowShapeValues.entrySet()) {
				assertEquals(entry.getValue(), converter.getNewEdgeOrNodePropertyValue(vpName, entry.getKey()));
			}
	    	
	    }
	    // Additional tests for other methods and edge cases
}
