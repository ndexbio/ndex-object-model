package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents networks for a given database
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseResult {
    private String _uuid;
    private String _description;
    private String _name;
    private String _numberOfNetworks;
    private String _imageURL;
    private String _legendURL;
    private List<NetworkInfo> _networks;
    private String _url;
	
	/**
	 * Constructor
	 */
	public DatabaseResult(){
		
	}
	
	/**
	 * Copy constructor, that performs a deep copy
	 * 
	 * @param dr DatabaseResult to copy
	 */
	public DatabaseResult(DatabaseResult dr){
		this(dr, false);
	}
	
	/**
	 * Copy constructor that performs a deep copy DatabaseResult passed in
	 * with the option to omit copying the networks via the {@code skipNetworks}
	 * flag
	 * 
	 * @param dr DatabaseResult to copy
	 * @param skipNetworks If {@code true} skip the copy of networks
	 */
	public DatabaseResult(DatabaseResult dr, boolean skipNetworks){
		if (dr == null){
			return;
		}
		_uuid = dr.getUuid();
		_description = dr.getDescription();
		_name = dr.getName();
		_numberOfNetworks = dr.getNumberOfNetworks();
		_imageURL = dr.getImageURL();
		_legendURL = dr.getLegendURL();
		_url = dr.getUrl();
		
		if (skipNetworks == true){
			return;
		}
		if (dr.getNetworks() != null){
			_networks = new ArrayList<>();
			for (NetworkInfo ni : dr.getNetworks()){
				NetworkInfo copyNI = new NetworkInfo();
				copyNI.setDescription(ni.getDescription());
				copyNI.setEdgeCount(ni.getEdgeCount());
				copyNI.setGeneCount(ni.getGeneCount());
				copyNI.setImageUrl(ni.getImageUrl());
				copyNI.setLegendUrl(ni.getLegendUrl());
				copyNI.setName(ni.getName());
				copyNI.setNodeCount(ni.getNodeCount());
				copyNI.setUrl(ni.getUrl());
				copyNI.setUuid(ni.getUuid());
				_networks.add(copyNI);
			}
		}
	}
    
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

    public List<NetworkInfo> getNetworks() {
        return _networks;
    }

    public void setNetworks(List<NetworkInfo> _networks) {
        this._networks = _networks;
    }

    public String getImageURL() {
        return _imageURL;
    }

    public void setImageURL(String _imageURL) {
        this._imageURL = _imageURL;
    }
    
    public String getUrl() {
    	return _url;
    }
    
    public void setUrl(String _url) {
    	this._url = _url;
    }
    
    public String getNumberOfNetworks() {
    	return _numberOfNetworks;
    }
    
    public void setNumberOfNetworks(String _numberOfNetworks) {
    	this._numberOfNetworks = _numberOfNetworks;
    }

	public String getLegendURL() {
		return _legendURL;
	}

	public void setLegendURL(String _legendURL) {
		this._legendURL = _legendURL;
	}
}
