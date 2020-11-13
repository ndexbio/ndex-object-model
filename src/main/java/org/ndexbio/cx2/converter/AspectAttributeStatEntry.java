package org.ndexbio.cx2.converter;

import java.util.HashMap;
import java.util.Map;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

// stores stats of attribute used in nodes and edges
public class AspectAttributeStatEntry {
	
	private ATTRIBUTE_DATA_TYPE datatype;
	
	private String alias;

	Map<Object, Long> valueHolder;
	
	//Threshold for trying to create default values. If the distinct value 
	// count is over this we will not try to create a default value for this field.
	private final static int distinctCount = 30;
	
	// at the end this value should match with total node/edge count if a default value can be generated.
	private long valueCount;
	
	public AspectAttributeStatEntry() { 
		valueHolder = new HashMap<>(distinctCount);
		datatype = null;
		alias = null;
		valueCount = 0;
	} 
	
	/**
	 * Add check the data type of an attribute. 
	 * @param dataType
	 * @return error message when data type is inconsistent in the CX. null if no errors were found. 
	 */
	public String addDatatype (ATTRIBUTE_DATA_TYPE dataType) {
		if ( datatype == null)
		   this.datatype = dataType;
		else {
			if ( datatype != dataType) 
				return ("data type " + dataType + " is inconsistent with previously declared data type " + datatype);
		}
		return null;
	}
	
	protected void addValue (Object v) {
	    
		if ( valueHolder == null) // we no longer want to find default value for this field.
	    	return;
		
		valueCount++;
		Long s1 = valueHolder.get(v);
		if ( s1 == null ) {
			if ( valueHolder.size() < distinctCount)
				valueHolder.put(v, Long.valueOf(1));
			else
				valueHolder = null;   // flag that we don't want to check the value any more.
		} else {
			valueHolder.put(v, s1.longValue() + 1);			
		} 
	}
	
	public ATTRIBUTE_DATA_TYPE getDataType () {return datatype;}
	
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	protected Object getDefaultValue (long totalCount) {
		if (totalCount != valueCount || valueHolder == null)
			return null;
		long c = 0;
		Object result = null;
		for ( Map.Entry<Object, Long>e : valueHolder.entrySet()) {
			long ct = e.getValue().longValue();
			if ( ct > c) {
				c = ct;
				result = e.getKey();	
			}	
		}
		if ( c > 50 )
			return result;
		return null;
	}

}
