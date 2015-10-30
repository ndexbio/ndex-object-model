package org.ndexbio.model.cx;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EdgeCitationLinksElement extends CitationLinksElement {

	public final static String ASPECT_NAME = "edgeCitations";
	public EdgeCitationLinksElement() {
	}

	public EdgeCitationLinksElement(Collection<String> sourceId, Collection<String> citationIds ) {
		super(sourceId,citationIds);
	}
	
	public EdgeCitationLinksElement(String sourceId, Collection<String> citationIds ) {
		Collection<String> srcIds = new ArrayList<>(1);
		srcIds.add(sourceId);
		setSourceIds(srcIds);
		setCitationIds(citationIds);
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

}
