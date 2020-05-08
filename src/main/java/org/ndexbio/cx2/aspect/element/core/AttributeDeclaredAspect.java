package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)

public abstract class AttributeDeclaredAspect {

	@JsonProperty("v")
	private Map<String, Object> attributes;
	
	public AttributeDeclaredAspect () {attributes = new HashMap<>(); }
	
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}
	
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public void validateAttribute( Map<String,DeclarationEntry> attributeDeclarations) throws NdexException {
		for ( Map.Entry<String, Object> entry : attributes.entrySet()) {
			DeclarationEntry decl = attributeDeclarations.get(entry.getKey());
			if ( decl == null)
				throw new NdexException ("Type of Attribute " + entry.getKey() + " is not declared.");
			ATTRIBUTE_DATA_TYPE t = decl.getDataType() == null? ATTRIBUTE_DATA_TYPE.STRING : 
				                     decl.getDataType();
			
			entry.setValue(processAttributeValue(t, entry.getValue()));	
		}
		
	}
	
	public static Object processAttributeValue (ATTRIBUTE_DATA_TYPE declaredType, Object value) throws NdexException {

			if (declaredType == ATTRIBUTE_DATA_TYPE.INTEGER) {
				return (value instanceof Integer) ? value : Integer.valueOf(((Number)value).intValue());
			} else if ( declaredType == ATTRIBUTE_DATA_TYPE.LONG) {
				return (value instanceof Long) ? value : Long.valueOf(((Number)value).longValue());
			} else if (declaredType == ATTRIBUTE_DATA_TYPE.BOOLEAN) {
				if ( value instanceof Boolean)
					return value;
				throw new NdexException ("Value " + value + " is not a boolean type.") ;
		    } else if (declaredType == ATTRIBUTE_DATA_TYPE.DOUBLE) {
				return (value instanceof Double) ? value : Double.valueOf(((Number)value).doubleValue());
		    } else if (declaredType == ATTRIBUTE_DATA_TYPE.STRING) {
		    	if (value instanceof String)
		    		return value;
		    	throw new NdexException("Value " + value + " is not a string.");
		    } else {
				if (value instanceof List<?>) {
					return ((List<?>)value).stream().map(n -> {
						try {
							return processAttributeValue ( declaredType.elementType(),n);
						} catch (NdexException e) {
							throw new RuntimeException ("Incorrect data type found in list: "  + e.getMessage());
						}
					})
							.collect(Collectors.toList());
				} 
				
				throw new RuntimeException ("Value " + value.toString() + " is not consistent the declared type " + declaredType 
							+ ".");
			} 
	}


}
