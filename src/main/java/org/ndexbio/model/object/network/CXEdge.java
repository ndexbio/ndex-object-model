package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class CXEdge {
	
	private String _id;
	private String _source;
	private String _target;

	public CXEdge() {
	}

	public CXEdge(String id, String source, String target) {
		setId(id);
		setSource(source);
		setTarget(target);
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		this._source = source;
	}

	public String getTarget() {
		return _target;
	}

	public void setTarget(String target) {
		this._target = target;
	}
	
	
}
