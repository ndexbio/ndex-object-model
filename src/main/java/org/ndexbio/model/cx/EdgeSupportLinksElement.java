package org.ndexbio.model.cx;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EdgeSupportLinksElement extends SupportLinksElement {

	public static final String ASPECT_NAME = "edgeSupports";
	public EdgeSupportLinksElement() {
	}

	public EdgeSupportLinksElement(Collection<Long> sourceId, Collection<Long> supportIds ) {
		super(sourceId,supportIds);
	}
	
	public EdgeSupportLinksElement(long sourceId, Collection<Long> supportIds ) {
		super(sourceId,supportIds);
	}
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

}
