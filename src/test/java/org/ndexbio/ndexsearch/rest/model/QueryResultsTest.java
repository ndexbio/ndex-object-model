package org.ndexbio.ndexsearch.rest.model;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author churas
 */
public class QueryResultsTest {
	
	@Test
	public void testConstructor(){
		QueryResults qr = new QueryResults();
		assertEquals(0, qr.getStartTime());
		
		qr = new QueryResults(10);
		assertEquals(10, qr.getStartTime());
	}
	
	@Test
	public void testUpdateStartTime(){
		QueryResults qr = new QueryResults(5);
		assertEquals(5, qr.getStartTime());
		
		QueryResults qr2 = new QueryResults(10);
		assertEquals(10, qr2.getStartTime());
		
		qr2.updateStartTime(qr);
		assertEquals(10, qr2.getStartTime());
		
		qr.updateStartTime(qr2);
		assertEquals(10, qr.getStartTime());
	}
	
	@Test
	public void testSources(){
		QueryResults qr = new QueryResults();
		assertNull(qr.getSources());
		SourceQueryResults sqr = new SourceQueryResults();
		sqr.setMessage("sqr1");
		qr.setSources(Arrays.asList(sqr));
		assertEquals(1, qr.getSources().size());
		assertEquals("sqr1", qr.getSources().get(0).getMessage());
	}
}
