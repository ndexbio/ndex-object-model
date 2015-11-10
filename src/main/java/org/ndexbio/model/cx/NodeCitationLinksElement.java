package org.ndexbio.model.cx;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NodeCitationLinksElement extends CitationLinksElement {

	public static final String ASPECT_NAME = "nodeCitations";
	public NodeCitationLinksElement() {
	}

	public NodeCitationLinksElement(Collection<Long> sourceId, Collection<Long> citationIds ) {
		super(sourceId,citationIds);
	}
	
	public NodeCitationLinksElement(Long sourceId, Collection<Long> citationIds ) {
		Collection<Long> srcIds = new ArrayList<>(1);
		srcIds.add(sourceId);
		setSourceIds(srcIds);
		setCitationIds(citationIds);
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return  ASPECT_NAME;
		
	}

}
