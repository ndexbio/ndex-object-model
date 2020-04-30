package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.VisualPropertyType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VisualPropertyTypeTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {
		
		String s0 = "\"n\"";
		ObjectMapper om = new ObjectMapper ();
		
		VisualPropertyType t = om.readValue(s0, VisualPropertyType.class);
		
		assertEquals ( VisualPropertyType.nodeByPass, t);
		
		String s2 = om.writeValueAsString(t);
		
		assertEquals (s0, s2 );
		
		s0 = "\"e\"";
	    t = om.readValue(s0, VisualPropertyType.class);
		
		assertEquals ( VisualPropertyType.edgeByPass, t);
		
		s2 = om.writeValueAsString(t);
		
		assertEquals (s0, s2 );
		
		s0 = "\"style\"";
	    t = om.readValue(s0, VisualPropertyType.class);
		
		assertEquals ( VisualPropertyType.style, t);
		
		s2 = om.writeValueAsString(t);
		
		assertEquals (s0, s2 );
		
	}

}
