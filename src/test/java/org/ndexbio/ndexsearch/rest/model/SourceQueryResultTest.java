package org.ndexbio.ndexsearch.rest.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author churas
 */
public class SourceQueryResultTest {
	@Test
	public void testGettersAndSetters(){
		SourceQueryResult sqr = new SourceQueryResult();
		assertNull(sqr.getDescription());
		assertEquals(0, sqr.getDetails().size());
		assertEquals(0, sqr.getEdges());
		assertNull(sqr.getHitGenes());
		assertNull(sqr.getImageURL());
		assertNull(sqr.getNetworkUUID());
		assertEquals(0, sqr.getNodes());
		assertEquals(0, sqr.getPercentOverlap());
		assertEquals(0, sqr.getRank());
		assertNull(sqr.getUrl());
		
		sqr.setDescription("description");
		Map<String, Object> details = new HashMap<>();
		details.put("key", "value");
		sqr.setDetails(details);
		
		sqr.setEdges(1);
		Set<String> hitGenes = new HashSet<>();
		hitGenes.add("gene1");
		sqr.setHitGenes(hitGenes);
		
		sqr.setImageURL("imageurl");
		sqr.setNetworkUUID("networkuuid");
		sqr.setNodes(2);
		sqr.setPercentOverlap(3);
		sqr.setRank(4);
		sqr.setUrl("url");
		
		assertEquals("description", sqr.getDescription());
		assertEquals(1, sqr.getDetails().size());
		assertEquals("value", sqr.getDetails().get("key"));
		assertEquals(1, sqr.getEdges());
		assertTrue(sqr.getHitGenes().contains("gene1"));
		assertEquals("imageurl", sqr.getImageURL());
		assertEquals("networkuuid", sqr.getNetworkUUID());
		assertEquals(2, sqr.getNodes());
		assertEquals(3, sqr.getPercentOverlap());
		assertEquals(4, sqr.getRank());
		assertEquals("url", sqr.getUrl());
	}
}
