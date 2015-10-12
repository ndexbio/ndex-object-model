package org.ndexbio.model.cx;

import java.util.ArrayList;
import java.util.Collection;

import org.cxio.aspects.datamodels.AbstractAspectElement;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SupportLinksElement extends AbstractAspectElement {

	@JsonProperty("po")	
	private Collection<String> sourceIds;
	
	@JsonProperty("supports")	
	private Collection<String> supportIds;
	
	public SupportLinksElement() {
	}

	protected SupportLinksElement(Collection<String> sourceId, Collection<String> supportIds ) {
		this.sourceIds = sourceId;
		this.supportIds = supportIds;
	}

	protected SupportLinksElement(String sourceId, Collection<String> supportIds ) {	
		this.sourceIds = new ArrayList<> (1);
		this.sourceIds.add(sourceId);
		this.supportIds = supportIds;
	}

	public Collection<String> getSourceIds() {
		return sourceIds;
	}


	public void setSourceIds(Collection<String> sourceId) {
		this.sourceIds = sourceId;
	}


	public Collection<String> getSupportIds() {
		return supportIds;
	}


	public void setSupportIds(Collection<String> supportIds) {
		this.supportIds = supportIds;
	}

}
