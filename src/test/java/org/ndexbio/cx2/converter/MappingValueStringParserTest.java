package org.ndexbio.cx2.converter;

import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author churas
 */
public class MappingValueStringParserTest {
    
	@Test
	public void testSplitStringBySingleCommasEmptyString(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas("");
		assertEquals(1, res.size());
		assertEquals("", res.get(0));
	}
	
	@Test
	public void testSplitStringBySingleCommasSingleComma(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas(",");
		assertEquals(2, res.size());
		assertEquals("", res.get(0));
		assertEquals("", res.get(1));
		
	}
	
	@Test
	public void testSplitStringBySingleCommasTwoCommas(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas(",,");
		assertEquals(1, res.size());
		assertEquals(",", res.get(0));		
	}
	
	@Test
	public void testSplitStringBySingleCommasTwoElement(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas("COL=ab,cd");
		assertEquals(2, res.size());
		assertEquals("COL=ab", res.get(0));
		assertEquals("cd", res.get(1));
	}
	
	@Test
	public void testSplitStringBySingleCommasTwoElementWithDoubleComma(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas("COL=ab,cd,,e");
		assertEquals(2, res.size());
		assertEquals("COL=ab", res.get(0));
		assertEquals("cd,e", res.get(1));
	}
	
	@Test
	public void testSplitStringBySingleCommasTwoElementWithTripleComma(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas("COL=ab,cd,,,e");
		assertEquals(3, res.size());
		assertEquals("COL=ab", res.get(0));
		assertEquals("cd,", res.get(1));
		assertEquals("e", res.get(2));
	}
	
	@Test
	public void testSplitStringBySingleCommasWithColumnNameStartingAndEndingWithTwoCommas(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas("COL=,,,,yo,,,T=string,K=0=0,V=0=#66FF66,K=1=1,V=1=#0033FF");
		assertEquals(6, res.size());
		assertEquals("COL=,,yo,", res.get(0));
		assertEquals("T=string", res.get(1));
		assertEquals("K=0=0", res.get(2));
		assertEquals("V=0=#66FF66", res.get(3));
		assertEquals("K=1=1", res.get(4));
		assertEquals("V=1=#0033FF", res.get(5));
	}
	
	/**
	 * This test directly tests failure encountered in ticket
	 * https://ndexbio.atlassian.net/browse/SRV2-173
	 * where the pattern matcher causes a stackoverflow in the old code
	 */
	@Test
	public void testSplitStringBySingleCommasWithLargeBase64ImageData(){
		List<String> res = MappingValueStringParser.splitStringBySingleCommas("COL=STRING style,T=string,K=0=string:data:image/png;base64,,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,V=0=org.cytoscape.ding.customgraphics.bitmap.URLImageCustomGraphics,,13,,Arabidopsis_thaliana_NL.png,,bitmap image,K=1=string:data:image/png;base64,,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,V=1=org.cytoscape.ding.customgraphics.bitmap.URLImageCustomGraphics,,41,,file:/Users/dexter/dexter%20headshot%20torrey%20pines%20beach.png,,bitmap image");
		assertEquals(6, res.size());
		assertEquals("COL=STRING style", res.get(0));
		assertEquals("T=string", res.get(1));
		assertTrue("K=0=0", res.get(2).startsWith("K=0=string:data:image/png;base64,i"));
		assertEquals("V=0=org.cytoscape.ding.customgraphics.bitmap.URLImageCustomGraphics,13,Arabidopsis_thaliana_NL.png,bitmap image", res.get(3));
		assertTrue("K=1=1", res.get(4).startsWith("K=1=string:data:image/png;base64,iV"));
		assertEquals("V=1=org.cytoscape.ding.customgraphics.bitmap.URLImageCustomGraphics,41,file:/Users/dexter/dexter%20headshot%20torrey%20pines%20beach.png,bitmap image", res.get(5));
	}
		
	@Test
	public void testDiscreteNodeHeight() throws IOException {
		MappingValueStringParser parser = new MappingValueStringParser("COL=node type,T=string,K=0=protein,V=0=50.0,K=1=compound,V=1=40.0");
		assertEquals(6, parser.getKeys().size());
		assertEquals("node type", parser.get("COL"));
		assertEquals("string", parser.get("T"));
		assertEquals("50.0", parser.get("V=0"));
		assertEquals("40.0", parser.get("V=1"));
		assertEquals("compound", parser.get("K=1"));
		assertEquals("protein", parser.get("K=0"));
	}
	
	@Test
	public void testValueWithDoubleCommaDelimiters() throws IOException {
		MappingValueStringParser parser = new MappingValueStringParser("V=1=org.cytoscape.ding."
				+ "customgraphics.bitmap.URLImageCustomGraphics,,41,,file:/Users/dexter/dexter%20headshot%20torrey%20pines%20beach.png,,bitmap image");
		assertEquals(1, parser.getKeys().size());
		assertEquals("org.cytoscape.ding.customgraphics."
				+ "bitmap.URLImageCustomGraphics,41,"
				+ "file:/Users/dexter/dexter%20head"
				+ "shot%20torrey%20pines%20beach.png,"
				+ "bitmap image", parser.get("V=1"));
	}
	
	@Test
	public void testPassThroughNodeLabelOfDisplayName() throws IOException {
		MappingValueStringParser parser = new MappingValueStringParser("COL=display name,T=string");
		assertEquals(2, parser.getKeys().size());
		assertEquals("display name", parser.get("COL"));
		assertEquals("string", parser.get("T"));
	}
	
	@Test
	public void testContinuousEdgeTransparency() throws IOException {
		MappingValueStringParser parser = new MappingValueStringParser("COL=score,T=double,L=0=34,E=0=34,G=0=34,OV=0=0.2,L=1=85,E=1=85,G=1=85,OV=1=0.5,L=2=170,E=2=170,G=2=170,OV=2=1.0");
		assertEquals(14, parser.getKeys().size());
		assertEquals("score", parser.get("COL"));
		assertEquals("double", parser.get("T"));
		assertEquals("34", parser.get("L=0"));
		assertEquals("34", parser.get("E=0"));
		assertEquals("34", parser.get("G=0"));
		assertEquals("0.2", parser.get("OV=0"));
		assertEquals("85", parser.get("L=1"));
		assertEquals("85", parser.get("E=1"));
		assertEquals("85", parser.get("G=1"));
		assertEquals("0.5", parser.get("OV=1"));
		assertEquals("170", parser.get("L=2"));
		assertEquals("170", parser.get("E=2"));
		assertEquals("170", parser.get("G=2"));
		assertEquals("1.0", parser.get("OV=2"));
	}
	
	@Test
	public void testRaiseIOException() throws IOException {
		String mapStr = "COL=score,T=double,L==0=34,E=0=34";
		try {
			
			MappingValueStringParser parser = new MappingValueStringParser(mapStr);
			fail("Expected IOException");
		} catch(IOException io){
			assertEquals("Failed to parse mapping segment 'L==0=34' in mapping " 
					+ mapStr + ".",
					io.getMessage());
		}
	}
}