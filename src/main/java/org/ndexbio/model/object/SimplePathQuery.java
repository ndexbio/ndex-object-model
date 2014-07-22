package org.ndexbio.model.object;

public class SimplePathQuery extends SimpleQuery {

	private int _searchDepth;
	
	public void setSearchDepth(int searchDepth) {
		_searchDepth = searchDepth;
	}
	
	public int getSearchDepth() {
		return _searchDepth;
	}
	
}
