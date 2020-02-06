package org.ndexbio.ndexsearch.rest.model;

public class NetworkInfo {
	private String _uuid;
	private String _description;
	private String _name;
	private String _url;
	private String _imageUrl;
	private int _geneCount;
	
	public String getUuid() {
		return _uuid;
	}
	
	public void setUuid(String _uuid) {
		this._uuid = _uuid;
	}
	
	public String getDescription() {
		return _description;
	}
	
	public void setDescription(String _description) {
		this._description = _description;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String _name) {
		this._name = _name;
	}
	
	public String getUrl() {
		return _url;
	}
	
	public void setUrl(String _url) {
		this._url = _url;
	}
	
	public String getImageUrl() {
		return _imageUrl;
	}
	
	public void setImageUrl(String _imageUrl) {
		this._imageUrl = _imageUrl;
	}

	/**
	 * Gets number of genes found in network
	 * @return 
	 */
	public int getGeneCount() {
		return _geneCount;
	}

	/**
	 * Sets number of genes found in network
	 * 
	 * @param _geneCount 
	 */
	public void setGeneCount(int _geneCount) {
		this._geneCount = _geneCount;
	}
	
	
}
