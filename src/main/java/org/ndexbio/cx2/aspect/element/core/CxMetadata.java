package org.ndexbio.cx2.aspect.element.core;

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

/*	public String getAspectName() {
		return "metaData";
	}
*/
}
