package org.ndexbio.model.cx;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NodeSupportLinksElement extends SupportLinksElement {

	public static final String NAME = "NodeSupports";
	public NodeSupportLinksElement() {
	}
	
	public NodeSupportLinksElement(String sourceId, Collection<String> supportIds ) {
		super(sourceId,supportIds);
	}
	

	@Override
	@JsonIgnore
	public String getAspectName() {
		return NAME;
	}

}
