package org.ndexbio.ndexsearch.rest.model;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author churas
 */
public class QueryTest {
	
	@Test
	public void testGettersAndSetters(){
		Query q = new Query();
		assertNull(q.getGeneList());
		assertNull(q.getSourceList());
		
		q.setGeneList(Arrays.asList("g1"));
		q.setSourceList(Arrays.asList("s1"));
		
		assertEquals(1, q.getGeneList().size());
		assertEquals("g1", q.getGeneList().get(0));
		
		assertEquals(1, q.getSourceList().size());
		assertEquals("s1", q.getSourceList().get(0));
		
	}
}
