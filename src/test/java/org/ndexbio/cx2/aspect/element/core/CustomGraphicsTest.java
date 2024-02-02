package org.ndexbio.cx2.aspect.element.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CustomGraphicsTest {

	@Test
	void test() throws NdexException, JsonMappingException, JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper();
		String s = "org.cytoscape.BarChart:{\"cy_range\":[-1.232,1.022],\"cy_showRangeAxis\":true,"
				+ "\"cy_axisLabelFontSize\":2,\"cy_showRangeZeroBaseline\":true,\"cy_colorScheme\":\"Set3 colors\","
				+ "\"cy_colors\":[\"#8DD3C7\",\"#FFFFB3\",\"#BEBADA\"],\"cy_itemLabelFontSize\":4,\"cy_showDomainAxis\":true,"
				+ "\"cy_axisColor\":\"#238443\",\"cy_axisWidth\":0.5,\"cy_autoRange\":true,\"cy_borderColor\":\"#CB181D\","
				+ "\"cy_borderWidth\":0.5,\"cy_dataColumns\":[\"gal1RGexp\",\"gal4RGexp\",\"gal80Rexp\"],"
				+ "\"cy_domainLabelPosition\":\"DOWN_45\",\"cy_showItemLabels\":true}";
		CustomGraphics cg = CustomGraphics.createFromCX1Value(s);
		assertEquals("org.cytoscape.BarChart", cg.getFullName());
		assertEquals(16, cg.getProperties().size());
		assertEquals("gal4RGexp", ((List)cg.getProperties().get("cy_dataColumns")).get(1));
		assertEquals("chart", cg.getType());
		assertEquals(1.022,((List)cg.getProperties().get("cy_range")).get(1));
		assertEquals("DOWN_45", cg.getProperties().get("cy_domainLabelPosition"));
		assertEquals("Set3 colors", cg.getProperties().get("cy_colorScheme"));
		assertEquals("#FFFFB3", ((List)cg.getProperties().get("cy_colors")).get(1));
		assertEquals(3, ((List)cg.getProperties().get("cy_colors")).size());
		assertEquals("#238443", cg.getProperties().get("cy_axisColor"));
		
		String s1 = cg.toCX1String();
		
		CustomGraphics cg1 = CustomGraphics.createFromCX1Value(s1);
		assertEquals (cg1, cg);
		
        String s2 = om.writeValueAsString(cg);	
        System.out.println("JSON string of a custom graphics obj: "+s2);
        
        CustomGraphics cg2 = om.readValue(s2, CustomGraphics.class);
        assertEquals (cg2, cg);
        
        String s3 = cg2.toCX1String();
        
        CustomGraphics cg3 = CustomGraphics.createFromCX1Value(s3);
        assertEquals (cg3, cg);
        

	}
	
	@Test
	void test1() throws NdexException, JsonMappingException, JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper();
		String s = "org.cytoscape.HeatMapChart:{\"cy_range\":[-2.426,3.126],\"cy_showRangeAxis\":false,"
				+ "\"cy_orientation\":\"HORIZONTAL\",\"cy_colorScheme\":\"CYAN_WHITE_YELLOW\","
				+ "\"cy_colors\":[\"#FFFF00\",\"#FFFFFF\",\"#00FFFF\",\"#808080\"],"
				+ "\"cy_dataColumns\":[\"gal1RGexp\",\"gal4RGexp\",\"gal80Rexp\"],\"cy_showDomainAxis\":false}";
		CustomGraphics cg = CustomGraphics.createFromCX1Value(s);
		assertEquals("org.cytoscape.HeatMapChart", cg.getFullName());
		assertEquals(7, cg.getProperties().size());
		assertEquals("gal4RGexp", ((List)cg.getProperties().get("cy_dataColumns")).get(1));
		assertEquals("chart", cg.getType());
		assertEquals(3.126,((List)cg.getProperties().get("cy_range")).get(1));
		assertEquals("HORIZONTAL", cg.getProperties().get("cy_orientation"));
		assertEquals("CYAN_WHITE_YELLOW", cg.getProperties().get("cy_colorScheme"));
		assertEquals("#FFFFFF", ((List)cg.getProperties().get("cy_colors")).get(1));
		assertEquals(4, ((List)cg.getProperties().get("cy_colors")).size());
		assertEquals(false, cg.getProperties().get("cy_showDomainAxis"));
		
		
		String s1 = cg.toCX1String();
		
		CustomGraphics cg1 = CustomGraphics.createFromCX1Value(s1);
		assertEquals (cg1, cg);
		
        String s2 = om.writeValueAsString(cg);	
        System.out.println(s2);
        
        CustomGraphics cg2 = om.readValue(s2, CustomGraphics.class);
        assertEquals (cg2, cg);
        
        String s3 = cg2.toCX1String();
        
        CustomGraphics cg3 = CustomGraphics.createFromCX1Value(s3);
        assertEquals (cg3, cg);
        

	}

	
	@Test
	void test2() throws NdexException, JsonMappingException, JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper();
		String s = "org.cytoscape.ding.customgraphics.bitmap.URLImageCustomGraphics,2,file:/Users/jingchen/CytoscapeConfiguration/images3/25.png,bitmap image";
		CustomGraphics cg = CustomGraphics.createFromCX1Value(s);
		assertEquals("org.cytoscape.ding.customgraphics.bitmap.URLImageCustomGraphics", cg.getFullName());
	    assertEquals(3, cg.getProperties().size());
		assertEquals("image", cg.getType());
		assertEquals("file:/Users/jingchen/CytoscapeConfiguration/images3/25.png", cg.getProperties().get("url"));
		assertEquals("bitmap image", cg.getProperties().get("tag"));
		assertEquals(2L, cg.getProperties().get("id"));
		
		String s1 = cg.toCX1String();
		assertEquals(s, s1);
		
		CustomGraphics cg1 = CustomGraphics.createFromCX1Value(s1);
		assertEquals (cg1, cg);
		
        String s2 = om.writeValueAsString(cg);	
        System.out.println(s2);
        
        CustomGraphics cg2 = om.readValue(s2, CustomGraphics.class);
        assertEquals (cg2, cg);
        
        String s3 = cg2.toCX1String();
        assertEquals(s, s3);
        
        CustomGraphics cg3 = CustomGraphics.createFromCX1Value(s3);
        assertEquals (cg3, cg);
        

	}

}
