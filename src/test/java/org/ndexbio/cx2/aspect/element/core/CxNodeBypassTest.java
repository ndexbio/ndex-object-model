package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CxNodeBypassTest {

	@Test
	public void test() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		
		String s0= "{\"id\":23, \"v\": {\"NODE_LABEL_POSITION\": \"C,C,c,0.25,10.86\"}}";
		
		CxNodeBypass e1 = om.readValue(s0, CxNodeBypass.class);
		
		String s2 = om.writeValueAsString(e1);
		
		System.out.println(s2);
		
		assertEquals("{\"id\":23,\"v\":{\"NODE_LABEL_POSITION\":\"C,C,c,0.25,10.86\"}}", s2);
		
	}

}
