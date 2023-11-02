package org.ndexbio.model.network.query;

import java.util.Set;

public class FilteredDirectQuery extends FilterCriterion {
	Set<Long> nodeIds;

	public Set<Long> getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(Set<Long> nodeIds) {
		this.nodeIds = nodeIds;
	}

}
