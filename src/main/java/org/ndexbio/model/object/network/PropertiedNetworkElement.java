package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.PropertiedObject;
import org.ndexbio.model.object.SimplePropertyValuePair;

public abstract class PropertiedNetworkElement extends NetworkElement implements
		PropertiedObject {

	
	private List<NdexPropertyValuePair> _properties;
	private List<SimplePropertyValuePair> _presentationProperties;

    
	 public PropertiedNetworkElement()
	 {
	        super();
	        _type = this.getClass().getSimpleName();
	        _properties = new ArrayList<NdexPropertyValuePair>();
	        _presentationProperties = new ArrayList<SimplePropertyValuePair>();
//	        this.initializeCollections();
	 }
	
	
	public List<NdexPropertyValuePair> getProperties() {
		return _properties;
	}

	public List<SimplePropertyValuePair> getPresentationProperties() {
		return _presentationProperties;
	}

	public void setProperties(List<NdexPropertyValuePair> properties) {
		_properties = properties;
	}

	public void setPresentationProperties(List<SimplePropertyValuePair> properties) {
		_presentationProperties = properties;
	}

	public String getPropertyAsString (String propertyName) {
		for (NdexPropertyValuePair p : this.getProperties()) {
			if ( p.getPredicateString().equals(propertyName))
				return p.getValue();
		}
		return null;
	}

}
