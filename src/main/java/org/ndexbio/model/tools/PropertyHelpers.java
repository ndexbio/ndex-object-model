package org.ndexbio.model.tools;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexProperty;

public class PropertyHelpers {

	public static List<NdexProperty> copyProperties(List<NdexProperty> properties) {
		List<NdexProperty> copiedProperties = new ArrayList<NdexProperty>();
		for (NdexProperty prop : properties){
			if (null != prop){
				copiedProperties.add(copyProperty(prop));
			}
		}		
		return copiedProperties;
	}
	
	public static NdexProperty copyProperty(NdexProperty property){
		if (null == property) return null;
		NdexProperty copiedProperty = new NdexProperty();
		
		//private long   _predicateId;
		copiedProperty.setPredicateId(property.getPredicateId());
		
		//private String _predicateString;
		if (null != property.getPredicateString()){
			copiedProperty.setPredicateString(new String(property.getPredicateString()));
		}
		
		//private long _valueId;
		copiedProperty.setValueId(property.getValueId());
		
		//private String _value;
		if (null != property.getValue()){
			copiedProperty.setValue(new String(property.getValue()));
		}
		
		//private String _dataType;
		if (null != property.getDataType()){
			copiedProperty.setDataType(new String(property.getDataType()));
		}
		
		return copiedProperty;
		
	}

}
