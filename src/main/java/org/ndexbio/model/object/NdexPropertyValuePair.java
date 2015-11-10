/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.object;

import java.io.Serializable;

import org.ndexbio.model.cx.CXSimpleAttribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NdexPropertyValuePair implements Serializable /*extends NdexObject */ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String STRING = "string"; 
	
	private String _predicateString;
	private String _value;
	private String _dataType;
	private Long subNetworkId;
	
	public NdexPropertyValuePair () {
		_dataType = STRING;

	}
	
	public NdexPropertyValuePair (Long subNetwork,
			String propertyName, String value, String dataType) {
		this._predicateString = propertyName;
		this._value = value;
		this.subNetworkId = subNetwork;
		this._dataType = dataType;
	}
	
	public NdexPropertyValuePair (CXSimpleAttribute e) {
		this._predicateString = e.getName();
		this._value = e.getValue();
		this.subNetworkId = null;
		this._dataType = e.getDataType();
	}
	public NdexPropertyValuePair (String key, String value) {
		_predicateString = key;
		_value = value;
		_dataType = STRING;
	}
	
/*	
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
	} */
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
	

	@Override
	public String toString() {
		return "{\""+ _predicateString + "\":\"" + _value+"\"("+ _dataType+")}" ;
	}

	public Long getSubNetworkId() {
		return subNetworkId;
	}

	public void setSubNetworkId(Long subNetworkId) {
		this.subNetworkId = subNetworkId;
	}

}
