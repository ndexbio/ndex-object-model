package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NdexPropertyValuePair extends NdexObject {

	public static final String STRING = "String"; 
	
	private long   _predicateId;
	private String _predicateString;
	private long _valueId;
	private String _value;
	private String _dataType;
	
	public NdexPropertyValuePair () {
		_dataType = STRING;
        _type = this.getClass().getSimpleName();

	}
	
	public NdexPropertyValuePair (String key, String value) {
		_predicateString = key;
		_value = value;
		_dataType = STRING;
        _type = this.getClass().getSimpleName();
	}
	
	public long getPredicateId() {
		return _predicateId;
	}
	public void setPredicateId(long predicateId) {
		this._predicateId = predicateId;
	}
	public long getValueId() {
		return _valueId;
	}
	public void setValueId(long valueId) {
		this._valueId = valueId;
	}
	public String getValue() {
		return _value;
	}
	public void setValue(String value) {
		this._value = value;
	}
	public String getDataType() {
		return _dataType;
	}
	public void setDataType(String dataType) {
		this._dataType = dataType;
	}

	public String getPredicateString() {
		return _predicateString;
	}

	public void setPredicateString(String predicateString) {
		this._predicateString = predicateString;
	}
	

}
