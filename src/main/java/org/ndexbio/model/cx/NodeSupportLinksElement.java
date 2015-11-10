package org.ndexbio.model.cx;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NodeSupportLinksElement extends SupportLinksElement {

	public static final String ASPECT_NAME = "nodeSupports";
	public NodeSupportLinksElement() {
	}
	
	public NodeSupportLinksElement(Collection<Long> sourceId, Collection<Long> supportIds ) {
		super(sourceId,supportIds);
	}
	
	public NodeSupportLinksElement(Long sourceId, Collection<Long> supportIds ) {
		super(sourceId,supportIds);
	}

	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

}
