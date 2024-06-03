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
	
	@Test
	public void testCopyConstructor(){
		DatabaseResult dr = new DatabaseResult();
		assertNull(dr.getDescription());
		assertNull(dr.getImageURL());
		assertNull(dr.getName());
		assertNull(dr.getNetworks());
		assertNull(dr.getNumberOfNetworks());
		assertNull(dr.getUrl());
		assertNull(dr.getUuid());
		
		DatabaseResult copyDr = new DatabaseResult(dr);
		
		dr.setDescription("description");
		dr.setImageURL("imageurl");
		dr.setName("name");
		
		NetworkInfo net = new NetworkInfo();
		net.setName("networkinfo");
		dr.setNetworks(Arrays.asList(net));
		
		dr.setNumberOfNetworks("1");
		dr.setUrl("url");
		dr.setUuid("uuid");
		
		assertNull(copyDr.getDescription());
		assertNull(copyDr.getImageURL());
		assertNull(copyDr.getName());
		assertNull(copyDr.getNetworks());
		assertNull(copyDr.getNumberOfNetworks());
		assertNull(copyDr.getUrl());
		assertNull(copyDr.getUuid());
		
		DatabaseResult secondCopyDr = new DatabaseResult(dr);
		assertEquals("description", secondCopyDr.getDescription());
		assertEquals("imageurl", secondCopyDr.getImageURL());
		assertEquals("name", secondCopyDr.getName());
		assertEquals(1, secondCopyDr.getNetworks().size());
		assertEquals("networkinfo", secondCopyDr.getNetworks().get(0).getName());
		assertEquals("url", secondCopyDr.getUrl());
		assertEquals("uuid", secondCopyDr.getUuid());
		
		DatabaseResult thirdCopyDr = new DatabaseResult(secondCopyDr, true);
		assertEquals("description", thirdCopyDr.getDescription());
		assertEquals("imageurl", thirdCopyDr.getImageURL());
		assertEquals("name", thirdCopyDr.getName());
		assertEquals(null, thirdCopyDr.getNetworks());
		assertEquals("url", thirdCopyDr.getUrl());
		assertEquals("uuid", thirdCopyDr.getUuid());
		
	}
}
