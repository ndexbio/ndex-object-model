package org.ndexbio.cx2.aspect.element.core;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultVisualPropertiesTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {
		String s0 = "{ \"edge\":{\"EDGE_TARGET_ARROW_COLOR\": \"#000000\",\n" + 
				"                    \"EDGE_WIDTH\": 2.0\n" + 
				"                },\n" + 
				"                \"network\": {\"NETWORK_BACKGROUND_COLOR\": \"#FFFFFF\"\n" + 
				"                },\n" + 
				"                \"node\": {\n" + 
				"                    \"NODE_BACKGROUND_COLOR\": \"#89D0F5\",\n" + 
				"                    \"NODE_BORDER_PAINT\": \"#CCCCCC\",\n" + 
				"                    \"NODE_BORDER_WIDTH\": 0.0,\n" + 
				"                    \"NODE_HEIGHT\": 35.0,\n" + 
				"                    \"NODE_LABEL_COLOR\": \"#000000\",\n" + 
				"                    \"NODE_LABEL_FONT_SIZE\": 12,\n" + 
				"                    \"NODE_SHAPE\": \"ellipse\",\n" + 
				"                    \"NODE_WIDTH\": 35.0\n" + 
				"                } }";
		ObjectMapper om = new ObjectMapper();
		
		DefaultVisualProperties d = om.readValue(s0, DefaultVisualProperties.class);
		
		assertEquals("#000000",d.getEdgeProperties().get("EDGE_TARGET_ARROW_COLOR"));
		assertEquals(2.0,d.getEdgeProperties().get("EDGE_WIDTH"));
		
		assertEquals("#FFFFFF", d.getNetworkProperties().get("NETWORK_BACKGROUND_COLOR"));
		
		assertEquals("ellipse", d.getNodeProperties().get("NODE_SHAPE"));
		assertEquals(35.0, d.getNodeProperties().get("NODE_WIDTH"));
	}

}
