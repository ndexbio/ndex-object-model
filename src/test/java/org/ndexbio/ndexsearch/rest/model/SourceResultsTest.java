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
	
	@Test
	public void testCopyConstructor(){
		SourceResults sr = new SourceResults(null);
		assertNull(sr.getResults());
		
		//make a copy
		SourceResults copySR = new SourceResults(sr);
		SourceResult sRes = new SourceResult();
		sRes.setName("res");
		sr.setResults(Arrays.asList(sRes));
		assertEquals(1, sr.getResults().size());
		assertEquals("res", sr.getResults().get(0).getName());
		
		DatabaseResult dr = new DatabaseResult();
		dr.setName("dr");
		
		NetworkInfo niOne = new NetworkInfo();
		niOne.setName("one");
		NetworkInfo niTwo = new NetworkInfo();
		niTwo.setName("two");
		
		dr.setNetworks(Arrays.asList(niOne, niTwo));
		sRes.setDatabases(Arrays.asList(dr));
		sRes.setName("name");
		
		assertNull(copySR.getResults());
		
		SourceResults copyTwoSR = new SourceResults(sr);
		assertEquals(1, copyTwoSR.getResults().size());
		assertEquals(2, copyTwoSR.getResults().get(0).getDatabases().get(0).getNetworks().size());
		
		SourceResults copyThreeSR = new SourceResults(sr, true);
		assertEquals(1, copyThreeSR.getResults().size());
		assertEquals(null, copyThreeSR.getResults().get(0).getDatabases().get(0).getNetworks());

	}
}
