package org.ndexbio.cx2.aspect.element.core;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.ndexbio.cx2.aspect.element.core.CxNetworkAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NetworkAttributeTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String s0 = "{\"name\":\"foo\", \"edgecount\": 23, \"hasLayout\": false, \"authors\": [\"AK\",  \"EG\"], \"avg_score\": 3.224}";
		CxNetworkAttribute e = om.readValue(s0, CxNetworkAttribute.class);
		System.out.println(e.getElementObject());
		
		String s1 = om.writeValueAsString(e);
		
		assertEquals("{\"name\":\"foo\",\"edgecount\":23,\"hasLayout\":false,\"authors\":[\"AK\",\"EG\"],\"avg_score\":3.224}"
				, s1);
		System.out.println(s1);
		
		e.add("description", "some test");
		e.getAttributes().put("version", "3.0");
		
		assertEquals ( "foo", e.getNetworkName());
		assertEquals ( "some test", e.getNetworkDescription());
		assertEquals ("3.0", e.getNetworkVersion());
	}

}
