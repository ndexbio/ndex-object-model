package org.ndexbio.model.cx;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EdgeSupportLinksElement extends SupportLinksElement {

	public static final String NAME = "edgeSupports";
	public EdgeSupportLinksElement() {
	}

	public EdgeSupportLinksElement(String sourceId, Collection<String> supportIds ) {
		super(sourceId,supportIds);
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return NAME;
	}

}
