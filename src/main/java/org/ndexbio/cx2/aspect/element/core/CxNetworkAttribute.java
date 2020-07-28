package org.ndexbio.cx2.aspect.element.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;
import org.ndexbio.model.object.NdexPropertyValuePair;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties({ "v" })

public class CxNetworkAttribute extends AttributeDeclaredAspect {

	public final static String ASPECT_NAME = "networkAttributes";
	
	public final static String nameAttribute = "name";
	public final static String descriptionAttribute  = "description";
	public final static String versionAttribute = "version";
	
	public CxNetworkAttribute () {}
	
	@JsonAnyGetter
	public Map<String, Object> getElementObject() {
		return attributes;
	}
	
	@JsonAnySetter
	public void add(String key, Object e) {
		if 	(CxAspectElement.isValidValueType(e))
			this.attributes.put(key, e);
		else 
		    throw new IllegalStateException("Invalid data type for network attribute " + key);
	}
 
	
	@Override
	@JsonIgnore

	public String getAspectName() {
		return ASPECT_NAME;
	}

	@JsonIgnore
	public String getNetworkName () {
		return (String) attributes.get(nameAttribute);
	}

	
	@JsonIgnore
	public String getNetworkDescription () {
		return (String) attributes.get(descriptionAttribute);
	}

	@JsonIgnore
	public String getNetworkVersion () {
		return (String) attributes.get(versionAttribute);
	}
	
	
	@Override
	public void transformAttributes(Map<String,DeclarationEntry> attributeDeclarations) throws NdexException {
		super.transformAttributes(attributeDeclarations);
		checkStringtype(nameAttribute);
		checkStringtype(descriptionAttribute);
		checkStringtype(versionAttribute);
	}
	
	private void checkStringtype( String attrName) throws NdexException {
		Object o = attributes.get(attrName);
	    if(  o == null || (o instanceof String))
	    	return;
	    throw new NdexException ("Network attribute " + attrName + " has to be a string.");
			
	}
	
	public List<NdexPropertyValuePair> toV1PropertyList( Map<String,DeclarationEntry> attributeDeclarations) throws JsonProcessingException {
		List<NdexPropertyValuePair> result = new ArrayList<>();
		for ( Map.Entry<String, Object> entry: attributes.entrySet()) {
			// filter out the 3 reserved attributes
			if ( !entry.getKey().equals(nameAttribute) && !entry.getKey().equals(descriptionAttribute)
					&& !entry.getKey().equals(versionAttribute)) {
				ATTRIBUTE_DATA_TYPE t = ATTRIBUTE_DATA_TYPE.STRING;
				
				if ( attributeDeclarations != null) {
					DeclarationEntry decl = attributeDeclarations.get(entry.getKey());
					t = decl.getDataType() == null? ATTRIBUTE_DATA_TYPE.STRING : 
	                     decl.getDataType();
				}
			
				NdexPropertyValuePair element ;
			
				if (t.isSingleValueType()) {
					element = new NdexPropertyValuePair( entry.getKey(), entry.getValue().toString(), t);
				} else {
					ObjectMapper om = new ObjectMapper();
					String v = om.writeValueAsString(entry.getValue());
					element =  new NdexPropertyValuePair( entry.getKey(), v, t);
				
				}
			
				result.add(element);
			}
		}
		
		return result;
	}

}
