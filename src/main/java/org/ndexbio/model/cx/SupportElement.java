package org.ndexbio.model.cx;

import java.util.Collection;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SupportElement implements AspectElement{
	
	public static final String NAME = "Support";
	
	private static final String tField = "t";

	/*
	@JsonProperty("n")
	private Collection<String> nodes;
	@JsonProperty("e")
	private Collection<String> edges; */
	
	@JsonProperty ( tField )
	private String text;
	
	@JsonProperty( "citation")
	private String citationId;
	
	
	public SupportElement() {
	}

/*
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

*/
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getCitationId() {
		return citationId;
	}


	public void setCitationId(String citationId) {
		this.citationId = citationId;
	}


	@Override
	public String getAspectName() {
		return NAME;
	}


	@Override
	public long getSum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
