package org.ndexbio.ndexsearch.rest.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 *
 * @author churas
 */
public class QueryStatusTest {
	
	@Test
	public void testOverloadedConstructorNullArg(){
		QueryStatus qs = new QueryStatus(null);
		assertNull(qs.getInputSourceList());
		assertNull(qs.getMessage());
		assertEquals(0, qs.getNumberOfHits());
		assertEquals(0, qs.getProgress());
		assertNull(qs.getQuery());
		assertEquals(0, qs.getSize());
		assertNull(qs.getSource());
		assertEquals(0, qs.getStart());
		assertEquals(0l, qs.getStartTime());
		assertNull(qs.getStatus());
		assertEquals(0l, qs.getWallTime());
	}
	
	@Test
	public void testOverloadedConstructor(){
		QueryResults qr = new QueryResults();
		qr.setStatus("status");
		qr.setMessage("message");
		qr.setWallTime(1l);
		qr.setNumberOfHits(2);
		qr.setStart(3);
		qr.setSize(4);
		qr.setSource("source");
		qr.setStartTime(5l);
		qr.setProgress(6);
		qr.setInputSourceList(Arrays.asList("source1"));
		qr.setQuery(Arrays.asList("query1"));
		
		QueryStatus qs = new QueryStatus(qr);
		
		assertEquals(1, qs.getInputSourceList().size());
		assertEquals("source1", qs.getInputSourceList().get(0));
		assertEquals("message", qs.getMessage());
		assertEquals(2, qs.getNumberOfHits());
		assertEquals(6, qs.getProgress());
		assertEquals(1, qs.getQuery().size());
		assertEquals("query1", qs.getQuery().get(0));
		assertEquals(4, qs.getSize());
		assertEquals(3, qs.getStart());
		assertEquals(5l, qs.getStartTime());
		assertEquals("status", qs.getStatus());
		assertEquals(1l, qs.getWallTime());
	}
	@Test
	public void testGettersAndSetters(){
		QueryStatus qs = new QueryStatus();
		assertNull(qs.getInputSourceList());
		assertNull(qs.getMessage());
		assertEquals(0, qs.getNumberOfHits());
		assertEquals(0, qs.getProgress());
		assertNull(qs.getQuery());
		assertEquals(0, qs.getSize());
		assertNull(qs.getSource());
		assertEquals(0, qs.getStart());
		assertEquals(0l, qs.getStartTime());
		assertNull(qs.getStatus());
		assertEquals(0l, qs.getWallTime());
		
		qs.setInputSourceList(Arrays.asList("sourcelist1"));
		qs.setMessage("message");
		qs.setNumberOfHits(1);
		qs.setProgress(2);
		qs.setQuery(Arrays.asList("query"));
		qs.setSize(3);
		qs.setSource("source");
		qs.setStart(4);
		qs.setStartTime(5l);
		qs.setStatus("status");
		qs.setWallTime(6l);
		
		assertEquals(1, qs.getInputSourceList().size());
		assertEquals("sourcelist1", qs.getInputSourceList().get(0));
		assertEquals("message", qs.getMessage());
		assertEquals(1, qs.getNumberOfHits());
		assertEquals(2, qs.getProgress());
		assertEquals(1, qs.getQuery().size());
		assertEquals("query", qs.getQuery().get(0));
		assertEquals(3, qs.getSize());
		assertEquals(4, qs.getStart());
		assertEquals(5l, qs.getStartTime());
		assertEquals("status", qs.getStatus());
		assertEquals(6l, qs.getWallTime());
	}
	
	@Test
	public void testUpdateStartTime(){
		QueryStatus qs = new QueryStatus();
		qs.setStartTime(4l);
		qs.updateStartTime(null);
		assertEquals(4l, qs.getStartTime());
		QueryStatus newqs = new QueryStatus();
		newqs.setStartTime(3l);
		
		qs.updateStartTime(newqs);
		assertEquals(4l, qs.getStartTime());
		
		newqs.setStartTime(6l);

		qs.updateStartTime(newqs);
		assertEquals(6l, qs.getStartTime());
	}
}
