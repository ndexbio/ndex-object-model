package org.ndexbio.cx2.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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

	    // Additional tests for other methods and edge cases
}
