package org.ndexbio.model.network.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class PropertySpecification {
	private String _name;
	private String _value;
	
	public PropertySpecification(String propName, String value) {
		_name = propName;
		_value = value;
		
	}
	
	public PropertySpecification() {}
	
	public String getName() {
		return _name;
	}
	public void setName(String property) {
		this._name = property;
	}
	public String getValue() {
		return _value;
	}
	public void setValue(String value) {
		this._value = value;
	}

}
