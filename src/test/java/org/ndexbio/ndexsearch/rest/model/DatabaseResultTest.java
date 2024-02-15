package org.ndexbio.ndexsearch.rest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
/**
 *
 * @author churas
 */
public class DatabaseResultTest {
	
	@Test
	public void testGettersAndSetters(){
		DatabaseResult dr = new DatabaseResult();
		assertNull(dr.getDescription());
		assertNull(dr.getImageURL());
		assertNull(dr.getName());
		assertNull(dr.getNetworks());
		assertNull(dr.getNumberOfNetworks());
		assertNull(dr.getUrl());
		assertNull(dr.getUuid());
			
		dr.setDescription("description");
		dr.setImageURL("imageurl");
		dr.setName("name");
		
		NetworkInfo net = new NetworkInfo();
		net.setName("networkinfo");
		dr.setNetworks(Arrays.asList(net));
		
		dr.setNumberOfNetworks("1");
		dr.setUrl("url");
		dr.setUuid("uuid");
		
		assertEquals("description", dr.getDescription());
		assertEquals("imageurl", dr.getImageURL());
		assertEquals("name", dr.getName());
		assertEquals(1, dr.getNetworks().size());
		assertEquals("networkinfo", dr.getNetworks().get(0).getName());
		assertEquals("url", dr.getUrl());
		assertEquals("uuid", dr.getUuid());
	}
}
