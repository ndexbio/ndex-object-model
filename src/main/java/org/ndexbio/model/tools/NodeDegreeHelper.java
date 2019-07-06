package org.ndexbio.model.tools;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a helper class for Interconnect queries. 
 * 
 * @author chenjing
 *
 */
public class NodeDegreeHelper {
	
	private boolean tobeDeleted;
	private Long nodeId;
	private List<Long> edgeIds;
	

	public NodeDegreeHelper (Long newNodeId, Long newEdgeId) {
		this.setToBeDeleted(true);
		edgeIds = new ArrayList<>();
		this.nodeId = newNodeId;
		edgeIds.add(newEdgeId);
	}

	public boolean isToBeDeleted() {
		return tobeDeleted;
	}

	public void setToBeDeleted(boolean toBeDeleted) {
		this.tobeDeleted = toBeDeleted;
	}

	public Long getNodeId() {
		return nodeId;
	}


	public List<Long> getEdgeIds() {
		return edgeIds;
	}
	
	public void addEdge(Long id) { this.edgeIds.add(id); }
	
	/**
	 * Warning: after calling this function, the get/add edge function should not be call anymore.
	 * TODO: make this class safer later when we want reuse his class in other places.
	 */
	public void removeAllEdges() {edgeIds = null;}

}