package org.ndexbio.cx2.aspect.element.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public abstract class AttributeDeclaredAspect implements CxAspectElement{

	@JsonProperty("v")
    @JsonInclude(Include.NON_EMPTY)	
	protected LinkedHashMap<String, Object> attributes;
	
	public AttributeDeclaredAspect () {attributes = new LinkedHashMap<>(); }
	
	public LinkedHashMap<String, Object> getAttributes() {
		return this.attributes;
	}
	
	public void setAttributes(LinkedHashMap<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
	/**
	 * 
	 * @param attributeDeclarations attribute declarations for this aspect.
	 * @throws NdexException
	 */
	public void transformAttributes(Map<String,DeclarationEntry> attributeDeclarations) throws NdexException {
		
		// convert alias back to the real attribute name first
		replaceShortenedName(attributeDeclarations);
		
		validateAttribute(attributeDeclarations,true);
	}
	
	
	
	/**
	 * Convert shortened name to full name, and put back the default value
	 * @param attributeDeclarations
	 */
	public void extendToFullNode(Map<String,DeclarationEntry> attributeDeclarations) {
		if ( attributeDeclarations !=null) {
		for ( Map.Entry<String, DeclarationEntry> e : attributeDeclarations.entrySet()) {
			DeclarationEntry entry = e.getValue(); 

			// convert alias back to the real attribute name first
			String a = entry.getAlias();
			if ( a != null) {
				Object v = attributes.remove(a);
				if ( v!= null)
					attributes.put(e.getKey(), v);
			}
			
			//Add back the defaultValue
			Object defaultV = entry.getDefaultValue();
			if ( defaultV!=null && attributes.get(e.getKey()) == null ) {
				attributes.put(e.getKey(), defaultV);
			}
				
		}
		}
	}
	
	
	public void replaceShortenedName(Map<String, DeclarationEntry> attributeDeclarations) {
		if (attributeDeclarations != null) {
			for (Map.Entry<String, DeclarationEntry> e : attributeDeclarations.entrySet()) {
				String a = e.getValue().getAlias();
				if (a != null) {
					Object v = attributes.remove(a);
					if (v != null)
						attributes.put(e.getKey(), v);
				}
			}
		}
	}
	/**
	 * Validate the attributes. This function assumes all the shortened attibute names has been replaced. 
	 * 
	 * @param attributeDeclarations
	 * @param updateValue when the value is true, this function will replace an attribute value with the
	 *  transformed value. 
	 * @throws NdexException
	 */
	public void validateAttribute( Map<String,DeclarationEntry> attributeDeclarations, boolean updateValue) throws NdexException {
		
		if ( !attributes.isEmpty() && attributeDeclarations == null)
			throw new NdexException ("Attribute in aspect " + getAspectName() + 
					" is not declared in " + CxAttributeDeclaration.ASPECT_NAME + " aspect.");
		for ( Map.Entry<String, Object> entry : attributes.entrySet()) {
			DeclarationEntry decl = attributeDeclarations.get(entry.getKey());
			if ( decl == null)
				throw new NdexException ("Attribute " + entry.getKey() + " is not declared in " + 
						CxAttributeDeclaration.ASPECT_NAME + " aspect.");
			ATTRIBUTE_DATA_TYPE t = decl.getDataType() == null? ATTRIBUTE_DATA_TYPE.STRING : 
				                     decl.getDataType();
			
			if ( updateValue)
				entry.setValue(processAttributeValue(t, entry.getValue()));
			else 
				processAttributeValue(t, entry.getValue());
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
