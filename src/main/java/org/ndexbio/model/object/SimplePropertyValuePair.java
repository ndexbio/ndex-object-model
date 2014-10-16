package org.ndexbio.model.object;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SimplePropertyValuePair extends NdexObject{


	private String _name;
	private String _value;
	
	public SimplePropertyValuePair() {
        super();
        this._type = this.getClass().getSimpleName();
	}
	
	public SimplePropertyValuePair(String key, String value) {
        super();
        this._type = this.getClass().getSimpleName();
        this.setName(key);
        this.setValue(value);
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		this._value = value;
	}

	
}
