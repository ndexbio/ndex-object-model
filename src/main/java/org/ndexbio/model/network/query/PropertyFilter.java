package org.ndexbio.model.network.query;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyFilter {

	private List<PropertySpecification> _propertySpecList;
	
	PropertyFilter () {
		_propertySpecList = new ArrayList<>(10);
	}
	
	public List<PropertySpecification> getPropertySpecList() {
		return _propertySpecList;
	}
	public void setPropertySpecList(List<PropertySpecification> propertySpecList) {
		this._propertySpecList = propertySpecList;
	}

}
