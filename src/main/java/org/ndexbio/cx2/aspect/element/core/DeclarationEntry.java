package org.ndexbio.cx2.aspect.element.core;


import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(Include.NON_NULL)
public class DeclarationEntry {
	
	public static final String dataTypeAttrName = "d";
	public static final String defaultValAttrName = "v";
	public static final String aliasAttrName = "a";

//	@JsonProperty( dataTypeAttrName)
	private ATTRIBUTE_DATA_TYPE dataType;

	@JsonProperty( defaultValAttrName)
	private Object defaultValue;
	
	@JsonProperty( aliasAttrName)
    private String alias;
		
	public DeclarationEntry () {}

	public DeclarationEntry (ATTRIBUTE_DATA_TYPE datatype, Object defaultValue, String alias) {
		this.dataType = datatype;
		this.defaultValue = defaultValue;
		this.alias = alias;
	}

	@JsonGetter (dataTypeAttrName)
	public String getDataTypeStr() {
		if (dataType !=null)
		  return dataType.toString();
		
		return null;
	}
	
	@JsonSetter (dataTypeAttrName) 
	public void setDataTypeStr( String typeStr) {
		ATTRIBUTE_DATA_TYPE t = ATTRIBUTE_DATA_TYPE.fromCxLabel(typeStr);
		setDataType (t);
		
	}
	
	@JsonIgnore
	public ATTRIBUTE_DATA_TYPE getDataType() {
		return dataType;
	}
	
	public void setDataType(ATTRIBUTE_DATA_TYPE dataType) {
	/*	if ( dataType == ATTRIBUTE_DATA_TYPE.LONG  || dataType == ATTRIBUTE_DATA_TYPE.INTEGER
			|| dataType == ATTRIBUTE_DATA_TYPE.LIST_OF_LONG ||
			   dataType == ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER ) */
			this.dataType = dataType;
	/*	else 
			throw new NdexException ("Data type declaration can only be long, integer, list-of-long or list-of-integer");
	*/	
	}
	public Object getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	public void processValue() throws NdexException {
		if ( dataType == null) {
			throw new NdexException("Data type attribute is not declared in DeclarationEntry. Default value " + 
					defaultValue + " can't be processed.");
		}
		
		if( defaultValue != null) {
			defaultValue = AttributeDeclaredAspect.processAttributeValue (dataType, defaultValue);
		}
	}
	
/*	public static Object processAttributeValue (ATTRIBUTE_DATA_TYPE declaredType, Object value) {
		if (declaredType != null) {
			if (declaredType == ATTRIBUTE_DATA_TYPE.INTEGER) {
				return Integer.valueOf(((Number)value).intValue());
			} else if ( declaredType == ATTRIBUTE_DATA_TYPE.LONG) {
				return Long.valueOf(((Number)value).longValue());
			} else if (declaredType == ATTRIBUTE_DATA_TYPE.BOOLEAN) {
				return value;
		    } else if (declaredType == ATTRIBUTE_DATA_TYPE.DOUBLE) {
				return Double.valueOf(((Number)value).doubleValue());
		    } else if (declaredType == ATTRIBUTE_DATA_TYPE.STRING) {
		    	return value;
		    } else {
				if (value instanceof List<?>) {
					return ((List<Object>)value).stream().map(n -> processAttributeValue ( declaredType.elementType(),n))
							.collect(Collectors.toList());
				} 
				
				throw new RuntimeException ("Value " + value.toString() + " is not consistent the declared type " + declaredType 
							+ ".");
			} 
		}

		if ( value instanceof Integer  || value instanceof Long)
			return Double.valueOf(((Number)value).doubleValue());
		else if ( value instanceof List<?>) {
			return ((List<Object>)value).stream().map(n -> processAttributeValue ( null,n))
					.collect(Collectors.toList());
		} 
		return value;
			
	} */
	
}
