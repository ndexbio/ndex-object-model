package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexProperty;
import org.ndexbio.model.object.PropertiedObject;

public abstract class PropertiedNetworkElement extends NetworkElement implements
		PropertiedObject {

	
	private List<NdexProperty> _properties;
	private List<NdexProperty> _presentationProperties;

    
	 public PropertiedNetworkElement()
	 {
	        super();
	        _type = this.getClass().getSimpleName();
	        _properties = new ArrayList<NdexProperty>();
	        _presentationProperties = new ArrayList<NdexProperty>();
//	        this.initializeCollections();
	 }
	
	
	public List<NdexProperty> getProperties() {
		return _properties;
	}

	public List<NdexProperty> getPresentationProperties() {
		return _presentationProperties;
	}

	public void setProperties(List<NdexProperty> properties) {
		_properties = properties;
	}

	public void setPresentationProperties(List<NdexProperty> properties) {
		_presentationProperties = properties;
	}


}
