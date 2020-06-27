package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "v" })

public class CxNetworkAttribute extends AttributeDeclaredAspect implements CxAspectElement {

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
	public void transformAttrites(Map<String,DeclarationEntry> attributeDeclarations) throws NdexException {
		super.transformAttrites(attributeDeclarations);
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

}
