package org.ndexbio.model.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvenanceEntity extends NdexObject {

	private List<NdexProperty> _properties;
	private ProvenanceEvent _creationEvent;
	private String _uri;

	public ProvenanceEntity () {
		super();
        _type = this.getClass().getSimpleName();

	}

	public List<NdexProperty> getProperties() {
		return _properties;
	}

	public void setProperties(List<NdexProperty> _properties) {
		this._properties = _properties;
	}

	public ProvenanceEvent getCreationEvent() {
		return _creationEvent;
	}

	public void setCreationEvent(ProvenanceEvent _creationEvent) {
		this._creationEvent = _creationEvent;
	}

	public String getUri() {
		return _uri;
	}

	public void setUri(String _uri) {
		this._uri = _uri;
	}
	
}
