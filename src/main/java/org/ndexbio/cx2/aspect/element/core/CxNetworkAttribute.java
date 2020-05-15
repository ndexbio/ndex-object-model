package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "v" })

public class CxNetworkAttribute extends AttributeDeclaredAspect implements CxAspectElement {

	public final static String ASPECT_NAME = "networkAttributes";
	
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

	

}
