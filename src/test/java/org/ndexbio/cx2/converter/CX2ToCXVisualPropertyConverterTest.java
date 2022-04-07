package org.ndexbio.cx2.converter;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.FontFace;
import org.ndexbio.cx2.aspect.element.core.VisualPropertyTable;

public class CX2ToCXVisualPropertyConverterTest {

	@Test
	public void test() {

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
		
		SortedMap<String, String> cxnodeVPs = converter.convertEdgeOrNodeVPs(nodeVPTable);
		
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
		

		//edges
		assertEquals("3.0", cxnodeVPs.get("EDGE_WIDTH"));
		assertEquals("#999999", cxnodeVPs.get("EDGE_STROKE_UNSELECTED_PAINT"));
		assertEquals("204", cxnodeVPs.get("EDGE_TRANSPARENCY"));
		assertEquals("EQUAL_DASH", cxnodeVPs.get("EDGE_LINE_TYPE"));
		assertEquals("NONE", cxnodeVPs.get("EDGE_SOURCE_ARROW_SHAPE"));
		assertEquals("DIAMOND", cxnodeVPs.get("EDGE_TARGET_ARROW_SHAPE"));
		assertEquals("#989898", cxnodeVPs.get("EDGE_SOURCE_ARROW_UNSELECTED_PAINT"));
		assertEquals("#EEAA88", cxnodeVPs.get("EDGE_TARGET_ARROW_UNSELECTED_PAINT"));
		assertEquals("6.0", cxnodeVPs.get("EDGE_SOURCE_ARROW_SIZE"));
		assertEquals("3.0", cxnodeVPs.get("EDGE_TARGET_ARROW_SIZE"));
		assertEquals("true", cxnodeVPs.get("EDGE_VISIBLE"));
		assertEquals("204", cxnodeVPs.get("EDGE_LABEL_TRANSPARENCY"));
		

	}

}
