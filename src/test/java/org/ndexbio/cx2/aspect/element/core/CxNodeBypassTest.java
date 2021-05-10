package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CxNodeBypassTest {

	@Test
	public void test() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		
		String s0= "{\"id\":23, \"v\": {\"NODE_LABEL_POSITION\": "
				+ "{\"HORIZONTAL_ALIGN\": \"center\",\n"
				+ "                            \"HORIZONTAL_ANCHOR\": \"center\",\n"
				+ "                            \"JUSTIFICATION\": \"center\",\n"
				+ "                            \"MARGIN_X\": 0.25,\n"
				+ "                            \"MARGIN_Y\": 10.86,\n"
				+ "                            \"VERTICAL_ALIGN\": \"center\",\n"
				+ "                            \"VERTICAL_ANCHOR\": \"center\"\n"
				+ "                        }}}";
		
		CxNodeBypass e1 = om.readValue(s0, CxNodeBypass.class);
		
		String s2 = om.writeValueAsString(e1);
		
		System.out.println(s2);
		
		assertEquals("{\"id\":23,\"v\":{\"NODE_LABEL_POSITION\":{\"HORIZONTAL_ALIGN\":\"center\",\"VERTICAL_ALIGN\":\"center\",\"HORIZONTAL_ANCHOR\":\"center\",\"VERTICAL_ANCHOR\":\"center\",\"MARGIN_X\":0.25,\"MARGIN_Y\":10.86,\"JUSTIFICATION\":\"center\"}}}", s2);
		
	}

}
