package org.ndexbio.model.cx;

import java.util.Map;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NamespacesElement implements AspectElement {

	public static final String NAME = "@context";
	
	private Map<String,String> prefixMap;
	
	public NamespacesElement() {
	}

	public NamespacesElement(Map<String,String> prefixTable){
		this.prefixMap = prefixTable;
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return NAME;
	}

	@Override
	@JsonIgnore
	public long getSum() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String,String> getPrefixMap() {
		return prefixMap;
	}

	public void setPrefixMap(Map<String,String> prefixMap) {
		this.prefixMap = prefixMap;
	}

}
