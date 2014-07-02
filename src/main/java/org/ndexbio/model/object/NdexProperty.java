package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NdexProperty extends NdexObject {

	public static final String STRING = "String"; 
	
	private long   _predicateId;
	private String _predicateString;
	private long _valueId;
	private String _value;
	private String _dataType;
	
	public NdexProperty () {
		_dataType = STRING;
        _type = this.getClass().getSimpleName();

	}
	
	public long getPredicateId() {
		return _predicateId;
	}
	public void setPredicateId(long _predicateId) {
		this._predicateId = _predicateId;
	}
	public long getValueId() {
		return _valueId;
	}
	public void setValueId(long _valueId) {
		this._valueId = _valueId;
	}
	public String getValue() {
		return _value;
	}
	public void setValue(String _value) {
		this._value = _value;
	}
	public String getDataType() {
		return _dataType;
	}
	public void setDataType(String _dataType) {
		this._dataType = _dataType;
	}

	public String getPredicateString() {
		return _predicateString;
	}

	public void setPredicateString(String _predicateString) {
		this._predicateString = _predicateString;
	}
	

}
