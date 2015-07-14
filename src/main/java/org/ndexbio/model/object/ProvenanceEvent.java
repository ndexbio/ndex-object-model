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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvenanceEvent //extends NdexObject
{
	
	private List<SimplePropertyValuePair> _properties;
	private List<ProvenanceEntity> _inputs;
	private Timestamp _startedAtTime;
	private Timestamp _endedAtTime;
	private String _eventType;

	public ProvenanceEvent () {
		//super();
        //_type = this.getClass().getSimpleName();
	} 

	public ProvenanceEvent(String eventType, Timestamp eventTime) {
//		super();
//        _type = this.getClass().getSimpleName();
        _endedAtTime = eventTime;
        _startedAtTime = eventTime;
        _eventType = eventType;
        _properties = new ArrayList<> ();
	}

	public List<SimplePropertyValuePair> getProperties() {
		return _properties;
	}

	public void setProperties(List<SimplePropertyValuePair> properties) {
		this._properties = properties;
	}

	public List<ProvenanceEntity> getInputs() {
		return _inputs;
	}

	public void setInputs(List<ProvenanceEntity> inputs) {
		this._inputs = inputs;
	}
	
	public void addInput(ProvenanceEntity _input) {
		if (null == this._inputs) this._inputs = new ArrayList<>();
		this._inputs.add(_input);
	}

	public Date getStartedAtTime() {
		return _startedAtTime;
	}

	public void setStartedAtTime(Timestamp _startDate) {
		this._startedAtTime = _startDate;
	}

	public Date getEndedAtTime() {
		return _endedAtTime;
	}

	public void setEndedAtTime(Timestamp _endDate) {
		this._endedAtTime = _endDate;
	}

	public String getEventType() {
		return _eventType;
	}

	public void setEventType(String eventType) {
		this._eventType = eventType;
	}
	

}
