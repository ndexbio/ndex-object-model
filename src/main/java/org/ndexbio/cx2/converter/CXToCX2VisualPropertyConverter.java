package org.ndexbio.cx2.converter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class CXToCX2VisualPropertyConverter {
	
	private Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>> networkCvtTable; 
	private Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>> nodeEdgeCvtTable; 
	
	private static final CXToCX2VisualPropertyCvtFunction numberCvtr = 
			 (strVal) -> Double.valueOf(strVal); 

	private static final CXToCX2VisualPropertyCvtFunction intCvtr = 
					 (strVal) -> Integer.valueOf(strVal); 

	private static final CXToCX2VisualPropertyCvtFunction opacityCvter =
			(strVal ) -> Double.valueOf(Double.valueOf(strVal).doubleValue()/255.0);
	
	private static final CXToCX2VisualPropertyCvtFunction booleanCvtr = 
			(strVal)  -> Boolean.valueOf(strVal);

	private static final CXToCX2VisualPropertyCvtFunction stringCvtr = (strVal) -> strVal;
    
	
	public CXToCX2VisualPropertyConverter () {
    	networkCvtTable = new HashMap<>(100);
    	nodeEdgeCvtTable = new HashMap<>(100);
    	    	
    	
    	// visual properties not added to these tables will be ignored.
    	
       	//Network attributes:
    	
    	addEntryToCvterTable( networkCvtTable, "NETWORK_BACKGROUND_PAINT","NETWORK_BACKGROUND_COLOR", stringCvtr);

    	// nodes
    	addEntry ( "NODE_FILL_COLOR", "NODE_BACKGROUND_COLOR", stringCvtr );
    	addEntry ( "NODE_LABEL",      "NODE_LABEL", stringCvtr );
    	addEntry ( "NODE_SHAPE",      "NODE_SHAPE", 
    				(nodeShapeStr) -> {
    					switch (nodeShapeStr ) { 
    				case "ELLIPSE":
    				case "TRIANGLE":
    				case "RECTANGLE":
    				case "PARALLELOGRAM":
    				case "DIAMOND":	
    				case "HEXAGON":
    				case "OCTAGON":	
    				case "VEE":	
    					return nodeShapeStr.toLowerCase();
    				case "ROUND_RECTANGLE":	
    					return "round-rectangle";
    				default: 
    					return nodeShapeStr;
    				} }
    			);
    	addEntry ( "NODE_LABEL_COLOR", "NODE_LABEL_COLOR", stringCvtr );
    	addEntry ( "NODE_LABEL_FONT_SIZE", "NODE_LABEL_FONT_SIZE", intCvtr );
    	addEntry ( "NODE_WIDTH",      "NODE_WIDTH", numberCvtr );
    	addEntry ( "NODE_HEIGHT",     "NODE_HEIGHT", numberCvtr	 );
    	addEntry ( "NODE_OPACITY",    "NODE_BACKGROUND_OPACITY", opacityCvter );
    	addEntry ( "NODE_BORDER_PAINT",    "NODE_BORDER_PAINT", stringCvtr );
    	addEntry ( "NODE_BORDER_WIDTH",    "NODE_BORDER_WIDTH", numberCvtr );
    	
    	// edges
    	//addEntry ( "EDGE_PAINT", "EDGE_LINE_COLOR", stringCvtr);
    	addEntry ( "EDGE_STROKE_UNSELECTED_PAINT", "EDGE_LINE_COLOR", stringCvtr);
    	addEntry ( "EDGE_SOURCE_ARROW_UNSELECTED_PAINT", "EDGE_SOURCE_ARROW_COLOR", stringCvtr);
    	addEntry ( "EDGE_TARGET_ARROW_UNSELECTED_PAINT", "EDGE_TARGET_ARROW_COLOR", stringCvtr);
    	addEntry ( "EDGE_LABEL", "EDGE_LABEL", stringCvtr);
    	addEntry ( "EDGE_OPACITY", "EDGE_OPACITY", opacityCvter );
    	addEntry ( "EDGE_WIDTH", "EDGE_WIDTH", numberCvtr );
 
    	
    }
    
	private static void addEntryToCvterTable (Map<String, Map.Entry<String, CXToCX2VisualPropertyCvtFunction>> table, String oldVP, String newVP,
			CXToCX2VisualPropertyCvtFunction cvt) { 
		AbstractMap.Entry<String, CXToCX2VisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(newVP, cvt);
		
		table.put(oldVP, cvtrEntry );
	}
    
	// adding an entry to the nodeEdge converter table
	private void addEntry ( String oldVP, String newVP, CXToCX2VisualPropertyCvtFunction cvt) { 
		AbstractMap.Entry<String, CXToCX2VisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(newVP, cvt);
		
		nodeEdgeCvtTable.put(oldVP, cvtrEntry );
	}
	
	private static Map<String,Object> convertNetworkProperties(Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>>  table,
				Map<String,String> cx1Properties) {
		Map<String,Object> result = new HashMap<>();
		
		for (Map.Entry<String, String> e : cx1Properties.entrySet()) {
			Map.Entry<String,CXToCX2VisualPropertyCvtFunction> cvtrEntry = table.get(e.getKey());
			if ( cvtrEntry != null) {
				Object newValue = cvtrEntry.getValue().convert(e.getValue());
				result.put(cvtrEntry.getKey(), newValue);
			}
		}
		
		return result;
	}
	
	public Map<String,Object>  convertNetworkVPs (Map<String,String> cx1Properties ) {
		return convertNetworkProperties(networkCvtTable, cx1Properties);
	}
    
	
	public Map<String,Object>  convertEdgeOrNodeVPs (Map<String,String> cx1Properties ) {
		return convertNetworkProperties(nodeEdgeCvtTable, cx1Properties);

	}
	
	public String getNewEdgeOrNodeProperty (String oldPropertyName) {
		Map.Entry<String,CXToCX2VisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(oldPropertyName);
		if ( cvtr != null)
			return cvtr.getKey();
		return null;
	}
	
	public Object getNewEdgeOrNodePropertyValue (String oldPropertyName, String oldValue) {
		Map.Entry<String,CXToCX2VisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(oldPropertyName);
		if ( cvtr != null)
			return cvtr.getValue().convert(oldValue);
		return null;
	}
	
}
