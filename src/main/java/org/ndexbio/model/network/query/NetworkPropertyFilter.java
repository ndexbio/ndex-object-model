package org.ndexbio.model.network.query;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class NetworkPropertyFilter {

	   private Collection<PropertySpecification> _properties;
	   private String _admin;
	   private int   _limit;
	   
	   
	public Collection<PropertySpecification> getProperties() {
		return _properties;
	}
	public void setProperties(Collection<PropertySpecification> _properties) {
		this._properties = _properties;
	}
	public String getAdmin() {
		return _admin;
	}
	public void setAdmin(String _admin) {
		this._admin = _admin;
	}
	public int getLimit() {
		return _limit;
	}
	public void setLimit(int _limit) {
		this._limit = _limit;
	}
	   

}
