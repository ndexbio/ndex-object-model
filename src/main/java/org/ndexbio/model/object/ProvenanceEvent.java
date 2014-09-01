package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvenanceEvent extends NdexObject{
	
	private List<NdexProperty> _properties;
	private List<ProvenanceEntity> _inputs;
	private Date _startDate;
	private Date _endDate;
	private String _eventType;

	public ProvenanceEvent () {
		super();
        _type = this.getClass().getSimpleName();
	}

	public ProvenanceEvent(String eventType, Date eventDate) {
		super();
        _type = this.getClass().getSimpleName();
        _endDate = eventDate;
        _eventType = eventType;
	}

	public List<NdexProperty> getProperties() {
		return _properties;
	}

	public void setProperties(List<NdexProperty> _properties) {
		this._properties = _properties;
	}

	public List<ProvenanceEntity> getInputs() {
		return _inputs;
	}

	public void setInputs(List<ProvenanceEntity> _inputs) {
		this._inputs = _inputs;
	}
	
	public void addInput(ProvenanceEntity _input) {
		if (null == this._inputs) this._inputs = new ArrayList<ProvenanceEntity>();
		this._inputs.add(_input);
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date _startDate) {
		this._startDate = _startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date _endDate) {
		this._endDate = _endDate;
	}

	public String getEventType() {
		return _eventType;
	}

	public void setEventType(String _eventType) {
		this._eventType = _eventType;
	}
	

}
