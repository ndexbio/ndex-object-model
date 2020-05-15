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
	
	private final static int distinctCount = 30;
	
	public AspectAttributeStatEntry() { 
		valueHolder = new HashMap<>(distinctCount);
		datatype = null;
		alias = null;
	} 
	
	public void addDatatype (ATTRIBUTE_DATA_TYPE dataType) throws NdexException {
		if ( datatype == null)
		   this.datatype = dataType;
		else {
			if ( datatype != dataType) 
				throw new NdexException ("Attribure has inconsistent datatype between " + datatype 
						+ " and " + dataType);
		}
	}
	
	public void addValue (Object v) {
		Long s1 = valueHolder.get(v);
		if ( s1 == null ) {
			if ( valueHolder.size() < distinctCount)
				valueHolder.put(v, Long.valueOf(1));
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
	
	public Object getDefaultValue () { 
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
