package org.ndexbio.cx2.aspect.element.core;

import java.util.List;

public interface CxAspectElement  {

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

}
