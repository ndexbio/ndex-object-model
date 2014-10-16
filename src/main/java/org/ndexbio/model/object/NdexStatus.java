package org.ndexbio.model.object;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NdexStatus extends NdexObject
{
    private int _networkCount;
    private int _userCount;
    private int _groupCount;

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



	public int getNetworkCount() {
		return _networkCount;
	}



	public void setNetworkCount(int networkCount) {
		this._networkCount = networkCount;
	}



	public int getUserCount() {
		return _userCount;
	}



	public void setUserCount(int userCount) {
		this._userCount = userCount;
	}



	public int getGroupCount() {
		return _groupCount;
	}



	public void setGroupCount(int groupCount) {
		this._groupCount = groupCount;
	}


	public Map<String,String> getProperties() {
		return _properties;
	}



	public void setProperties(Map<String,String> properties) {
		this._properties = properties;
	}



	public String getMessage() {
		return _message;
	}



	public void setMessage(String message) {
		this._message = message;
	}



}
