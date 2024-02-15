package org.ndexbio.ndexsearch.rest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 *
 * @author churas
 */
public class SourceConfigurationsTest {
	
	@Test
	public void testGetSources(){
		SourceConfigurations sc = new SourceConfigurations();
		assertNull(sc.getSources());
		
		SourceConfiguration sconf = new SourceConfiguration();
		sconf.setName("hi");
		sc.setSources(Arrays.asList(sconf));
		assertEquals(1, sc.getSources().size());
		assertEquals("hi", sc.getSources().get(0).getName());
	}
	
	@Test
	public void testGetSourceConfigurationByNameNoSources(){
		SourceConfigurations sc = new SourceConfigurations();
		assertNull(sc.getSourceConfigurationByName(null));
	}
	
	@Test
	public void testGetSourceConfigurationByNameSourceHasNullForName(){
		SourceConfigurations sc = new SourceConfigurations();
		SourceConfiguration sconf = new SourceConfiguration();
		
		sc.setSources(Arrays.asList(sconf));
		assertNull(sc.getSourceConfigurationByName("foo"));
	}
	
	@Test
	public void testGetSourceConfigurationByNameSourceNullPassedIn(){
		SourceConfigurations sc = new SourceConfigurations();
		SourceConfiguration sconf = new SourceConfiguration();
		
		sc.setSources(Arrays.asList(sconf));
		assertNull(sc.getSourceConfigurationByName(null));
	}
	
	@Test
	public void testGetSourceConfigurationByNameMatchFound(){
		SourceConfigurations sc = new SourceConfigurations();
		SourceConfiguration sconf = new SourceConfiguration();
		SourceConfiguration sconf2 = new SourceConfiguration();
		sconf2.setName("blah");
		
		SourceConfiguration sconf3 = new SourceConfiguration();
		sconf3.setName("fo");
		
		SourceConfiguration sconf4 = new SourceConfiguration();
		sconf4.setName("foo");

		sc.setSources(Arrays.asList(sconf, sconf2, sconf3, sconf4));
		
		SourceConfiguration res = sc.getSourceConfigurationByName("foo");
		assertEquals("foo", res.getName());
	}
	
}
