package org.ndexbio.model.cx;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CXSupport {
	private static final String id = "t";

	@JsonProperty("n")
	private Collection<String> nodes;
	@JsonProperty("e")
	private Collection<String> edges;
	
	@JsonProperty ( id )
	private String text;
	
	
	public CXSupport() {
	}


	public Collection<String> getNodes() {
		return nodes;
	}


	public void setNodes(Collection<String> nodes) {
		this.nodes = nodes;
	}


	public Collection<String> getEdges() {
		return edges;
	}


	public void setEdges(Collection<String> edges) {
		this.edges = edges;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

}
