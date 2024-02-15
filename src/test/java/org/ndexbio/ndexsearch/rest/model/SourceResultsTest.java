package org.ndexbio.ndexsearch.rest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 *
 * @author churas
 */
public class SourceResultsTest {
	
	@Test
	public void testConstructors(){
		InternalSourceResults isr = new InternalSourceResults();
		SourceResult sRes = new SourceResult();
		sRes.setName("res");
		isr.setResults(Arrays.asList(sRes));
		
		SourceResults sr = new SourceResults(isr);
		
		assertEquals(1, sr.getResults().size());
		assertEquals("res", sr.getResults().get(0).getName());
	}
	
	@Test
	public void testGetterAndSetters(){
		SourceResults sr = new SourceResults();
		assertNull(sr.getResults());
		SourceResult sRes = new SourceResult();
		sRes.setName("res");
		sr.setResults(Arrays.asList(sRes));
		assertEquals(1, sr.getResults().size());
		assertEquals("res", sr.getResults().get(0).getName());
	}
}
