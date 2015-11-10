package org.ndexbio.model.cx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.cxio.aspects.datamodels.AbstractAspectElement;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SupportLinksElement extends AbstractAspectElement {

	@JsonProperty("po")	
	private Collection<Long> sourceIds;
	
	@JsonProperty("supports")	
	private Collection<Long> supportIds;
	
	public SupportLinksElement() {
		supportIds = new LinkedList<>();
	}

	protected SupportLinksElement(Collection<Long> sourceId, Collection<Long> supportIds ) {
		this.sourceIds = sourceId;
		this.supportIds = supportIds;
	}

	protected SupportLinksElement(Long sourceId, Collection<Long> supportIds ) {	
		this.sourceIds = new ArrayList<> (1);
		this.sourceIds.add(sourceId);
		this.supportIds = supportIds;
	}

	public Collection<Long> getSourceIds() {
		return sourceIds;
	}


	public void setSourceIds(Collection<Long> sourceId) {
		this.sourceIds = sourceId;
	}


	public Collection<Long> getSupportIds() {
		return supportIds;
	}


	public void setSupportIds(Collection<Long> supportIds) {
		this.supportIds = supportIds;
	}

}
