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
	
	@Test
	public void testNullCopyConstructor(){
		SourceResult sr = new SourceResult(null);
		assertNull(sr.getDatabases());
		assertNull(sr.getDescription());
		assertNull(sr.getEndPoint());
		assertNull(sr.getName());
		assertEquals(0, sr.getNumberOfNetworks());
		assertNull(sr.getStatus());
		assertNull(sr.getUuid());
		assertNull(sr.getVersion());
	}
	
	@Test
	public void testCopyConstructors(){
		
		SourceResult sr = new SourceResult();
		assertNull(sr.getDatabases());
		assertNull(sr.getDescription());
		assertNull(sr.getEndPoint());
		assertNull(sr.getName());
		assertEquals(0, sr.getNumberOfNetworks());
		assertNull(sr.getStatus());
		assertNull(sr.getUuid());
		assertNull(sr.getVersion());
		
		// make a copy
		SourceResult srCopy = new SourceResult(sr);
		
		//now update the original sr
		DatabaseResult dr = new DatabaseResult();
		dr.setName("dr");
		
		NetworkInfo niOne = new NetworkInfo();
		niOne.setName("one");
		NetworkInfo niTwo = new NetworkInfo();
		niTwo.setName("two");
		
		dr.setNetworks(Arrays.asList(niOne, niTwo));
		sr.setDatabases(Arrays.asList(dr));
		sr.setDescription("description");
		sr.setEndPoint("endpoint");
		sr.setName("name");
		sr.setNumberOfNetworks(1);
		sr.setStatus("status");
		sr.setUuid("uuid");
		sr.setVersion("version");
		
		// verify copy has not changed
		assertNull(srCopy.getDatabases());
		assertNull(srCopy.getDescription());
		assertNull(srCopy.getEndPoint());
		assertNull(srCopy.getName());
		assertEquals(0, srCopy.getNumberOfNetworks());
		assertNull(srCopy.getStatus());
		assertNull(srCopy.getUuid());
		assertNull(srCopy.getVersion());
		
		// but the sr has
		assertEquals(1, sr.getDatabases().size());
		assertEquals("dr", sr.getDatabases().get(0).getName());
		assertEquals("one", sr.getDatabases().get(0).getNetworks().get(0).getName());
		assertEquals("two", sr.getDatabases().get(0).getNetworks().get(1).getName());
		assertEquals("description", sr.getDescription());
		assertEquals("endpoint", sr.getEndPoint());
		assertEquals("name", sr.getName());
		assertEquals(1, sr.getNumberOfNetworks());
		assertEquals("status", sr.getStatus());
		assertEquals("uuid", sr.getUuid());
		assertEquals("version", sr.getVersion());
		
		// now copy sr again once with networks and once without
		SourceResult srCopyTwo = new SourceResult(sr);
		assertEquals(1, srCopyTwo.getDatabases().size());
		assertEquals("dr", srCopyTwo.getDatabases().get(0).getName());
		assertEquals("description", srCopyTwo.getDescription());
		assertEquals("endpoint", srCopyTwo.getEndPoint());
		assertEquals("name", srCopyTwo.getName());
		assertEquals(1, srCopyTwo.getNumberOfNetworks());
		assertEquals("status", srCopyTwo.getStatus());
		assertEquals("uuid", srCopyTwo.getUuid());
		assertEquals("version", srCopyTwo.getVersion());
		
		SourceResult srCopyThree = new SourceResult(sr, true);
		assertEquals(1, srCopyThree.getDatabases().size());
		assertEquals(null, srCopyThree.getDatabases().get(0).getNetworks());
		assertEquals("description", srCopyThree.getDescription());
		assertEquals("endpoint", srCopyThree.getEndPoint());
		assertEquals("name", srCopyThree.getName());
		assertEquals(1, srCopyThree.getNumberOfNetworks());
		assertEquals("status", srCopyThree.getStatus());
		assertEquals("uuid", srCopyThree.getUuid());
		assertEquals("version", srCopyThree.getVersion());
		
		
	}
}
