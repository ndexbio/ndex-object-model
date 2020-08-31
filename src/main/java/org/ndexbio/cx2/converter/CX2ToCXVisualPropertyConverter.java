package org.ndexbio.cx2.converter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CX2ToCXVisualPropertyConverter {
	
	
	private Map<String, Map.Entry<String,CX2ToCXVisualPropertyCvtFunction>> networkCvtTable; 
	private Map<String, Map.Entry<String,CX2ToCXVisualPropertyCvtFunction>> nodeEdgeCvtTable; 
	
	/* converter for numeric and boolean values */
	private static final CX2ToCXVisualPropertyCvtFunction defaultCvtr = 
			 (value) -> value.toString(); 

	private static final CX2ToCXVisualPropertyCvtFunction opacityCvtr =
			(opacityVal ) -> Double.toString(((Number)opacityVal).doubleValue()*255.0);
			
	private static final CX2ToCXVisualPropertyCvtFunction nodeBorderTypeCvtr = (cytoscapeLineType) ->
	{
		switch ( cytoscapeLineType.toString() ) { 
		case "solid":
			return "SOLID";
		case "dotted":
			return "DOT";
		case "double":
			return "PARALLEL_LINES";
		default: 
			return "EQUAL_DASH";
		} };
		
		
		private static final CX2ToCXVisualPropertyCvtFunction edgeLineTypeCvtr = (cytoscapeLineType) ->
		{
			
			switch ( cytoscapeLineType.toString() ) { 
			case "solid":
				return "SOLID";
			case "dotted":
				return "DOT";
			default: 
				return "EQUAL_DASH";
			} 
		};
			
	
		private static final CX2ToCXVisualPropertyCvtFunction arrowShapeCvtr = (cytoscapeArrowShape) ->
		{
			switch ( cytoscapeArrowShape.toString() ) { 
			case "none":
				return "NONE";					
			case "circle CIRCLE":
				return "CIRCLE";
			case "triangle-cross":
				return "CROSS_DELTA";
			case "diamond":	
				return "DIAMOND";
			case "square":
				return "SQUARE";
			case "tee":
				return "T";
			default:
				return "ARROW";	
				
			} };						

		
	
	private CX2ToCXVisualPropertyConverter () {
    	networkCvtTable = new HashMap<>(100);
    	nodeEdgeCvtTable = new HashMap<>(100);
    	    	
    	
    	// visual properties not added to these tables will be ignored.
    	
       	//Network attributes:
    	
    	addEntryToCvterTable( networkCvtTable, "NETWORK_BACKGROUND_COLOR","NETWORK_BACKGROUND_PAINT",defaultCvtr);

    	// nodes
    	addEntry ( "NODE_BORDER_PAINT");
    	addEntry ("NODE_BORDER_LINE_TYPE", "NODE_BORDER_STROKE", nodeBorderTypeCvtr);
    	addEntry ( "NODE_BORDER_TRANSPARENCY", opacityCvtr );
    	addEntry ( "NODE_BORDER_WIDTH");

    	addEntry ( "NODE_BACKGROUND_COLOR", "NODE_FILL_COLOR");
    	addEntry ( "NODE_HEIGHT");
    	addEntry ( "NODE_LABEL" );
    	addEntry ( "NODE_LABEL_COLOR"    );
    	addEntry ( "NODE_LABEL_FONT_FACE");
    	addEntry ( "NODE_LABEL_FONT_SIZE" );
    	
    	//TODO: implementing the mapping function.
    	addEntry ( "NODE_LABEL_POSITION" );
    	addEntry ( "NODE_LABEL_TRANSPARENCY", opacityCvtr );
    	
       	addEntry ( "NODE_LABEL_WIDTH", "NODE_LABEL_MAX_WIDTH" );
    	addEntry ( "NODE_SELECTED" );
    	addEntry ( "NODE_SELECTED_PAINT" );
   	
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
    	addEntry ( "NODE_WIDTH");
    	addEntry ( "NODE_SIZE");
    	addEntry ( "NODE_BACKGROUND_OPACITY", "NODE_TRANSPARENCY",    opacityCvtr );
    	addEntry ( "NODE_VISIBLE");
    	    	
    	// edges
    	
    	addEntry ( "EDGE_LABEL");
    	addEntry ( "EDGE_LABEL_COLOR"    );
    	addEntry ( "EDGE_LABEL_FONT_FACE");
    	addEntry ( "EDGE_LABEL_FONT_SIZE" );
    	addEntry ( "EDGE_LABEL_TRANSPARENCY", opacityCvtr );
    	addEntry ( "EDGE_LABEL_MAX_WIDTH","EDGE_LABEL_WIDTH");
    	addEntry ( "EDGE_LINE_TYPE", edgeLineTypeCvtr );
    	addEntry ( "EDGE_SOURCE_ARROW_SHAPE", arrowShapeCvtr );
    	addEntry ( "EDGE_SOURCE_ARROW_SIZE" );
    	addEntry ( "EDGE_TARGET_ARROW_SHAPE", arrowShapeCvtr );
    	addEntry ( "EDGE_TARGET_ARROW_SIZE");
    	
    	
    	addEntry ( "EDGE_LINE_COLOR", "EDGE_STROKE_UNSELECTED_PAINT");
    	addEntry ( "EDGE_SOURCE_ARROW_COLOR", "EDGE_SOURCE_ARROW_UNSELECTED_PAINT");
    	addEntry ( "EDGE_TARGET_ARROW_COLOR", "EDGE_TARGET_ARROW_UNSELECTED_PAINT");
    	addEntry ( "EDGE_OPACITY", "EDGE_TRANSPARENCY", opacityCvtr );
    	addEntry ( "EDGE_WIDTH" );
    	addEntry ( "EDGE_PAINT");
    	addEntry ( "EDGE_VISIBLE");
    	
    	
    	for ( String n : CXToCX2VisualPropertyConverter.cx1CarryOverVPNames) {
    		addEntry ( n);
    	}
 
    	
    }
    
	private static final CX2ToCXVisualPropertyConverter instance = new CX2ToCXVisualPropertyConverter();

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

	
	private void addEntry ( String cx2VP, CX2ToCXVisualPropertyCvtFunction cvt) { 
		AbstractMap.Entry<String, CX2ToCXVisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(cx2VP, cvt);
		
		nodeEdgeCvtTable.put(cx2VP, cvtrEntry );
	}

	// use the default converter, and visual property names are the same in CX and CX2
		private void addEntry ( String vp) { 
			AbstractMap.Entry<String, CX2ToCXVisualPropertyCvtFunction> cvtrEntry= 
					new AbstractMap.SimpleImmutableEntry<>(vp, defaultCvtr);
			
			nodeEdgeCvtTable.put(vp, cvtrEntry );
		}
	
	private static SortedMap<String,String> convertNetworkProperties(Map<String, Map.Entry<String,CX2ToCXVisualPropertyCvtFunction>>  table,
				Map<String,Object> cx1Properties) {
		SortedMap<String,String> result = new TreeMap<>();
		
		for (Map.Entry<String, Object> e : cx1Properties.entrySet()) {
			Map.Entry<String,CX2ToCXVisualPropertyCvtFunction> cvtrEntry = table.get(e.getKey());
			if ( cvtrEntry != null) {
				String newValue = cvtrEntry.getValue().convert(e.getValue());
				result.put(cvtrEntry.getKey(), newValue);
			}
		}
		
		return result;
	}
	
	public SortedMap<String,String>  convertNetworkVPs (Map<String,Object> cx1Properties ) {
		return convertNetworkProperties(networkCvtTable, cx1Properties);
	}
    
	
	public SortedMap<String,String>  convertEdgeOrNodeVPs (Map<String,Object> cx1Properties ) {
		return convertNetworkProperties(nodeEdgeCvtTable, cx1Properties);

	}
	
	public String getCx1EdgeOrNodeProperty (String cx2VPName) {
		Map.Entry<String,CX2ToCXVisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(cx2VPName);
		if ( cvtr != null)
			return cvtr.getKey();
		return null;
	}
	
	public String getCx1EdgeOrNodePropertyValue (String cx2VPName, Object oldValue) {
		Map.Entry<String,CX2ToCXVisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(cx2VPName);
		if ( cvtr != null)
			return cvtr.getValue().convert(oldValue);
		return null;
	}
	
	public static CX2ToCXVisualPropertyConverter getInstance() { return instance;}
	
}
