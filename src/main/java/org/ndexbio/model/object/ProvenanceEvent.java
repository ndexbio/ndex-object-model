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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvenanceEvent extends NdexObject{
	
	private List<SimplePropertyValuePair> _properties;
	private List<ProvenanceEntity> _inputs;
	private Timestamp _startedAtTime;
	private Timestamp _endedAtTime;
	private String _eventType;

	public ProvenanceEvent () {
		super();
        _type = this.getClass().getSimpleName();
	}

	public ProvenanceEvent(String eventType, Timestamp eventTime) {
		super();
        _type = this.getClass().getSimpleName();
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
