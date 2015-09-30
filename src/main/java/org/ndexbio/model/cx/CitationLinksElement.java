package org.ndexbio.model.cx;

import java.util.Collection;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CitationLinksElement implements AspectElement {
	
	@JsonProperty("po")	
	private Collection<String> sourceIds ;
	
	@JsonProperty("citations")	
	private Collection<String> citationIds; 

	public CitationLinksElement() {
	}

	public CitationLinksElement(Collection<String> sourceIds, Collection<String> citationIds) {
		this.sourceIds = sourceIds;
		this.citationIds = citationIds;
	}


	public Collection<String> getSourceIds() {
		return sourceIds;
	}

	public void setSourceIds(Collection<String> sourceId) {
		this.sourceIds = sourceId;
	}

	@JsonIgnore
	public Collection<String> getCitationIds() {
		return citationIds;
	}

	@JsonIgnore
	public void setCitationIds(Collection<String> citationId) {
		this.citationIds = citationId;
	}

}
