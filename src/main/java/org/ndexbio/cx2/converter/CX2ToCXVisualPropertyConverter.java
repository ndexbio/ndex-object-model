package org.ndexbio.cx2.converter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class CX2ToCXVisualPropertyConverter {
	
	private Map<String, Map.Entry<String,CX2ToCXVisualPropertyCvtFunction>> networkCvtTable; 
	private Map<String, Map.Entry<String,CX2ToCXVisualPropertyCvtFunction>> nodeEdgeCvtTable; 
	
	/* converter for numeric and boolean values */
	private static final CX2ToCXVisualPropertyCvtFunction defaultCvtr = 
			 (value) -> value.toString(); 

	private static final CX2ToCXVisualPropertyCvtFunction opacityCvter =
			(opacityVal ) -> Double.toString(((Number)opacityVal).doubleValue()*255.0);
	
	public CX2ToCXVisualPropertyConverter () {
    	networkCvtTable = new HashMap<>(100);
    	nodeEdgeCvtTable = new HashMap<>(100);
    	    	
    	
    	// visual properties not added to these tables will be ignored.
    	
       	//Network attributes:
    	
    	addEntryToCvterTable( networkCvtTable, "NETWORK_BACKGROUND_COLOR","NETWORK_BACKGROUND_PAINT",defaultCvtr);

    	// nodes
    	addEntry ( "NODE_BACKGROUND_COLOR", "NODE_FILL_COLOR");
    	addEntry ( "NODE_LABEL" );
    	addEntry ( "NODE_SHAPE",      "NODE_SHAPE", 
    				(nodeShape) -> {
    					String nodeShapeStr = (String)nodeShape; 
    					switch (nodeShapeStr ) { 
    				case "ellipse":
    				case "triangle":
    				case "rectangle":
    				case "parallelogram":
    				case "diamond":	
    				case "hexagon":
    				case "octagon":	
    				case "vee":	
    					return nodeShapeStr.toUpperCase();
    				case "round-rectangle":	
    					return "ROUND_RECTANGLE";
    				default: 
    					return nodeShapeStr;
    				} }
    			);
    	addEntry ( "NODE_LABEL_COLOR");
    	addEntry ( "NODE_LABEL_FONT_SIZE");
    	addEntry ( "NODE_WIDTH");
    	addEntry ( "NODE_HEIGHT");
    	addEntry ( "NODE_BACKGROUND_OPACITY", "NODE_OPACITY",    opacityCvter );
    	addEntry ( "NODE_BORDER_PAINT");
    	addEntry ( "NODE_BORDER_WIDTH");
    	
    	// edges
    	//addEntry ( "EDGE_PAINT", "EDGE_LINE_COLOR", stringCvtr);
    	addEntry ( "EDGE_LINE_COLOR", "EDGE_STROKE_UNSELECTED_PAINT",  defaultCvtr);
    	addEntry (  "EDGE_SOURCE_ARROW_COLOR", "EDGE_SOURCE_ARROW_UNSELECTED_PAINT");
    	addEntry ( "EDGE_TARGET_ARROW_COLOR", "EDGE_TARGET_ARROW_UNSELECTED_PAINT");
    	addEntry ( "EDGE_LABEL");
    	addEntry ( "EDGE_OPACITY", "EDGE_OPACITY", opacityCvter );
    	addEntry ( "EDGE_WIDTH" );
 
    	
    }
    
	private static void addEntryToCvterTable (Map<String, Map.Entry<String, CX2ToCXVisualPropertyCvtFunction>> table, String oldVP, String newVP,
			CX2ToCXVisualPropertyCvtFunction cvt) { 
		AbstractMap.Entry<String, CX2ToCXVisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(newVP, cvt);
		
		table.put(oldVP, cvtrEntry );
	}
    
	// adding an entry to the nodeEdge converter table
	private void addEntry ( String cx2VP, String cxVP, CX2ToCXVisualPropertyCvtFunction cvt) { 
		AbstractMap.Entry<String, CX2ToCXVisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(cxVP, cvt);
		
		nodeEdgeCvtTable.put(cx2VP, cvtrEntry );
	}
	
	// use the default converter, which is a simple toString()
	private void addEntry ( String cx2VP, String cxVP) { 
		AbstractMap.Entry<String, CX2ToCXVisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(cxVP, defaultCvtr);
		
		nodeEdgeCvtTable.put(cx2VP, cvtrEntry );
	}
	
	// use the default converter, and visual property names are the same in CX and CX2
		private void addEntry ( String vp) { 
			AbstractMap.Entry<String, CX2ToCXVisualPropertyCvtFunction> cvtrEntry= 
					new AbstractMap.SimpleImmutableEntry<>(vp, defaultCvtr);
			
			nodeEdgeCvtTable.put(vp, cvtrEntry );
		}
	
	private static Map<String,String> convertNetworkProperties(Map<String, Map.Entry<String,CX2ToCXVisualPropertyCvtFunction>>  table,
				Map<String,Object> cx1Properties) {
		Map<String,String> result = new HashMap<>();
		
		for (Map.Entry<String, Object> e : cx1Properties.entrySet()) {
			Map.Entry<String,CX2ToCXVisualPropertyCvtFunction> cvtrEntry = table.get(e.getKey());
			if ( cvtrEntry != null) {
				String newValue = cvtrEntry.getValue().convert(e.getValue());
				result.put(cvtrEntry.getKey(), newValue);
			}
		}
		
		return result;
	}
	
	public Map<String,String>  convertNetworkVPs (Map<String,Object> cx1Properties ) {
		return convertNetworkProperties(networkCvtTable, cx1Properties);
	}
    
	
	public Map<String,String>  convertEdgeOrNodeVPs (Map<String,Object> cx1Properties ) {
		return convertNetworkProperties(nodeEdgeCvtTable, cx1Properties);

	}
	
	public String getNewEdgeOrNodeProperty (String oldPropertyName) {
		Map.Entry<String,CX2ToCXVisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(oldPropertyName);
		if ( cvtr != null)
			return cvtr.getKey();
		return null;
	}
	
	public Object getNewEdgeOrNodePropertyValue (String oldPropertyName, String oldValue) {
		Map.Entry<String,CX2ToCXVisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(oldPropertyName);
		if ( cvtr != null)
			return cvtr.getValue().convert(oldValue);
		return null;
	}
	
}
