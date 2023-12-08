package org.ndexbio.cx2.aspect.element.core;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;

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
		assertNull(md2.getAttributeType());
		
		String s1 = om.writeValueAsString(md2);
		System.out.println(s1);
		assertEquals(s, s1);
		
		
		md2.setAttributeType(ATTRIBUTE_DATA_TYPE.LONG);
		
		String s2 = om.writeValueAsString(md2);
		System.out.println(s2);
		
		md = om.readValue(s2, MappingDefinition.class);
		assertEquals(md.getAttributeType(), ATTRIBUTE_DATA_TYPE.LONG);
		
		
		s = " {\"map\": [\n"
				+ "                                {\n"
				+ "                                    \"v\": \"phenotype\",\n"
				+ "                                    \"vp\": \"round-rectangle\"\n"
				+ "                                },\n"
				+ "                                {\n"
				+ "                                    \"v\": \"antibody\",\n"
				+ "                                    \"vp\": \"vee\"\n"
				+ "                                },\n"
				+ "                                {\n"
				+ "                                    \"v\": \"disease\",\n"
				+ "                                    \"vp\": \"hexagon\"\n"
				+ "                                },\n"
				+ "                                {\n"
				+ "                                    \"v\": \"rna\",\n"
				+ "                                    \"vp\": \"rectangle\"\n"
				+ "                                },\n"
				+ "                                {\n"
				+ "                                    \"v\": \"gene\",\n"
				+ "                                    \"vp\": \"round-rectangle\"\n"
				+ "                                }, {\n"
				+ "                                    \"v\": \"chemical\",\n"
				+ "                                    \"vp\": \"ellipse\"\n"
				+ "                                }],\n"
				+ "                            \"attribute\": \"type\",\n"
				+ "                            \"type\": \"string\"\n"
				+ "                        }";
		
		
		md2 = om.readValue(s, MappingDefinition.class);

		assertEquals("type", md2.getAttributeName());
		assertEquals(6,md2.getMapppingList().size());
		assertEquals("phenotype", md2.getMapppingList().get(0).get("v"));
		
		
		
	}

}
