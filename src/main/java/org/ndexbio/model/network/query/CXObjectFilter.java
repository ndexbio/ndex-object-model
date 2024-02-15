package org.ndexbio.model.network.query;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)

public class CXObjectFilter {
	private Set<Long> ids;
	private Set<String> attributeNames;
	
	public CXObjectFilter() {
		ids = new TreeSet<>();
		attributeNames = new TreeSet<>();		
	}
	
	
	public Set<Long> getIds() {
		return ids;
	}
	
	public void setIds(Set<Long> ids) {
		this.ids = ids;
	}
	
	public Set<String> getAttributeNames() {
		return attributeNames;
	}
	
	public void setAttributeNames(Set<String> attributeNames) {
		this.attributeNames = attributeNames;
	}
	
	
	
	
}
