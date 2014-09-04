package org.ndexbio.model.tools;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.SimplePropertyValuePair;

public class PropertyHelpers {

	public static List<SimplePropertyValuePair> copyProperties(List<SimplePropertyValuePair> properties) {
		List<SimplePropertyValuePair> copiedProperties = new ArrayList<SimplePropertyValuePair>();
		for (SimplePropertyValuePair prop : properties){
			if (null != prop){
				copiedProperties.add(copyProperty(prop));
			}
		}		
		return copiedProperties;
	}
	
	public static SimplePropertyValuePair copyProperty(SimplePropertyValuePair property){
		if (null == property) return null;
		SimplePropertyValuePair copiedProperty = new SimplePropertyValuePair();
		
		
		//private String _predicateString;
		if (null != property.getName()){
			copiedProperty.setName(new String(property.getName()));
		}
		
		//private String _value;
		if (null != property.getValue()){
			copiedProperty.setValue(new String(property.getValue()));
		}
		
		return copiedProperty;
		
	}

}
