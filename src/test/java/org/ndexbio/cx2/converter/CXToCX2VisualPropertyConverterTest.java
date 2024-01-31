package org.ndexbio.cx2.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ndexbio.cx2.aspect.element.core.CustomGraphics;
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
	    	
	    }

	    // Additional tests for other methods and edge cases
}
