package org.ndexbio.cx2.aspect.element.core;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CxEdgeBypassTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		
		String s0= "{\"id\":23, \"v\": {\"EDGE_WIDTH\": 22}}";
		
		CxEdgeBypass e1 = om.readValue(s0, CxEdgeBypass.class);
		
		String s2 = om.writeValueAsString(e1);
		
		System.out.println(s2);
		
		assertEquals("{\"id\":23,\"v\":{\"EDGE_WIDTH\":22}}", s2);
		
	}

}
