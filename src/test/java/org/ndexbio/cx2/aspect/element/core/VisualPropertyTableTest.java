package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VisualPropertyTableTest {

	@Test
	public void test1() throws JsonMappingException, JsonProcessingException {
		String s = " {\"EDGE_LABEL_COLOR\": \"#000000\",\n"
				+ "                        \"EDGE_LABEL_FONT_FACE\": {\n"
				+ "                            \"FONT_FAMILY\": \"sans-serif\",\n"
				+ "                            \"FONT_STYLE\": \"normal\",\n"
				+ "                            \"FONT_WEIGHT\": \"bold\"\n"
				+ "                        } }";
		
		ObjectMapper om = new ObjectMapper();
		VisualPropertyTable t = om.readValue(s, VisualPropertyTable.class);
		
		assertEquals (FontFace.class.getCanonicalName(),
				t.getVisualProperties().get("EDGE_LABEL_FONT_FACE").getClass().getCanonicalName());
		String s2 = om.writeValueAsString(t);
		
		System.out.println ( s2);
		
	    assertEquals ("{\"EDGE_LABEL_COLOR\":\"#000000\",\"EDGE_LABEL_FONT_FACE\":{\"FONT_FAMILY\":\"sans-serif\",\"FONT_STYLE\":\"normal\",\"FONT_WEIGHT\":\"bold\"}}",
	    		s2);
	}

	
	@Test
	public void test2() throws JsonMappingException, JsonProcessingException {
		String s = "{\"NODE_IMAGE_1\": \"org.cytoscape.ding.customgraphics.bitmap.URLImageCustomGraphics,42,/Users/ppm/Documents/Useful/cytoscapesymbols/180px-Symbol_version_newer.png,bitmap image\",\n"
				+ "                        \"NODE_IMAGE_1_POSITION\": {\n"
				+ "                            \"HORIZONTAL_ALIGN\": \"left\",\n"
				+ "                            \"HORIZONTAL_ANCHOR\": \"right\",\n"
				+ "                            \"MARGIN_X\": 0.0,\n"
				+ "                            \"MARGIN_Y\": 0.0,\n"
				+ "                            \"VERTICAL_ALIGN\": \"center\",\n"
				+ "                            \"VERTICAL_ANCHOR\": \"center\"\n"
				+ "                        },\n"
				+ "                        \"NODE_IMAGE_1_SIZE\": {\n"
				+ "                            \"HEIGHT\": 50.0,\n"
				+ "                            \"WIDTH\": 50.0\n"
				+ "                        },"
				+ "\"NODE_LABEL_POSITION\": {\n"
				+ "                            \"HORIZONTAL_ALIGN\": \"center\",\n"
				+ "                            \"HORIZONTAL_ANCHOR\": \"center\",\n"
				+ "                            \"JUSTIFICATION\": \"center\",\n"
				+ "                            \"MARGIN_X\": 0.0,\n"
				+ "                            \"MARGIN_Y\": 0.0,\n"
				+ "                            \"VERTICAL_ALIGN\": \"center\",\n"
				+ "                            \"VERTICAL_ANCHOR\": \"center\"\n"
				+ "                        }}";
		
		ObjectMapper om = new ObjectMapper();
		VisualPropertyTable t = om.readValue(s, VisualPropertyTable.class);
		
		assertEquals (ObjectPosition.class.getCanonicalName(),
				t.getVisualProperties().get("NODE_IMAGE_1_POSITION").getClass().getCanonicalName());
		
		assertEquals (LabelPosition.class.getCanonicalName(),
				t.getVisualProperties().get("NODE_LABEL_POSITION").getClass().getCanonicalName());
		
		assertEquals(4, t.getVisualProperties().size());
		String s2 = om.writeValueAsString(t);
		
		System.out.println ( s2);
		
	  //  assertEquals ("{\"EDGE_LABEL_COLOR\":\"#000000\",\"EDGE_LABEL_FONT_FACE\":{\"FONT_FAMILY\":\"sans-serif\",\"FONT_STYLE\":\"normal\",\"FONT_WEIGHT\":\"bold\"}}",
	  //  		s2);
	}

	
	@Test
	public void test3() throws JsonMappingException, JsonProcessingException {
		String s = " {\"EDGE_CONTROL_POINTS\": [{\"cos\": 0.4767872530254065,\n"
				+ "                                \"ratio\": 0.3135770853210523,\n"
				+ "                                \"sin\": 0.8790187229817616\n"
				+ "                            },\n"
				+ "                            {\n"
				+ "                                \"cos\": -0.21261436217353974,\n"
				+ "                                \"ratio\": 0.7000506537473652,\n"
				+ "                                \"sin\": 0.9771361895854328\n"
				+ "                            }\n"
				+ "                        ],\n"
				+ "                        \"EDGE_CURVED\": true,\n"
				+ "                        \"EDGE_LABEL_COLOR\": \"#000000\"}";
		
		ObjectMapper om = new ObjectMapper();
		VisualPropertyTable t = om.readValue(s, VisualPropertyTable.class);
		
		List<EdgeControlPoint> points = (List<EdgeControlPoint>)t.getVisualProperties().get("EDGE_CONTROL_POINTS");
		assertEquals (0.4767872530254065, points.get(0).getCos(), 0.00000001);
		assertEquals(3, t.getVisualProperties().size());

		
		String s2 = om.writeValueAsString(t);
		
		System.out.println ( s2);
		
	    assertEquals ("{\"EDGE_LABEL_COLOR\":\"#000000\",\"EDGE_CURVED\":true,\"EDGE_CONTROL_POINTS\":[{\"sin\":0.8790187229817616,\"cos\":0.4767872530254065,\"ratio\":0.3135770853210523},{\"sin\":0.9771361895854328,\"cos\":-0.21261436217353974,\"ratio\":0.7000506537473652}]}",
	    		s2);
	}


}
