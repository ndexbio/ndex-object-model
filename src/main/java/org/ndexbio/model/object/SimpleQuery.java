package org.ndexbio.model.object;


public abstract class SimpleQuery {
	
	private String _searchString;
	
	public void setSearchString(String searchString) {
		_searchString = searchString;
	}
	
	public String getSearchString() {
		return _searchString;
	}
	
}
