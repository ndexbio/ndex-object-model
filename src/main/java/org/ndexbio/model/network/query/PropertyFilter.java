package org.ndexbio.model.network.query;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyFilter {

	private List<PropertySpecification> _propertySpecifications;
	
	PropertyFilter () {
		_propertySpecifications = new ArrayList<>(10);
	}
	
	public List<PropertySpecification> getPropertySpecifications() {
		return _propertySpecifications;
	}
	public void setPropertySpecifications(List<PropertySpecification> propertySpecList) {
		this._propertySpecifications = propertySpecList;
	}

}
