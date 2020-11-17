package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.ndexbio.cxio.misc.OpaqueElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpaqueAspectElementTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {
		String s0 = "{\"v\":\"interacts with\",\"a\":\"i\", \"c\": 23.3, \"d\": true,"+
	                 "\"e\":24, \"f\": [23,45.2]}";
		ObjectMapper om = new ObjectMapper();
		
		CxOpaqueAspectElement e = om.readValue(s0, CxOpaqueAspectElement.class);
		
		assertEquals("i", e.getElementObject().get("a"));
		assertEquals(23.3, e.getElementObject().get("c"));
		assertEquals(true, e.getElementObject().get("d"));
		assertEquals(2, ((List<?>)e.getElementObject().get("f")).size());
		
		System.out.println(e.getElementObject());
		
		String s2 = om.writeValueAsString(e);
		System.out.println(s2);
		
		assertEquals("{\"a\":\"i\",\"c\":23.3,\"d\":true,\"e\":24,\"v\":\"interacts with\",\"f\":[23,45.2]}", s2);
		
	}
	
	
	@Test
	public void test2() throws JsonMappingException, JsonProcessingException {
		String s0 = "{\"v\":\"interacts with\",\"a\":\"i\", \"c\": 23.3, \"d\": true,"+
	                 "\"e\":24, \"f\": [23,45.2]}";
		ObjectMapper om = new ObjectMapper();

		JsonNode jsonNode = om.readTree(s0);
		
		OpaqueElement cx1opaqueElmt = new OpaqueElement ("foo", jsonNode);
		
		CxOpaqueAspectElement e = om.convertValue(cx1opaqueElmt.getData(), CxOpaqueAspectElement.class);
		
		e.setAspectName(cx1opaqueElmt.getAspectName());
		
		assertEquals("i", e.getElementObject().get("a"));
		assertEquals(23.3, e.getElementObject().get("c"));
		assertEquals(true, e.getElementObject().get("d"));
		assertEquals(2, ((List<?>)e.getElementObject().get("f")).size());
		
		System.out.println(e.getElementObject());
		
		String s2 = om.writeValueAsString(e);
		System.out.println(s2);
		
		assertEquals("{\"a\":\"i\",\"c\":23.3,\"d\":true,\"e\":24,\"v\":\"interacts with\",\"f\":[23,45.2]}", s2);
		
	}

}
