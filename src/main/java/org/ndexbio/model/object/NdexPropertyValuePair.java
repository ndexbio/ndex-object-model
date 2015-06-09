/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
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
