package org.ndexbio.model.object.network;

import java.util.Map;
import java.util.TreeMap;

import org.ndexbio.cx2.aspect.element.core.CxNetworkAttribute;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class NetworkProperties {

	private Map<String, Map<String,Object>> propertyTable;
	
	
	public NetworkProperties() {
		propertyTable = new TreeMap<>();
	}
	
	@JsonAnyGetter
	public Map<String, Map<String,Object>> getProperties() {
		return propertyTable;
	}
	
	@JsonAnySetter
	public void add(String key, Map<String,Object> e) {		
		Object typeString = e.get("t");
		if( typeString == null)
			throw new IllegalStateException("Type (attribute 't') is missing for network property " + key + ".");
		if( !(typeString instanceof String))
			throw new IllegalStateException("Attribute 't' can have a string type value in network property.");
		
		ATTRIBUTE_DATA_TYPE t = ATTRIBUTE_DATA_TYPE.fromCxLabel((String)typeString);
		
		// check type for name, description, and version 
		if ( (key.equals(CxNetworkAttribute.nameAttribute) || key.equals(CxNetworkAttribute.descriptionAttribute) ||
				key.equals(CxNetworkAttribute.versionAttribute)) && t!=ATTRIBUTE_DATA_TYPE.STRING) {
			throw new IllegalStateException("Network name, description or version has to be a string type attribute.");
		}
			
		Object value = e.get("v");
		if ( value == null)
			throw new IllegalStateException("Value is missing in network property '" + key + "'");
		
		
	    propertyTable.put(key, e);
	}
 
	@JsonIgnore
	public void setProperty(String key, String type, Object value) {
		
		Map<String, Object> v = new TreeMap<>();
		v.put("t", type);
		v.put("v", value);
		add(key, v);
		
	}
}
