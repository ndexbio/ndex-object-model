package org.ndexbio.model.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SearchUtilitiesTest {

	
	@SuppressWarnings("static-method")
	@Test
	public void CVSParsing() {
		String str = "A,Category,\"Agriculture, forestry and fishing\",";
		String r1 = SearchUtilities.preprocessSearchTerm(str);
		assertEquals (r1 , "A Category \"Agriculture, forestry and fishing\" ");
		
	}
	
}
