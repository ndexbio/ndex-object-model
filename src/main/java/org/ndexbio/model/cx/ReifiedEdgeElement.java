package org.ndexbio.model.cx;

import org.cxio.aspects.datamodels.AbstractAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReifiedEdgeElement extends AbstractAspectElement {

	final public static String ASPECT_NAME  = "reifiedEdges";
	
	private long edge;
	private long node;
	
	public ReifiedEdgeElement() {
	}

	public ReifiedEdgeElement(long nodeId, long edgeId) {
		this.edge=edgeId;
		this.node=nodeId;
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	public long getEdge() {
		return edge;
	}

	public void setEdge(long edge) {
		this.edge = edge;
	}

	public long getNode() {
		return node;
	}

	public void setNode(long node) {
		this.node = node;
	}

}
