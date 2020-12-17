package org.ndexbio.ndexsearch.rest.model;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author churas
 */
public class ServerStatusTest {

	@Test
	public void testGettersAndSetters(){
		ServerStatus ss = new ServerStatus();
		assertNull(ss.getLoad());
		assertEquals(0, ss.getPcDiskFull());
		assertNull(ss.getQueries());
		assertNull(ss.getRestVersion());
		assertNull(ss.getStatus());
		
		ss.setLoad(Arrays.asList(0f));
		ss.setPcDiskFull(10);
		ss.setQueries(Arrays.asList(1));
		ss.setRestVersion("version");
		ss.setStatus("status");
		assertEquals(1, ss.getLoad().size());
		
		assertEquals(0, Float.compare(0f, ss.getLoad().get(0)));
		
		assertEquals(1, ss.getQueries().size());
		assertEquals(0, Integer.compare(1, ss.getQueries().get(0)));
		
		assertEquals("version", ss.getRestVersion());
		assertEquals("status", ss.getStatus());
		assertEquals(10, ss.getPcDiskFull());
		
	}
}
