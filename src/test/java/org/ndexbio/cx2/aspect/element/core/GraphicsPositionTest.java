package org.ndexbio.cx2.aspect.element.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class GraphicsPositionTest {

	   @Test
	    public void testCreateFromCX1Value() {
	        String cx1Value = "SE,NW,l,0.00,27.00";
	        GraphicsPosition edgeLabelPosition = GraphicsPosition.createFromCX1Value(cx1Value);

	        assertEquals(HorizontalAlignment.left, edgeLabelPosition.getJustification(), "Justification should be LEFT");
	        assertEquals("SE", edgeLabelPosition.getEntityAnchorPoints(), "Entity anchor points should be 'SE'");
	        assertEquals("NW", edgeLabelPosition.getGraphicsAnchorPoints(), "Graphics anchor points should be 'NW'");
	        assertEquals(0.00f, edgeLabelPosition.getMarginX(), "Margin X should be 0.00");
	        assertEquals(27.00f, edgeLabelPosition.getMarginY(), "Margin Y should be 27.00");
	    }

	    @Test
	    public void testToCX1String() {
	        GraphicsPosition edgeLabelPosition = new GraphicsPosition();
	        edgeLabelPosition.setJustification(HorizontalAlignment.center);
	        edgeLabelPosition.setEntityAnchorPoints("C");
	        edgeLabelPosition.setGraphicsAnchorPoints("C");
	        edgeLabelPosition.setMarginX(1.5f);
	        edgeLabelPosition.setMarginY(-2.5f);

	        String expectedCX1String = "C,C,c,1.5,-2.5";
	        assertEquals(expectedCX1String, edgeLabelPosition.toCX1String(), "CX1 string representation should match");
	    }

	    @Test
	    public void testCreateFromMap() throws JsonProcessingException {
	        Map<String, Object> map = new HashMap<>();
	        map.put(LabelPosition.JUSTIFICATION, HorizontalAlignment.left.name());
	        map.put(GraphicsPosition.ENTITY_ANCHOR, "SE");
	        map.put(GraphicsPosition.GRAPHICS_ANCHOR, "NW");
	        map.put(ObjectPosition.MARGIN_X, 1.5f);
	        map.put(ObjectPosition.MARGIN_Y, 3.2f);
	        GraphicsPosition imagePosition = GraphicsPosition.createFromMap(map);
	        	        
	        assertEquals(HorizontalAlignment.left, imagePosition.getJustification());
	        assertEquals("SE", imagePosition.getEntityAnchorPoints());
	        assertEquals("NW", imagePosition.getGraphicsAnchorPoints());
	        assertEquals(1.5f, imagePosition.getMarginX(), 0.001f);
	        assertEquals(3.2f, imagePosition.getMarginY(), 0.001f);
	        
	        ObjectMapper mapper = new ObjectMapper();
	        
			String s = mapper.writeValueAsString(imagePosition);
			System.out.println(s);
			
			GraphicsPosition imagePosition2 = mapper.readValue(s, GraphicsPosition.class);
			System.out.println(imagePosition2.toCX1String());	
					
					
			assertEquals(imagePosition.getJustification(), imagePosition2.getJustification());
			assertEquals(imagePosition.getEntityAnchorPoints(), imagePosition2.getEntityAnchorPoints());
			assertEquals(imagePosition.getGraphicsAnchorPoints(), imagePosition2.getGraphicsAnchorPoints());
			assertEquals(imagePosition.getMarginX(), imagePosition2.getMarginX(), 0.001f);
			assertEquals(imagePosition.getMarginY(), imagePosition2.getMarginY(), 0.001f);
			
	    }

}
