package org.ndexbio.model.cx;

import java.util.Collection;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CitationLinksElement implements AspectElement {
	
	@JsonProperty("po")	
	private Collection<String> sourceIds ;
	
	@JsonProperty("citations")	
	private Collection<String> citationId; 

	public CitationLinksElement() {
	}

	public CitationLinksElement(Collection<String> sourceIds, Collection<String> citationIds) {
		this.sourceIds = sourceIds;
		citationId = citationIds;
	}


	public Collection<String> getSourceIds() {
		return sourceIds;
	}

	public void setSourceIds(Collection<String> sourceId) {
		this.sourceIds = sourceId;
	}

	public Collection<String> getCitationIds() {
		return citationId;
	}

	public void setCitationIds(Collection<String> citationId) {
		this.citationId = citationId;
	}

}
