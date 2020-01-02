/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.ndexsearch.rest.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author churas
 */
public class SourceQueryResult {
    private String _networkUUID;
    private String _description;
    private String _imageURL;
    private String _url;
    private int _percentOverlap;
    private int _nodes;
    private int _edges;
    private int _rank;
    private Set<String> _hitGenes;
    private Map<String,Object> _details;


    public SourceQueryResult() {
    	_details= new TreeMap<>();
    }
    
    public String getNetworkUUID() {
        return _networkUUID;
    }

    public void setNetworkUUID(String _networkUUID) {
        this._networkUUID = _networkUUID;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public String getImageURL() {
        return _imageURL;
    }

    public void setImageURL(String _imageURL) {
        this._imageURL = _imageURL;
    }


    public int getPercentOverlap() {
        return _percentOverlap;
    }

    public void setPercentOverlap(int _percentOverlap) {
        this._percentOverlap = _percentOverlap;
    }

    public int getNodes() {
        return _nodes;
    }

    public void setNodes(int _nodes) {
        this._nodes = _nodes;
    }

    public int getEdges() {
        return _edges;
    }

    public void setEdges(int _edges) {
        this._edges = _edges;
    }

    public int getRank() {
        return _rank;
    }

    public void setRank(int _rank) {
        this._rank = _rank;
    }

    public Set<String> getHitGenes() {
        return _hitGenes;
    }

    public void setHitGenes(Set<String> _hitGenes) {
        this._hitGenes = _hitGenes;
    }

	public Map<String,Object> getDetails() {
		return _details;
	}

	public void setDetails(Map<String,Object> _details) {
		this._details = _details;
	}
	
	public String getUrl() {
		return _url;
	}

	public void setUrl(String _url) {
		this._url = _url;
	}
}
