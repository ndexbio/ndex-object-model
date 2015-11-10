package org.ndexbio.model.cx;

import java.util.Collection;
import java.util.LinkedList;

import org.cxio.aspects.datamodels.AbstractAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CitationLinksElement extends AbstractAspectElement {
	
	@JsonProperty("po")	
	private Collection<Long> sourceIds ;
	
	@JsonProperty("citations")	
	private Collection<Long> citationIds; 

	public CitationLinksElement() {
		citationIds = new LinkedList<>();
	}

	public CitationLinksElement(Collection<Long> sourceIds, Collection<Long> citationIds) {
		this.sourceIds = sourceIds;
		this.citationIds = citationIds;
	}


	public Collection<Long> getSourceIds() {
		return sourceIds;
	}

	public void setSourceIds(Collection<Long> sourceId) {
		this.sourceIds = sourceId;
	}

	@JsonIgnore
	public Collection<Long> getCitationIds() {
		return citationIds;
	}

	@JsonIgnore
	public void setCitationIds(Collection<Long> citationId) {
		this.citationIds = citationId;
	}

}
