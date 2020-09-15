package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CxOpaqueAspectElement implements CxAspectElement<CxOpaqueAspectElement> {
	
	private String aspectName;
	
	private Map<String, Object> elementObject = new HashMap<>();
	
	public CxOpaqueAspectElement () {}
	
	@JsonAnyGetter
	public Map<String, Object> getElementObject() {
		return elementObject;
	}
	
	@JsonAnySetter
	public void add(String key, Object e) {
		this.elementObject.put(key, e);
	}
 

	@JsonIgnore
	public void setAspectName(String aspectName) {
		this.aspectName = aspectName;
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return aspectName;
	}

	@Override
	public int compareTo(CxOpaqueAspectElement o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
