package org.ndexbio.ndexsearch.rest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 *
 * @author churas
 */
public class SourceQueryResultsTest {
	
	@Test
	public void testGettersAndSetters(){
		SourceQueryResults sqr = new SourceQueryResults();
		assertNull(sqr.getMessage());
		assertEquals(0, sqr.getNumberOfHits());
		assertEquals(0, sqr.getProgress());
		assertNull(sqr.getResults());
		assertNull(sqr.getSourceName());
		assertEquals(0, sqr.getSourceRank());
		assertNull(sqr.getSourceTaskId());
		assertNull(sqr.getSourceUUID());
		assertNull(sqr.getStatus());
		assertEquals(0l, sqr.getWallTime());
		
		sqr.setMessage("message");
		sqr.setNumberOfHits(1);
		sqr.setProgress(2);
		SourceQueryResult sqr1 = new SourceQueryResult();
		sqr1.setDescription("sqr1");
		sqr.setResults(Arrays.asList(sqr1));
		sqr.setSourceName("sourcename");
		sqr.setSourceRank(3);
		sqr.setSourceTaskId("taskid");
		UUID srcUUID = UUID.randomUUID();
		sqr.setSourceUUID(srcUUID);
		sqr.setStatus("status");
		sqr.setWallTime(4);
		
		assertEquals("message", sqr.getMessage());
		assertEquals(1, sqr.getNumberOfHits());
		assertEquals(2, sqr.getProgress());
		assertEquals(1, sqr.getResults().size());
		assertEquals("sqr1", sqr.getResults().get(0).getDescription());
		
		assertEquals("sourcename", sqr.getSourceName());
		assertEquals(3, sqr.getSourceRank());
		assertEquals("taskid", sqr.getSourceTaskId());
		assertEquals(srcUUID.toString(), sqr.getSourceUUID().toString());
		assertEquals("status", sqr.getStatus());
		assertEquals(4, sqr.getWallTime());
	}
}
