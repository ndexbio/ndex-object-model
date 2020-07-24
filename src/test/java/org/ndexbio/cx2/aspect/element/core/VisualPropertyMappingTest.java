package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VisualPropertyMappingTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		
		VisualPropertyMapping vpm = new VisualPropertyMapping();
		vpm.setType(VPMappingType.DISCRETE);
		String s = "{\"map\":[{\"v\":1,\"vp\":\"#CCCCFF\"},{\"v\":2,\"vp\":\"#FF9999\"}],\"attribute\":\"size_code\"}";
		MappingDefinition md2 = om.readValue(s, MappingDefinition.class);
		vpm.setMappingDef(md2);
		
		String o = om.writeValueAsString(vpm);
		System.out.println(o);
		assertEquals("{\"type\":\"DISCRETE\",\"definition\":{\"map\":[{\"v\":1,\"vp\":\"#CCCCFF\"},{\"v\":2,\"vp\":\"#FF9999\"}],\"attribute\":\"size_code\"}}",
				o);
		VisualPropertyMapping vpm2 = om.readValue(o, VisualPropertyMapping.class);
		assertEquals(VPMappingType.DISCRETE, vpm2.getType());
		assertEquals("size_code",vpm2.getMappingDef().getAttributeName());
		assertEquals(2, vpm2.getMappingDef().getMapppingList().size());
	}

}
