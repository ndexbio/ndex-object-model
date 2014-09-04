package org.ndexbio.model.object;

import java.util.List;

public interface PropertiedObject {

	public List<NdexPropertyValuePair>  getProperties();
	
	public List<SimplePropertyValuePair>  getPresentationProperties();
	
	public void setProperties(List<NdexPropertyValuePair> properties);

	public void setPresentationProperties(List<SimplePropertyValuePair> properties);
}
