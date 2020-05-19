package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxEdge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CxEdgeTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		
		CxEdge e = om.readerFor(CxEdge.class)
				.readValue("{\"id\":23, \"s\": 2, \"t\": 33, \"v\":{\"i\":\"interact with\", \"pmids\": [13333,25555]}}");
		
		assertEquals(23, e.getId());
		assertEquals(2, e.getSource());
		assertEquals(33, e.getTarget());
		List<Object> val =  (List<Object>) e.getAttributes().get("pmids");
		
		assertEquals(Integer.valueOf(13333), val.get(0));
		assertEquals(2, val.size());
		
		String s = om.writeValueAsString(e);
		assertEquals("{\"id\":23,\"s\":2,\"t\":33,\"v\":{\"i\":\"interact with\",\"pmids\":[13333,25555]}}"
				,s);
		
	}

}
