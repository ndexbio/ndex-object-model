package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphEdge {
	private long _id;
	private long _subjectId;
	private long _objectId;
	private String _predicate;
	private List<Long> _citations;
	private List<Long> _supports;
	
	public PropertyGraphEdge () {
		setCitations(new ArrayList<Long>());
		_supports = new ArrayList<Long>();
	}

	public List<Long> getSupports() {
		return _supports;
	}

	public void set_supports(List<Long> _supports) {
		this._supports = _supports;
	}

	public List<Long> getCitations() {
		return _citations;
	}

	public void setCitations(List<Long> _citations) {
		this._citations = _citations;
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

	public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

	
}
