package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimplePathQuery extends SimpleQuery {

	private int _searchDepth;
	private int _edgeLimit;  // this is a temporary attribute, might be removed in future releases. 
	
	
	public void setSearchDepth(int searchDepth) {
		_searchDepth = searchDepth;
	}
	
	public int getSearchDepth() {
		return _searchDepth;
	}

	public int getEdgeLimit() {
		return _edgeLimit;
	}

	public void setEdgeLimit(int edgeLimit) {
		this._edgeLimit = edgeLimit;
	}
	
}
