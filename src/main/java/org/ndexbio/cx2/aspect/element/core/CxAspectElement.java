package org.ndexbio.cx2.aspect.element.core;

import java.io.Serializable;
import java.util.List;

import org.ndexbio.cx2.aspect.element.cytoscape.VisualEditorProperties;

public interface CxAspectElement<T extends CxAspectElement<?>> extends Comparable<T>,Serializable{

	public String getAspectName() ;
	
	public static boolean isValidBaseType (Object value) {
		if ( value instanceof String || 
			 value instanceof Double ||
			 value instanceof Integer ||
			 value instanceof Long || 
			 value instanceof Boolean)
			return true;
		return false;
	}
	
	// note: this function only validate the first element of the the list. 
	// might need another function if we need to fully validate the type, which might be 
	// expensive.
	public static boolean isValidValueType (Object value) { 
		if ( isValidBaseType (value))
			return true;
		if ( value instanceof List) {
			List<?> l = (List<?>)value;
			if (l.isEmpty() || isValidBaseType(l.get(0))) return true;
			return true;
		}
		return false;	
	}

	
	public static Class<? extends CxAspectElement<?>> getCxClassFromAspectName (String aspectName) {
		switch (aspectName) {
		case CxEdge.ASPECT_NAME: 
			return CxEdge.class;
		case CxNode.ASPECT_NAME:
			return CxNode.class;
		case CxAttributeDeclaration.ASPECT_NAME:
			return CxAttributeDeclaration.class;
		case CxNetworkAttribute.ASPECT_NAME:
			return CxNetworkAttribute.class;
		case CxVisualProperty.ASPECT_NAME:
			return CxVisualProperty.class;
		case CxNodeBypass.ASPECT_NAME:
			return CxNodeBypass.class;
		case CxEdgeBypass.ASPECT_NAME:
			return CxEdgeBypass.class;
		case VisualEditorProperties.ASPECT_NAME:
			return VisualEditorProperties.class;
		default:
			return CxOpaqueAspectElement.class;
		}
		
	}
}
