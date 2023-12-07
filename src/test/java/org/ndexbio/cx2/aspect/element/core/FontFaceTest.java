package org.ndexbio.cx2.aspect.element.core;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FontFaceTest {

	@Test
	public void test() throws JsonProcessingException {
		FontFace f = new FontFace();
		
		ObjectMapper om = new ObjectMapper();
		
		String v = om.writeValueAsString(f);
		
		System.out.println(v);
		
		assertEquals ( "{\"FONT_FAMILY\":\"sans-serif\",\"FONT_STYLE\":\"normal\",\"FONT_WEIGHT\":\"normal\"}",v);
	
	    String v2 = "{\"FONT_FAMILY\":\"sans-serif\",\"FONT_STYLE\":\"italic\",\"FONT_WEIGHT\":\"bold\"}";
	    
	    FontFace f2 = om.readValue(v2, FontFace.class);
	    
	    assertEquals ( FontFace.PORTABLE_SANS_SERIF_FONT, f2.getFamily());
	    assertEquals (FontFace.ITALIC, f2.getStyle());
	    assertEquals (FontFace.BOLD, f2.getWeight());
	    
	    assertEquals (v2, om.writeValueAsString(f2));
	}

}
