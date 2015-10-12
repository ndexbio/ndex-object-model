package org.ndexbio.model.cx;

import org.cxio.aspects.datamodels.AbstractAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReifiedEdgeElement extends AbstractAspectElement {

	final public static String NAME  = "reifiedEdges";
	
	private String edge;
	private String node;
	
	public ReifiedEdgeElement() {
	}

	public ReifiedEdgeElement(String nodeId, String edgeId) {
		this.edge=edgeId;
		this.node=nodeId;
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return NAME;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}
