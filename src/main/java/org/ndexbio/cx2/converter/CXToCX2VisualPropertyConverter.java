package org.ndexbio.cx2.converter;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.ndexbio.cx2.aspect.element.core.EdgeControlPoint;
import org.ndexbio.cx2.aspect.element.core.LabelPosition;
import org.ndexbio.cx2.aspect.element.core.NodeImageSize;
import org.ndexbio.cx2.aspect.element.core.ObjectPosition;
import org.ndexbio.cx2.aspect.element.core.VisualPropertyTable;
import org.ndexbio.model.exceptions.NdexException;


public class CXToCX2VisualPropertyConverter {
	
	public interface CXToCX2VisualPropertyCvtFunction {
		public Object convert(String cxValue) throws NdexException;

	}	
	
	/* These node or edge visual properties are not part of the cx2 portable styles, we just carry them over to cx2, and it is up to the 
	 * application to decide whether supporting them.
	 */
	protected static final List<String> cx1CarryOverVPNames = Arrays.asList(
			"COMPOUND_NODE_PADDING",
			"COMPOUND_NODE_SHAPE",
	
			"NODE_TOOLTIP",
			"NODE_X_LOCATION",
			"NODE_Y_LOCATION",
			"NODE_Z_LOCATION",
			
            "EDGE_SELECTED",
            "EDGE_SELECTED_PAINT",
			"EDGE_TOOLTIP"			
			);
	
	/* These properties will be held in a table and then get converted at the very end.
	 * 
	 */
	protected static final List<String> specialVPNames = Arrays.asList(
			"EDGE_BEND", "EDGE_CURVED");
	
	
	private Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>> networkCvtTable; 
	private Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>> nodeEdgeCvtTable; 
	
	private static final CXToCX2VisualPropertyCvtFunction numberCvtr = 
			 (strVal) -> Double.valueOf(strVal); 

	private static final CXToCX2VisualPropertyCvtFunction intCvtr = (strVal) -> {
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
	
	private static final CXToCX2VisualPropertyCvtFunction fontFaceCvtr = (strVal) -> {
		return FontFaceConverter.convertFont(strVal);
	};
	
	private static final CXToCX2VisualPropertyCvtFunction visibilityCvtr = (strVal) ->
		{return strVal.equals("true")? "element" : "none"; };
	
	private static final CXToCX2VisualPropertyCvtFunction nodeImageCvtr = 
			(strVal) -> {
			 if ( strVal.equals("org.cytoscape.ding.customgraphics.NullCustomGraphics,0,[ Remove Graphics ],"))
				 return null;
			 return strVal;
			};
	
	private static final CXToCX2VisualPropertyCvtFunction edgeBendCvtr = (strVal) -> {
		return strVal == null ? null :
			(Stream.of(strVal.split("\\|")).map(arg0 -> EdgeControlPoint.createFromCX1String(arg0)).collect(Collectors.toList()));
	
		};
			
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
    	addEntry ( "NODE_BORDER_PAINT", "NODE_BORDER_COLOR",stringCvtr );
    	addEntry ("NODE_BORDER_STROKE", "NODE_BORDER_STYLE", nodeBorderTypeCvtr);
    	addEntry ( "NODE_BORDER_TRANSPARENCY","NODE_BORDER_OPACITY", opacityCvtr );
    	addEntry ( "NODE_BORDER_WIDTH", numberCvtr );
    	 	
    	addEntry ( "NODE_FILL_COLOR", "NODE_BACKGROUND_COLOR", stringCvtr );
    	addEntry ( "NODE_HEIGHT",  numberCvtr	 );
    	addEntry ( "NODE_LABEL");
    	addEntry ( "NODE_LABEL_COLOR"    );
    	addEntry ( "NODE_LABEL_FONT_FACE", fontFaceCvtr);
    	addEntry ( "NODE_LABEL_FONT_SIZE", intCvtr );
    	
    	addEntry ( "NODE_LABEL_POSITION", (positionStr) -> {return LabelPosition.createFromCX1Value(positionStr);} );
    	addEntry ( "NODE_LABEL_ROTATION",  numberCvtr);
    	
    	addEntry ( "NODE_LABEL_TRANSPARENCY","NODE_LABEL_OPACITY", opacityCvtr );
    	
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
    	addEntry ( "NODE_VISIBLE", "NODE_VISIBILITY", visibilityCvtr );
    	
    	for ( int i = 1 ; i < 10; i++) {
        	addEntry ( "NODE_CUSTOMGRAPHICS_" + i, "NODE_IMAGE_" + i, nodeImageCvtr );    		
        	addEntry ( "NODE_CUSTOMGRAPHICS_SIZE_" + i, "NODE_IMAGE_" + i + "_SIZE",
        			(strVal) -> {return NodeImageSize.createFromCX1Str(strVal);} );    		
        	addEntry ( "NODE_CUSTOMGRAPHICS_POSITION_" + i, "NODE_IMAGE_" + i + "_POSITION", 
        			(positionStr) ->{ return ObjectPosition.createFromCX1Value(positionStr);} );    		
    	}

    	
    	// edges
    	
    	//TODO: handle edge_curved and edge_bend
    	addEntry ( "EDGE_LABEL");
    	addEntry ( "EDGE_LABEL_COLOR"    );
    	addEntry ( "EDGE_LABEL_FONT_FACE", fontFaceCvtr);
    	addEntry ( "EDGE_LABEL_FONT_SIZE", intCvtr );
    	addEntry ( "EDGE_LABEL_TRANSPARENCY", "EDGE_LABEL_OPACITY", opacityCvtr );
    	addEntry ( "EDGE_LABEL_WIDTH","EDGE_LABEL_MAX_WIDTH",numberCvtr );
    	addEntry ( "EDGE_LINE_TYPE", "EDGE_LINE_STYLE", edgeLineTypeCvtr );
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
    	addEntry ( "EDGE_VISIBLE", "EDGE_VISIBILITY", visibilityCvtr );
    	addEntry ( "EDGE_SELECTED", booleanCvtr );
    	addEntry ( "EDGE_CURVED", booleanCvtr );
    	addEntry ( "EDGE_BEND", "EDGE_CONTROL_POINTS", edgeBendCvtr );
    	addEntry ( "EDGE_Z_ORDER", numberCvtr );
    	addEntry ( "EDGE_STACKING_DENSITY", numberCvtr );
    	addEntry ( "EDGE_STACKING" );
    	
    	
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

	private static VisualPropertyTable convertVisualProperties(Map<String, Map.Entry<String,CXToCX2VisualPropertyCvtFunction>>  table,
				Map<String,String> cx1Properties) throws NdexException {
		VisualPropertyTable result = new VisualPropertyTable();
		
		
		for (Map.Entry<String, String> e : cx1Properties.entrySet()) {
			Map.Entry<String,CXToCX2VisualPropertyCvtFunction> cvtrEntry = table.get(e.getKey());
			if ( cvtrEntry != null) {
				try {
					Object newValue = cvtrEntry.getValue().convert(e.getValue());
					if ( newValue != null)
					result.getVisualProperties().put(cvtrEntry.getKey(), newValue);
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
		
		return convertVisualProperties(networkCvtTable, cx1Properties).getVisualProperties();
	}
    
	
	public VisualPropertyTable convertEdgeOrNodeVPs (Map<String,String> cx1Properties ) throws NdexException {
		return convertVisualProperties(nodeEdgeCvtTable, cx1Properties);

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
	
	/*
	private static void cvtEdgeBendNCurve (String edgeBend, String edgeCurve, VisualPropertyTable resultHolder) {
		if ( edgeCurve != null) { 
			boolean curved = Boolean.parseBoolean(edgeCurve);
			CurveStyle curvestyle = curved ? 
					edgeBend == null ? 
						CurveStyle.BEZIER : CurveStyle.UNBUNDLED_BEZIER
					: edgeBend == null ? 
						CurveStyle.STRAIGHT	: CurveStyle.SEGMENTS;

			EdgeBendPoint[] bendPoints = edgeBend == null ? null :
				Stream.of(edgeBend.split("\\|")).map(arg0 -> EdgeBendPoint.createFromCX1String(arg0)).toArray(EdgeBendPoint[]::new);

			resultHolder.getVisualProperties().put("EDGE_CURVE_STYLE", curvestyle);
			if ( bendPoints != null) {
				resultHolder.getVisualProperties().put("EDGE_BEND", bendPoints);
			}
		}
	} */
	
	public static CXToCX2VisualPropertyConverter getInstance() {
		return instance;
	}
	
}
