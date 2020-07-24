package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingDefinitionTest {

	@Test
	public void test() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper ();
		
		MappingDefinition md = new MappingDefinition();
		md.setAttributeName("name");
		String s0 = om.writeValueAsString(md);
		System.out.println(s0);
		
		assertEquals("{\"attribute\":\"name\"}", s0);
		
		MappingDefinition md2 = om.readValue(s0, MappingDefinition.class);
		assertEquals("name", md2.getAttributeName());
		
		String s = "{\"map\":[{\"v\":1,\"vp\":\"#CCCCFF\"},{\"v\":2,\"vp\":\"#FF9999\"}],\"attribute\":\"size_code\"}";
		md2 = om.readValue(s, MappingDefinition.class);
		assertEquals("size_code", md2.getAttributeName());
		assertEquals(2,md2.getMapppingList().size());
		assertEquals(1, md2.getMapppingList().get(0).get("v"));
		assertEquals("#FF9999", md2.getMapppingList().get(1).get("vp"));
		
		String s1 = om.writeValueAsString(md2);
		System.out.println(s1);
		assertEquals(s, s1);
		
	}

}
