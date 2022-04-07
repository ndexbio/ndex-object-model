package org.ndexbio.cx2.aspect.element.core;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.cxio.metadata.MetaDataCollection;
import org.ndexbio.cxio.metadata.MetaDataElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CxMetadata implements Comparable<CxMetadata> {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("elementCount")
	private Long elementCount;
	
	public CxMetadata() {}

	public CxMetadata (MetaDataElement mde) {
		elementCount = mde.getElementCount();
		name = mde.getName();
		
	}
	
	public CxMetadata (String aspect, long count) {
		this.name = aspect;
		this.elementCount = Long.valueOf(count);
		
	}
	
	public CxMetadata (String aspect) {
		this.name = aspect;
		this.elementCount= null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getElementCount() {
		return elementCount;
	}

	public void setElementCount(Long elementCount) {
		this.elementCount = elementCount;
	}
	
	public MetaDataElement toMetaDataElement () {
		MetaDataElement mde = new MetaDataElement(name, "1.0");
		mde.setElementCount(elementCount);
		return mde;
	}
	
	public static List<CxMetadata> createCxMetadataListFromMetedataCollection(MetaDataCollection c) {
		List<MetaDataElement > md = c.getMetaData();
		List<CxMetadata> result = new ArrayList<>(md.size());
		for (MetaDataElement e : md) {
			result.add(new CxMetadata(e));
		}
		return result;
	}
	
	@Override
	public boolean equals(Object m2) {
		if ( m2 instanceof CxMetadata) {
			CxMetadata md2 = (CxMetadata)m2;
			if ( name!= null && elementCount !=null && md2.getElementCount() !=null 
					&& md2.getName() !=null )
				return this.name.equals(md2.getName()) && elementCount.equals(md2.getElementCount());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (name != null) ? 
			name.hashCode() : super.hashCode();
	}

	@Override
	public int compareTo(CxMetadata o) {
		String n2= o.getName();
		Long c2 = o.getElementCount();
		
		if ( name == null ) {
			if ( n2 == null) {
				
				if (elementCount == null ) {
					return  c2 == null ? 0 : -1;
				}
				
				return c2 == null ? 1: elementCount.compareTo(c2);
			}
			return  -1;
		}
		
		if ( n2 == null)
			return 1;
		int r = name.compareTo(n2);
		if ( r != 0)
			return r;
		
		if ( c2 == null)
			return 1;
		
		return elementCount.compareTo(c2);
	}


}
