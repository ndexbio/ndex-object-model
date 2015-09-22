package org.ndexbio.model.cx;

import java.util.Collection;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class CitationLinksElement implements AspectElement {
	
	private String sourceId ;
	
	private Collection<String> citationId; 

	public CitationLinksElement() {
	}

	public CitationLinksElement(String sourceId, Collection<String> citationIds) {
		this.sourceId = sourceId;
		citationId = citationIds;
	}


	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Collection<String> getCitationIds() {
		return citationId;
	}

	public void setCitationIds(Collection<String> citationId) {
		this.citationId = citationId;
	}

}
