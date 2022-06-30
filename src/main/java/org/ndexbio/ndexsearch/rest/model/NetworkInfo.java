package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Pojo containing summary information about a given network
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkInfo {
	private String _uuid;
	private String _description;
	private String _name;
	private String _url;
	private String _imageUrl;
	private String _legendUrl;
	
	private int _geneCount;
	private int _nodeCount;
	private int _edgeCount;
	
	/**
	 * Gets NDEx UUID of network
	 * @return 
	 */
	public String getUuid() {
		return _uuid;
	}
	
	/**
	 * Sets NDEx UUID of network
	 * @param _uuid 
	 */
	public void setUuid(String _uuid) {
		this._uuid = _uuid;
	}
	
	/**
	 * Gets network description
	 * @return
	 */
	public String getDescription() {
		return _description;
	}
	
	/**
	 * Sets network description
	 * @param _description
	 */
	public void setDescription(String _description) {
		this._description = _description;
	}
	
	/**
	 * Gets network name
	 * @return
	 */
	public String getName() {
		return _name;
	}
	
	/***
	 * Sets network name
	 * @param _name 
	 */
	public void setName(String _name) {
		this._name = _name;
	}
	
	/**
	 * Gets NDEx URL for network
	 * 
	 * @return 
	 */
	public String getUrl() {
		return _url;
	}
	
	/**
	 * Sets NDEx URL for network
	 * 
	 * @param _url 
	 */
	public void setUrl(String _url) {
		this._url = _url;
	}
	
	/**
	 * Gets URL to an image icon file for network
	 * 
	 * @return 
	 */
	public String getImageUrl() {
		return _imageUrl;
	}

	/**
	 * Sets URL to an image icon file for network
	 * @param _imageUrl 
	 */
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

	/**
	 * Gets number of nodes in network
	 * @return 
	 */
	public int getNodeCount() {
		return _nodeCount;
	}

	/**
	 * Sets number of nodes in network
	 * @param _nodeCount 
	 */
	public void setNodeCount(int _nodeCount) {
		this._nodeCount = _nodeCount;
	}

	/**
	 * Gets edge count of network
	 * @return 
	 */
	public int getEdgeCount() {
		return _edgeCount;
	}

	/**
	 * Sets edge count of network
	 * @param _edgeCount 
	 */
	public void setEdgeCount(int _edgeCount) {
		this._edgeCount = _edgeCount;
	}

	public String getLegendUrl() {
		return _legendUrl;
	}

	public void setLegendUrl(String _legendUrl) {
		this._legendUrl = _legendUrl;
	}
}
