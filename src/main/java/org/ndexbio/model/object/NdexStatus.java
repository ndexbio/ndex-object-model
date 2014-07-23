package org.ndexbio.model.object;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NdexStatus extends NdexObject
{
    private Integer _networkCount;
    private Integer _userCount;
    private Integer _groupCount;

    private Map<String,String> _properties;
    
    private String _message;

    private final static String online= "Online";

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public NdexStatus()
    {
        super();
        _properties = new HashMap<String,String>();
        _message = online;
    }



	public Integer getNetworkCount() {
		return _networkCount;
	}



	public void setNetworkCount(Integer _networkCount) {
		this._networkCount = _networkCount;
	}



	public Integer getUserCount() {
		return _userCount;
	}



	public void setUserCount(Integer _userCount) {
		this._userCount = _userCount;
	}



	public Integer getGroupCount() {
		return _groupCount;
	}



	public void setGroupCount(Integer _groupCount) {
		this._groupCount = _groupCount;
	}


	public Map<String,String> getProperties() {
		return _properties;
	}



	public void setProperties(Map<String,String> _properties) {
		this._properties = _properties;
	}



	public String getMessage() {
		return _message;
	}



	public void setMessage(String _message) {
		this._message = _message;
	}



}
