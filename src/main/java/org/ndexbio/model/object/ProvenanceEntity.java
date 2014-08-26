package org.ndexbio.model.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvenanceEntity extends NdexObject {

	private List<NdexProperty> _properties;
	private ProvenanceEvent _creationEvent;

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

	public void set_createdFromEvent(ProvenanceEvent _creationEvent) {
		this._creationEvent = _creationEvent;
	}
	

	
}
