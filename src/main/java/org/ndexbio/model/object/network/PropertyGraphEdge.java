package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphEdge extends PropertiedNetworkElement{

	public static final String citations = "NDEX:citations";
	public static final String supports  = "NDEX:supports";
	
	private long _subjectId;
	private long _objectId;
	private String _predicate;
	
	public PropertyGraphEdge () {
		super();
	}


	public String getPredicate() {
		return _predicate;
	}

	public void setPredicate(String _predicate) {
		this._predicate = _predicate;
	}

	public long getObjectId() {
		return _objectId;
	}

	public void setObjectId(long _objectId) {
		this._objectId = _objectId;
	}

	public long getSubjectId() {
		return _subjectId;
	}

	public void setSubjectId(long _subjectId) {
		this._subjectId = _subjectId;
	}

	
}
