package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NodeImageSizeTest {

	@Test
	public void test() throws JsonProcessingException {
		NodeImageSize size = new NodeImageSize();
		
		size.setWidth(20.1f);
		size.setHeight(20.2f);
		
		ObjectMapper om = new ObjectMapper();
		
		String s = om.writeValueAsString(size);
		
		System.out.println(s);
		NodeImageSize size2 = om.readValue(s, NodeImageSize.class);
		
		assertEquals(20.1f, size2.getWidth(), 0.00001);
		assertEquals(20.2f, size.getHeight(), 0.00001);
		
	}

}
