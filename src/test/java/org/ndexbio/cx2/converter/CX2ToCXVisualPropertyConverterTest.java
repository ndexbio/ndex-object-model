package org.ndexbio.cx2.converter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import org.junit.jupiter.api.Test;
import org.ndexbio.cx2.aspect.element.core.CustomGraphics;
import org.ndexbio.cx2.aspect.element.core.FontFace;
import org.ndexbio.cx2.aspect.element.core.GraphicsPosition;
import org.ndexbio.cx2.aspect.element.core.VisualPropertyTable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CX2ToCXVisualPropertyConverterTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {

		CX2ToCXVisualPropertyConverter converter = 
				CX2ToCXVisualPropertyConverter.getInstance();
		
		Map<String,Object> networkVPs = new HashMap<>();
		networkVPs.put("NETWORK_BACKGROUND_COLOR", "#FFFFFF");
		
		SortedMap<String, String> cx1NetVPs = converter.convertNetworkVPs(networkVPs);
	
		assertEquals(1, cx1NetVPs.size());
		assertEquals("#FFFFFF", cx1NetVPs.get("NETWORK_BACKGROUND_PAINT"));
		
		VisualPropertyTable nodeVPTable = new VisualPropertyTable();
		
		Map<String,Object> nodeVPs = nodeVPTable.getVisualProperties();
		
		nodeVPs.put("NODE_SHAPE", "ellipse");
		nodeVPs.put("NODE_BACKGROUND_COLOR", "#00EEFF");
		nodeVPs.put("NODE_WIDTH",20.2);
		nodeVPs.put("NODE_HEIGHT", 29.3);
		nodeVPs.put("NODE_BACKGROUND_OPACITY", 0.8);
		nodeVPs.put("NODE_VISIBILITY", "element");
		nodeVPs.put("NODE_BORDER_COLOR", "#CCCCCC");
		nodeVPs.put("NODE_BORDER_STYLE", "dotted");
		nodeVPs.put("NODE_BORDER_WIDTH", 2.0);
		nodeVPs.put("NODE_BORDER_OPACITY", 0.8);
		nodeVPs.put("NODE_LABEL_OPACITY", 0.8);
		
		FontFace font1 = new FontFace();
		font1.setFamily(FontFace.PORTABLE_SANS_SERIF_FONT);
		nodeVPs.put("NODE_LABEL_FONT_FACE", font1);
		nodeVPs.put("NODE_LABEL_FONT_SIZE", 12);
		
		//edge properties
		nodeVPs.put("EDGE_WIDTH", 3.0);
		nodeVPs.put("EDGE_LINE_COLOR", "#999999");
		nodeVPs.put("EDGE_OPACITY", 0.8);
		nodeVPs.put("EDGE_LINE_STYLE", "dashed");
		nodeVPs.put("EDGE_SOURCE_ARROW_SHAPE", "none");
		nodeVPs.put("EDGE_TARGET_ARROW_SHAPE", "diamond");
		nodeVPs.put("EDGE_SOURCE_ARROW_COLOR", "#989898");
		nodeVPs.put("EDGE_TARGET_ARROW_COLOR", "#EEAA88");
		nodeVPs.put("EDGE_SOURCE_ARROW_SIZE", 6.0);
		nodeVPs.put("EDGE_TARGET_ARROW_SIZE", 3.0);
		nodeVPs.put("EDGE_VISIBILITY", "element");
		nodeVPs.put("EDGE_LABEL_OPACITY",0.8);
		
		
		String jsonStr = " {\"type\":\"chart\",\"name\":\"org.cytoscape.BarChart\","
				+ "\"properties\":{\"cy_range\":[-1.232,1.022],\"cy_showRangeAxis\":true,\"cy_axisLabelFontSize\":2,"
				+ "\"cy_showRangeZeroBaseline\":true,\"cy_colorScheme\":\"Set3 colors\","
				+ "\"cy_colors\":[\"#8DD3C7\",\"#FFFFB3\",\"#BEBADA\"],\"cy_itemLabelFontSize\":4,\"cy_showDomainAxis\":true,"
				+ "\"cy_axisColor\":\"#238443\",\"cy_axisWidth\":0.5,\"cy_autoRange\":true,\"cy_borderColor\":\"#CB181D\","
				+ "\"cy_borderWidth\":0.5,\"cy_dataColumns\":[\"gal1RGexp\",\"gal4RGexp\",\"gal80Rexp\"],"
				+ "\"cy_domainLabelPosition\":\"DOWN_45\",\"cy_showItemLabels\":true}}";
		ObjectMapper mapper = new ObjectMapper();
		CustomGraphics c = mapper.readValue(jsonStr, CustomGraphics.class);
		nodeVPs.put("NODE_CUSTOMGRAPHICS_1", c);
		
		nodeVPs.put("NODE_CUSTOMGRAPHICS_SIZE_1", 23.1);
		
		GraphicsPosition p1 = mapper.readValue("{\"MARGIN_X\":1.5,\"JUSTIFICATION\":\"left\",\"GRAPHICS_ANCHOR\":\"NW\",\"ENTITY_ANCHOR\":\"SE\",\"MARGIN_Y\":3.2}\n"
				, GraphicsPosition.class);
		nodeVPs.put("NODE_CUSTOMGRAPHICS_POSITION_9", p1);
		
		
		//convert visual properties to CX1 format.
		
		SortedMap<String, String> cxnodeVPs = converter.convertEdgeOrNodeVPs(nodeVPTable);
		
		//nodes
				
		assertEquals("#00EEFF", cxnodeVPs.get("NODE_FILL_COLOR"));
		assertEquals("20.2", cxnodeVPs.get("NODE_WIDTH"));
		assertEquals("29.3", cxnodeVPs.get("NODE_HEIGHT"));
		assertEquals("204", cxnodeVPs.get("NODE_TRANSPARENCY"));
		assertEquals("true", cxnodeVPs.get("NODE_VISIBLE"));
		assertEquals("#CCCCCC", cxnodeVPs.get("NODE_BORDER_PAINT"));
		assertEquals("DOT", cxnodeVPs.get("NODE_BORDER_STROKE"));
		assertEquals("2.0", cxnodeVPs.get("NODE_BORDER_WIDTH"));
		assertEquals("204", cxnodeVPs.get("NODE_BORDER_TRANSPARENCY"));
		assertEquals(FontFace.PORTABLE_SANS_SERIF_FONT + ",plain,12", cxnodeVPs.get("NODE_LABEL_FONT_FACE"));
		assertEquals("12", cxnodeVPs.get("NODE_LABEL_FONT_SIZE"));
		assertEquals("204", cxnodeVPs.get("NODE_LABEL_TRANSPARENCY"));
		assertTrue(cxnodeVPs.get("NODE_CUSTOMGRAPHICS_1").startsWith("org.cytoscape.BarChart:"));
		String s0 = "org.cytoscape.BarChart:{\"cy_range\":[-1.232,1.022],\"cy_showRangeAxis\":true,\"cy_axisLabelFontSize\":2,\"cy_showRangeZeroBaseline\":true,\"cy_colorScheme\":\"Set3 colors\",\"cy_colors\":[\"#8DD3C7\",\"#FFFFB3\",\"#BEBADA\"],\"cy_itemLabelFontSize\":4,\"cy_showDomainAxis\":true,\"cy_axisColor\":\"#238443\",\"cy_axisWidth\":0.5,\"cy_autoRange\":true,\"cy_borderColor\":\"#CB181D\",\"cy_borderWidth\":0.5,\"cy_dataColumns\":[\"gal1RGexp\",\"gal4RGexp\",\"gal80Rexp\"],\"cy_domainLabelPosition\":\"DOWN_45\",\"cy_showItemLabels\":true}";
		assertEquals(s0, cxnodeVPs.get("NODE_CUSTOMGRAPHICS_1"));
		assertEquals("23.1", cxnodeVPs.get("NODE_CUSTOMGRAPHICS_SIZE_1"));
		assertEquals("SE,NW,l,1.5,3.2", cxnodeVPs.get("NODE_CUSTOMGRAPHICS_POSITION_9"));

		//edges
		assertEquals("3.0", cxnodeVPs.get("EDGE_WIDTH"));
		assertEquals("#999999", cxnodeVPs.get("EDGE_STROKE_UNSELECTED_PAINT"));
		assertEquals("204", cxnodeVPs.get("EDGE_TRANSPARENCY"));
		assertEquals("LONG_DASH", cxnodeVPs.get("EDGE_LINE_TYPE"));
		assertEquals("NONE", cxnodeVPs.get("EDGE_SOURCE_ARROW_SHAPE"));
		assertEquals("DIAMOND", cxnodeVPs.get("EDGE_TARGET_ARROW_SHAPE"));
		assertEquals("#989898", cxnodeVPs.get("EDGE_SOURCE_ARROW_UNSELECTED_PAINT"));
		assertEquals("#EEAA88", cxnodeVPs.get("EDGE_TARGET_ARROW_UNSELECTED_PAINT"));
		assertEquals("6.0", cxnodeVPs.get("EDGE_SOURCE_ARROW_SIZE"));
		assertEquals("3.0", cxnodeVPs.get("EDGE_TARGET_ARROW_SIZE"));
		assertEquals("true", cxnodeVPs.get("EDGE_VISIBLE"));
		assertEquals("204", cxnodeVPs.get("EDGE_LABEL_TRANSPARENCY"));
		

	}
	
	@Test
	public void EdgeArrowShapeConverterTest() {
		String vpName = "EDGE_SOURCE_ARROW_SHAPE";
		Map<String,Object> edgeArrowShapeMap = new HashMap<>();
		edgeArrowShapeMap.put("none", "NONE");
		edgeArrowShapeMap.put("triangle", "DELTA");
		edgeArrowShapeMap.put("circle", "CIRCLE");
		edgeArrowShapeMap.put("diamond", "DIAMOND");
		edgeArrowShapeMap.put("square", "SQUARE");
		edgeArrowShapeMap.put("arrow", "ARROW");
		edgeArrowShapeMap.put("tee", "T");
		edgeArrowShapeMap.put("triangle-cross", "CROSS_DELTA");
		edgeArrowShapeMap.put("cross_open_delta", "CROSS_OPEN_DELTA");
		edgeArrowShapeMap.put("open_circle", "OPEN_CIRCLE");
		edgeArrowShapeMap.put("open_delta", "OPEN_DELTA");
		edgeArrowShapeMap.put("open_square", "OPEN_SQUARE");
		edgeArrowShapeMap.put("open_diamond", "OPEN_DIAMOND");

		edgeArrowShapeMap.forEach((k, v) -> {
			assertEquals(v, CX2ToCXVisualPropertyConverter.getInstance().getCx1EdgeOrNodePropertyValue(vpName,k));
		});
	}

}
