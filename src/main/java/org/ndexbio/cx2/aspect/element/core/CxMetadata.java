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

public class CxMetadata {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("elementCount")
	private Long elementCount;
	
	public CxMetadata() {}

	public CxMetadata (MetaDataElement mde) {
		elementCount = mde.getElementCount();
		name = mde.getName();
		
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

}
