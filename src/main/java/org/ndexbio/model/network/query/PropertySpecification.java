package org.ndexbio.model.network.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class PropertySpecification {
	private String _property;
	private String _value;
	
	public PropertySpecification(String propName, String value) {
		_property = propName;
		_value = value;
		
	}
	
	public PropertySpecification() {}
	
	public String getProperty() {
		return _property;
	}
	public void setProperty(String property) {
		this._property = property;
	}
	public String getValue() {
		return _value;
	}
	public void setValue(String value) {
		this._value = value;
	}

}
