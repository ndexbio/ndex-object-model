package org.ndexbio.cx2.aspect.element.core;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectPositionTest {

	@Test
	public void test() throws JsonProcessingException {
		String CX1Value = "SE,NW,l,-5.00,27.00";
		ObjectPosition p = ObjectPosition.createFromCX1Value(CX1Value);
		
		assertEquals(HorizontalAlignment.left, p.getHorizontalAlign());
		assertEquals(HorizontalAlignment.right, p.getHorizontalAnchor());

		assertEquals(VerticalAlignment.top, p.getVerticalAlign());
		assertEquals(VerticalAlignment.bottom, p.getVerticalAnchor());
		
		assertEquals(-5.0f, p.getMarginX(), 0.000001);
		assertEquals(27.0, p.getMarginY(), 0.000001);
		
		ObjectMapper om = new ObjectMapper();
	        
	    String s = om.writeValueAsString(p);

	    System.out.println(s);
	    
	    assertEquals("{\"HORIZONTAL_ALIGN\":\"left\",\"VERTICAL_ALIGN\":\"top\",\"HORIZONTAL_ANCHOR\":\"right\",\"VERTICAL_ANCHOR\":\"bottom\",\"MARGIN_X\":-5.0,\"MARGIN_Y\":27.0}",
	    		s);
	    
	    ObjectPosition p2 = om.readValue(s, ObjectPosition.class);
	    
	    
	    assertEquals(p.getHorizontalAlign(),p2.getHorizontalAlign());
	    
	    
	    assertEquals(p.getMarginX(), p2.getMarginX(), 0.000001);

	}

}
