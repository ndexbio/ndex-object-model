package org.ndexbio.model.object.network;

import java.util.List;

import org.ndexbio.model.object.NdexObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Provenance extends NdexObject {

	private List<Long> _eventIds;
	private List<Long> _entityIds;
	private long _rootEventId;

	public Provenance () {
		super();
        _type = this.getClass().getSimpleName();

	}
	public List<Long> getEventIds() {
		return _eventIds;
	}

	public void setEventIds(List<Long> _eventIds) {
		this._eventIds = _eventIds;
	}

	public long getRootEventId() {
		return _rootEventId;
	}

	public void setRootEventId(long _rootEventId) {
		this._rootEventId = _rootEventId;
	}

	public List<Long> getEntityIds() {
		return _entityIds;
	}

	public void setEntityIds(List<Long> _entityIds) {
		this._entityIds = _entityIds;
	}
	
}
