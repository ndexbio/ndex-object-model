package org.ndexbio.model.cx;

import java.util.Collection;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class SupportLinksElement implements AspectElement {

	private String sourceId;
	
	private Collection<String> supportId;
	
	public SupportLinksElement() {
	}

	public SupportLinksElement(String sourceId, Collection<String> supportIds ) {
		this.sourceId = sourceId;
		this.supportId = supportIds;
	}
	
	@Override
	@JsonIgnore
	public long getSum() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getSourceId() {
		return sourceId;
	}


	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}


	public Collection<String> getSupportIds() {
		return supportId;
	}


	public void setSupportIds(Collection<String> supportIds) {
		this.supportId = supportIds;
	}

}
