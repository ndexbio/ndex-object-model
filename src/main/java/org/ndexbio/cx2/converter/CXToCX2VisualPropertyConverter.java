package org.ndexbio.cx2.converter;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.ndexbio.model.exceptions.NdexException;


public class CXToCX2VisualPropertyConverter {
	
	
	
	/* These node or edge visual properties are not part of the cx2 portable styles, we just carry them over to cx2, and it is up to the 
	 * application to decide whether supporting them.
	 */
	protected static final List<String> cx1CarryOverVPNames = Arrays.asList(
			"COMPOUND_NODE_PADDING",
			"COMPOUND_NODE_SHAPE",
			"NODE_CUSTOMGRAPHICS_1",
			"NODE_CUSTOMGRAPHICS_2",
			"NODE_CUSTOMGRAPHICS_3",
			"NODE_CUSTOMGRAPHICS_4",
			"NODE_CUSTOMGRAPHICS_5",
			"NODE_CUSTOMGRAPHICS_6",
			"NODE_CUSTOMGRAPHICS_7",
			"NODE_CUSTOMGRAPHICS_8",
			"NODE_CUSTOMGRAPHICS_9",
			"NODE_TOOLTIP",
			"NODE_X_LOCATION",
			"NODE_Y_LOCATION",
			"NODE_Z_LOCATION",
			
            "EDGE_SELECTED",
            "EDGE_SELECTED_PAINT",
			"EDGE_TOOLTIP"			
			);
		
	
	private Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>> networkCvtTable; 
	private Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>> nodeEdgeCvtTable; 
	
	private static final CXToCX2VisualPropertyCvtFunction numberCvtr = 
			 (strVal) -> Double.valueOf(strVal); 

	private static final CXToCX2VisualPropertyCvtFunction intCvtr = (String strVal) -> {
		try {
			return Integer.valueOf(strVal);

		} catch (NumberFormatException e) {
			try {
				return Integer.valueOf(Double.valueOf(strVal).intValue());
			} catch (NumberFormatException e2) {
				throw new NdexException("Can't convert string " + strVal + " to number.");
			}
		}
	};

	private static final CXToCX2VisualPropertyCvtFunction opacityCvtr =
			(strVal ) -> Double.valueOf(Double.valueOf(strVal).doubleValue()/255.0);
	
	private static final CXToCX2VisualPropertyCvtFunction booleanCvtr = 
			(strVal)  -> Boolean.valueOf(strVal);

	private static final CXToCX2VisualPropertyCvtFunction stringCvtr = (strVal) -> strVal;
	
	private static final CXToCX2VisualPropertyCvtFunction nodeBorderTypeCvtr = (cytoscapeLineType) ->
		{
			switch ( cytoscapeLineType ) { 
			case "SOLID":
			case "SINEWAVE":
			case "CONTIGUOUS_ARROW":
			case "ZIGZAG":
				return "solid";
			case "DASH_DOT":
			case "DOT":
				return "dotted";
			case "PARALLEL_LINES":
				return "double";
			default:
				/* these cytoscape line types are in this case
			case "BACKWARD_SLASH":
			case "LONG_DASH":
			case "EQUAL_DASH":
			case "FORWARD_SLASH":
			case "MARQUEE_DASH":
			case "SEPARATE_ARROW":
			case "VERTICAL_SLASH":
				*/
				return "dashed";
			} };
    
			
			private static final CXToCX2VisualPropertyCvtFunction edgeLineTypeCvtr = (cytoscapeLineType) ->
			{
				switch ( cytoscapeLineType ) { 
				case "SOLID":
				case "SINEWAVE":
				case "CONTIGUOUS_ARROW":
				case "PARALLEL_LINES":
				case "ZIGZAG":
					return "solid";
				case "DASH_DOT":
				case "DOT":
					return "dotted";
				default:
					/* these cytoscape line types are in this case
				case "BACKWARD_SLASH":
				case "LONG_DASH":
				case "EQUAL_DASH":
				case "FORWARD_SLASH":
				case "MARQUEE_DASH":
				case "SEPARATE_ARROW":
				case "VERTICAL_SLASH":
					*/
					return "dashed";
				} };		
				
			private static final CXToCX2VisualPropertyCvtFunction arrowShapeCvtr = (cytoscapeArrowShape) ->
				{
					switch ( cytoscapeArrowShape ) { 
					case "NONE":
						return "none";					
					case "CIRCLE":
					case "OPEN_CIRCLE":
						return "circle";
					case "CROSS_DELTA":
					case "CROSS_OPEN_DELTA":
						return "triangle-cross";
					case "DIAMOND":
					case "DIAMOND_SHORT_1":
					case "DIAMOND_SHORT_2":
					case "OPEN_DIAMOND":	
						return "diamond";
					case "OPEN_SQUARE":
					case "SQUARE":
						return "square";
					case "T":
						return "tee";
					default:
						/* covers these cases
					case "ARROW":
					case "ARROW_SHORT":
					case "DELTA":
					case "DELTA_SHORT_1":
					case "DELTA_SHORT_2":
					case "HALF_BOTTOM":
					case "HALF_CIRCLE":
					case "HALF_TOP":
					case "OPEN_DELTA":
					case "OPEN_HALF_CIRCLE":	*/
						return "triangle";	
						
					} };						
	

	private static final CXToCX2VisualPropertyConverter instance = new CXToCX2VisualPropertyConverter();
			
	private CXToCX2VisualPropertyConverter () {
    	networkCvtTable = new TreeMap<>();
    	nodeEdgeCvtTable = new TreeMap<>();
    	    	
    	
    	// visual properties not added to these tables will be ignored.
    	
       	//Network attributes:
    	
    	addEntryToCvterTable( networkCvtTable, "NETWORK_BACKGROUND_PAINT","NETWORK_BACKGROUND_COLOR", stringCvtr);

    	// these are the visual properties that are in the portable styles.
    	// nodes
    	addEntry ( "NODE_BORDER_PAINT" );
    	addEntry ("NODE_BORDER_STROKE", "NODE_BORDER_LINE_TYPE", nodeBorderTypeCvtr);
    	addEntry ( "NODE_BORDER_TRANSPARENCY", opacityCvtr );
    	addEntry ( "NODE_BORDER_WIDTH", numberCvtr );
    	 	
    	addEntry ( "NODE_FILL_COLOR", "NODE_BACKGROUND_COLOR", stringCvtr );
    	addEntry ( "NODE_HEIGHT",  numberCvtr	 );
    	addEntry ( "NODE_LABEL");
    	addEntry ( "NODE_LABEL_COLOR"    );
    	addEntry ( "NODE_LABEL_FONT_FACE");
    	addEntry ( "NODE_LABEL_FONT_SIZE", intCvtr );
    	
    	//TODO: implementing the mapping function.
    	addEntry ( "NODE_LABEL_POSITION" );
    	addEntry ( "NODE_LABEL_TRANSPARENCY", opacityCvtr );
    	
    	addEntry ( "NODE_LABEL_WIDTH", "NODE_LABEL_MAX_WIDTH",numberCvtr );
    	addEntry ( "NODE_SELECTED", booleanCvtr );
    	addEntry ( "NODE_SELECTED_PAINT" );

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
    	addEntry ( "NODE_WIDTH",      "NODE_WIDTH", numberCvtr );
    	addEntry ( "NODE_TRANSPARENCY",    "NODE_BACKGROUND_OPACITY", opacityCvtr );
    	addEntry ( "NODE_VISIBLE", booleanCvtr );
    	
    	// edges
    	
    	//TODO: handle edge_curved and edge_bend
    	addEntry ( "EDGE_LABEL");
    	addEntry ( "EDGE_LABEL_COLOR"    );
    	addEntry ( "EDGE_LABEL_FONT_FACE");
    	addEntry ( "EDGE_LABEL_FONT_SIZE", intCvtr );
    	addEntry ( "EDGE_LABEL_TRANSPARENCY", opacityCvtr );
    	addEntry ( "EDGE_LABEL_WIDTH","EDGE_LABEL_MAX_WIDTH",numberCvtr );
    	addEntry ( "EDGE_LINE_TYPE", edgeLineTypeCvtr );
    	addEntry ( "EDGE_SOURCE_ARROW_SHAPE", arrowShapeCvtr );
    	addEntry ( "EDGE_SOURCE_ARROW_SIZE", numberCvtr );
    	addEntry ( "EDGE_TARGET_ARROW_SHAPE", arrowShapeCvtr );
    	addEntry ( "EDGE_TARGET_ARROW_SIZE", numberCvtr );

    	
    	//addEntry ( "EDGE_PAINT", "EDGE_LINE_COLOR", stringCvtr);
    	addEntry ( "EDGE_STROKE_UNSELECTED_PAINT", "EDGE_LINE_COLOR", stringCvtr);
    	addEntry ( "EDGE_SOURCE_ARROW_UNSELECTED_PAINT", "EDGE_SOURCE_ARROW_COLOR", stringCvtr);
    	addEntry ( "EDGE_TARGET_ARROW_UNSELECTED_PAINT", "EDGE_TARGET_ARROW_COLOR", stringCvtr);
    	addEntry ( "EDGE_TRANSPARENCY", "EDGE_OPACITY", opacityCvtr );
    	addEntry ( "EDGE_WIDTH", "EDGE_WIDTH", numberCvtr );
    	addEntry ( "EDGE_VISIBLE", booleanCvtr );

    	// these are non-portable Cytoscape styles that we just carry over. Cytoscape visual properties
    	// that are not in this list or the list above are excluded from the cx2 visual styles.
    	for(String n: cx1CarryOverVPNames) {
        	addEntry ( n );
    	}
 
    	
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


	// adding an entry to the nodeEdge converter table when old and new VP name are the same
	private void addEntry ( String oldVP, CXToCX2VisualPropertyCvtFunction cvt) { 
		AbstractMap.Entry<String, CXToCX2VisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(oldVP, cvt);
		
		nodeEdgeCvtTable.put(oldVP, cvtrEntry );
	}
	
	/* old and new VP name are the same, and the old and new VP value are the same. */
	private void addEntry ( String oldVP ) { 
		AbstractMap.Entry<String, CXToCX2VisualPropertyCvtFunction> cvtrEntry= new AbstractMap.SimpleImmutableEntry<>(oldVP, stringCvtr);
		
		nodeEdgeCvtTable.put(oldVP, cvtrEntry );
	}

	private static Map<String,Object> convertNetworkProperties(Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>>  table,
				Map<String,String> cx1Properties) throws NdexException {
		Map<String,Object> result = new HashMap<>();
		
		for (Map.Entry<String, String> e : cx1Properties.entrySet()) {
			Map.Entry<String,CXToCX2VisualPropertyCvtFunction> cvtrEntry = table.get(e.getKey());
			if ( cvtrEntry != null) {
				try {
					Object newValue = cvtrEntry.getValue().convert(e.getValue());
					result.put(cvtrEntry.getKey(), newValue);
				} catch (NullPointerException e1) {
					String errMsg = "NPE in mapping. Key: " + (e.getKey() == null? "": e.getKey()) +
							". value: " + (e.getValue()== null? "": e.getValue()) + ".";
					System.err.println (errMsg);
					throw new NdexException(errMsg);
				} catch (NdexException en) {
					throw new NdexException ("Failed to Convert visual property " + e.getKey() + 
							". Cause: " + en.getMessage());
				}
			}
		}
		
		return result;
	}
	
	public Map<String,Object>  convertNetworkVPs (Map<String,String> cx1Properties ) throws NdexException {
		return convertNetworkProperties(networkCvtTable, cx1Properties);
	}
    
	
	public Map<String,Object>  convertEdgeOrNodeVPs (Map<String,String> cx1Properties ) throws NdexException {
		return convertNetworkProperties(nodeEdgeCvtTable, cx1Properties);

	}
	
	public String getNewEdgeOrNodeProperty (String oldPropertyName) {
		Map.Entry<String,CXToCX2VisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(oldPropertyName);
		if ( cvtr != null)
			return cvtr.getKey();
		return null;
	}
	
	public Object getNewEdgeOrNodePropertyValue (String oldPropertyName, String oldValue) throws NdexException {
		Map.Entry<String,CXToCX2VisualPropertyCvtFunction> cvtr = nodeEdgeCvtTable.get(oldPropertyName);
		if ( cvtr != null) {
			try {
				return cvtr.getValue().convert(oldValue);
			} catch ( NumberFormatException e) {
				throw new NdexException ( "Visual property '" + oldPropertyName + "' has non-numeric value '" 
			           + oldValue +"'.");
			}
			
		}	
		return null;
	}
	
	
	public static CXToCX2VisualPropertyConverter getInstance() {
		return instance;
	}
	
	public interface CXToCX2VisualPropertyCvtFunction {
		public Object convert(String cxValue) throws NdexException;

	}
}
