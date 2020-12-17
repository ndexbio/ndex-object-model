package org.ndexbio.ndexsearch.rest.model;

import java.util.UUID;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author churas
 */
public class SourceConfigurationTest {

	@Test
	public void testGettersAndSetters(){
		SourceConfiguration sc = new SourceConfiguration();
		assertNull(sc.getDescription());
		assertNull(sc.getEndPoint());
		assertNull(sc.getName());
		assertNull(sc.getUuid());
		
		sc.setDescription("description");
		sc.setEndPoint("endpoint");
		sc.setName("name");
		UUID theUUID = UUID.randomUUID();
		sc.setUuid(theUUID.toString());
		assertEquals("description", sc.getDescription());
		assertEquals("endpoint", sc.getEndPoint());
		assertEquals("name", sc.getName());
		assertEquals(theUUID.toString(), sc.getUuid().toString());
	}
}
