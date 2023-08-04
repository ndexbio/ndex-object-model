package org.ndexbio.cx2.aspect.element.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.ndexbio.cxio.aspects.datamodels.AbstractElementAttributesAspectElement;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



public abstract class AttributeDeclaredAspect<T extends AttributeDeclaredAspect<?>> implements CxAspectElement<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			else {
				try { 
					processAttributeValue(t, entry.getValue());
				} catch (ClassCastException e) {
					throw new NdexException ("Value " + entry.getValue() + " on attribute " + entry.getKey() + " can not be cast to " + t);
				}
			}	
		}
		
	}
	
	
	/**
	 * Get the value of the given attribute. The value is evaluated based on the attribute declaration.
	 * @param attributeName
	 * @param decl
	 * @return
	 * @throws NdexException
	 */
	public Object getWelldoneAttributeValue(String attributeName, DeclarationEntry decl) throws NdexException {
		Object rawValue =  
				attributes.get( decl.getAlias()!=null ? decl.getAlias() : attributeName);
		
		if ( rawValue == null && decl.getDefaultValue() != null) 
			rawValue = decl.getDefaultValue();
		
		if ( rawValue !=null)
			rawValue = processAttributeValue(decl.getDataType(), rawValue);
		
		return rawValue;
	}
	
	public static Object processAttributeValue(ATTRIBUTE_DATA_TYPE declaredType, Object value) throws NdexException {
		if (value == null)
			return value;

		if (declaredType == ATTRIBUTE_DATA_TYPE.INTEGER) {
			return (value instanceof Integer) ? value : Integer.valueOf(((Number) value).intValue());
		} else if (declaredType == ATTRIBUTE_DATA_TYPE.LONG) {
			return (value instanceof Long) ? value : Long.valueOf(((Number) value).longValue());
		} else if (declaredType == ATTRIBUTE_DATA_TYPE.BOOLEAN) {
			if (value instanceof Boolean)
				return value;
			throw new NdexException("Value " + value + " is not a boolean type.");
		} else if (declaredType == ATTRIBUTE_DATA_TYPE.DOUBLE) {
			return (value instanceof Double) ? value : Double.valueOf(((Number) value).doubleValue());
		} else if (declaredType == ATTRIBUTE_DATA_TYPE.STRING) {
			if (value instanceof String)
				return value;
			throw new NdexException("Value " + value + " is not a string.");
		} else {
			if (value instanceof List<?>) {
				return ((List<?>) value).stream().map(n -> {
					try {
						return processAttributeValue(declaredType.elementType(), n);
					} catch (NdexException e) {
						throw new RuntimeException("Incorrect data type found in list: " + e.getMessage());
					}
				}).collect(Collectors.toList());
			}

			throw new RuntimeException(
					"Value " + value.toString() + " is not consistent the declared type " + declaredType + ".");
		}
	}
	
	protected String addCX1Attribute(AbstractElementAttributesAspectElement cx1ElementAttribute,
			CxAttributeDeclaration attrDeclarations, String aspectName) throws NdexException {
		try {
			List<String> warnings = new ArrayList<> ();
			Object v = convertAttributeValue(cx1ElementAttribute,warnings);
			String attrName = cx1ElementAttribute.getName();
			Map<String, DeclarationEntry> attributeDef = attrDeclarations.getDeclarations().get(aspectName);
			if (attributeDef != null && attributeDef.containsKey(attrName)) {
				DeclarationEntry decl = attributeDef.get(attrName);
				if (decl.getDefaultValue() != null) {
					Object defaultV = decl.getDefaultValue();
					if (v.equals(defaultV))
						return null;
				}

				if (decl.getAlias() != null) {
					attrName = decl.getAlias();
				}
			}
			Object oldV = attributes.put(attrName, v);
			if (oldV != null) {
				if (!v.equals(oldV))
					throw new NdexException("Duplicate " + aspectName + " attribute on id: "
							+ cx1ElementAttribute.getPropertyOf() + ". Attribute '" + cx1ElementAttribute.getName()
							+ "' has value (" + oldV + ") and (" + cx1ElementAttribute.getValueString() + ")");
			}
			if (warnings.isEmpty())
				return null;
			return warnings.get(0) +"(of attribute " + attrName + " in aspect "+ cx1ElementAttribute.getAspectName() + ")" ;
		} catch (NumberFormatException e) {
			if ( cx1ElementAttribute.isSingleValue() && cx1ElementAttribute.getValue().toLowerCase().equals("null")) {
				String warningstr = "In '" + cx1ElementAttribute.getAspectName() + "' aspect, ignoring '" + 
						cx1ElementAttribute.getValue() + "' on '" + 
						cx1ElementAttribute.getName() + "' attribute with id '" + cx1ElementAttribute.getPropertyOf() + "'.";
				return warningstr;
			}	
			throw e;
		}
	}
	
	public static Object convertAttributeValue(AbstractAttributesAspectElement attr, List<String> warningHolder ) throws NdexException {
		switch (attr.getDataType()) {
		case BOOLEAN: 
		case DOUBLE:
		case INTEGER:
		case LONG:
		case STRING:
			return convertSingleAttributeValue(attr.getDataType(), attr.getValue(),warningHolder);
		case LIST_OF_BOOLEAN:
		case LIST_OF_DOUBLE:
		case LIST_OF_INTEGER:
		case LIST_OF_LONG:
		case LIST_OF_STRING:	
			List<String> ls = attr.getValues();
			ArrayList<Object> result = new ArrayList<>(ls.size());
			for ( String s : ls) {
				result.add(convertSingleAttributeValue(attr.getDataType().elementType(), s, warningHolder));
			}
			return result;
		default:
			throw new NdexException ("Unsupported attribute data type found: " + attr.getDataType());
		}
	}

	private static Object convertSingleAttributeValue(ATTRIBUTE_DATA_TYPE t, String value, List<String> warningHolder) throws NdexException {
		try {
			switch (t) {
			case BOOLEAN:
				return Boolean.valueOf(value);
			case DOUBLE: {
				Double v = Double.valueOf(value);
				if ( v.isNaN() || v.isInfinite()) {
					warningHolder.add(value + " is not a supported double value in CX2. It is converted to null.");
					return null;
				}	
				return Double.valueOf(value);
			}	
			case INTEGER:
				return Integer.valueOf(value);
			case LONG:
				return Long.valueOf(value);
			case STRING:
				return value;
			default:
				throw new NdexException("Value " + value + " is not a single value type. It is " + t.toString());
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException(
					"Non numeric value '" + value + "' is declared as type " + t.toString() + ".");
		}
	}
	
	
	protected String getStringAttr(Map<String, DeclarationEntry> attrDecls, String attrName) {
		if ( attrDecls == null )
			return null;
		
		DeclarationEntry decl = attrDecls.get(attrName);
		if (decl==null || decl.getDataType()!=ATTRIBUTE_DATA_TYPE.STRING)
			return null;
		String defaultVal = (String)decl.getDefaultValue();
		
		
		String a = decl.getAlias();
		Object v = this.getAttributes().get((a == null ? attrName : a));
		if ( v!= null)
		  return (String)v;
		return defaultVal;
		
	}
}
