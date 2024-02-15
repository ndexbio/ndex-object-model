package org.ndexbio.ndexsearch.rest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 *
 * @author churas
 */
public class NetworkInfoTest {
	
	@Test
	public void testGettersAndSetters(){
		NetworkInfo ni = new NetworkInfo();
		assertNull(ni.getDescription());
		assertEquals(0, ni.getEdgeCount());
		assertEquals(0, ni.getNodeCount());
		assertNull(ni.getImageUrl());
		assertEquals(0, ni.getGeneCount());
		assertNull(ni.getName());
		assertNull(ni.getUrl());
		assertNull(ni.getUuid());
		
		ni.setDescription("description");
		ni.setEdgeCount(1);
		ni.setNodeCount(2);
		ni.setGeneCount(3);
		ni.setName("name");
		ni.setImageUrl("imageurl");
		ni.setUrl("url");
		ni.setUuid("uuid");
		
		assertEquals("description", ni.getDescription());
		assertEquals(1, ni.getEdgeCount());
		assertEquals(2, ni.getNodeCount());
		assertEquals("imageurl", ni.getImageUrl());
		assertEquals(3, ni.getGeneCount());
		assertEquals("name", ni.getName());
		assertEquals("url", ni.getUrl());
		assertEquals("uuid", ni.getUuid());
	}
}
