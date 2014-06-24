package org.ndexbio.model.object;

import java.util.List;

public interface PropertiedObject {

	public List<NdexProperty>  getProperties();
	
	public List<NdexProperty>  getPresentationProperties();
	
	public void setProperties(List<NdexProperty> properties);

	public void setPresentationProperties(List<NdexProperty> properties);
}
