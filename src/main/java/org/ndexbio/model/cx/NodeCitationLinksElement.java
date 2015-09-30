package org.ndexbio.model.cx;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NodeCitationLinksElement extends CitationLinksElement {

	public static final String NAME = "nodeCitations";
	public NodeCitationLinksElement() {
	}

	public NodeCitationLinksElement(Collection<String> sourceId, Collection<String> citationIds ) {
		super(sourceId,citationIds);
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return  NAME;
		
	}

}
