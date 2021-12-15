package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


/**
 *
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseResult {
    private String _uuid;
    private String _description;
    private String _name;
    private String _numberOfNetworks;
    private String _imageURL;
    private List<NetworkInfo> _networks;
    private String _url;
    
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
}
