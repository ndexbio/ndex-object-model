package org.ndexbio.cx2.aspect.element.core;

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

/*	public String getAspectName() {
		return "metaData";
	}
*/
}
