package org.ndexbio.model.network.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class EdgeCollectionQuery {

	private EdgeByNodePropertyFilter _nodeFilter;
	private EdgeByEdgePropertyFilter _edgeFilter;
	private String _queryName;
	
	private int _edgeLimit; // -1 means no limit;

	public EdgeByNodePropertyFilter getNodeFilter() {
		return _nodeFilter;
	}

	public void setNodeFilter(EdgeByNodePropertyFilter nodeFilter) {
		this._nodeFilter = nodeFilter;
	}

	public EdgeByEdgePropertyFilter getEdgeFilter() {
		return _edgeFilter;
	}

	public void setEdgeFilter(EdgeByEdgePropertyFilter edgeFilter) {
		this._edgeFilter = edgeFilter;
	}

	public int getEdgeLimit() {
		return _edgeLimit;
	}

	public void setEdgeLimit(int edgeLimit) {
		this._edgeLimit = edgeLimit;
	}

	public String getQueryName() {
		return _queryName;
	}

	public void setQueryName(String queryName) {
		this._queryName = queryName;
	}
	
}
