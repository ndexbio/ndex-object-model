package org.ndexbio.ndexsearch.rest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 *
 * @author churas
 */
public class SourceResultTest {
	
	@Test
	public void testGettersAndSetters(){
		SourceResult sr = new SourceResult();
		assertNull(sr.getDatabases());
		assertNull(sr.getDescription());
		assertNull(sr.getEndPoint());
		assertNull(sr.getName());
		assertEquals(0, sr.getNumberOfNetworks());
		assertNull(sr.getStatus());
		assertNull(sr.getUuid());
		assertNull(sr.getVersion());
		
		DatabaseResult dr = new DatabaseResult();
		dr.setName("dr");
		sr.setDatabases(Arrays.asList(dr));
		sr.setDescription("description");
		sr.setEndPoint("endpoint");
		sr.setName("name");
		sr.setNumberOfNetworks(1);
		sr.setStatus("status");
		sr.setUuid("uuid");
		sr.setVersion("version");
		
		assertEquals(1, sr.getDatabases().size());
		assertEquals("dr", sr.getDatabases().get(0).getName());
		assertEquals("description", sr.getDescription());
		assertEquals("endpoint", sr.getEndPoint());
		assertEquals("name", sr.getName());
		assertEquals(1, sr.getNumberOfNetworks());
		assertEquals("status", sr.getStatus());
		assertEquals("uuid", sr.getUuid());
		assertEquals("version", sr.getVersion());
	}
}
