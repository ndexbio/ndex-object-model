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
