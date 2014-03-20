package org.ndexbio.model.object;


import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NdexStatus extends NdexObject
{
    private Integer _networkCount;
    private Integer _userCount;
    private Integer _groupCount;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public NdexStatus()
    {
        super();
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




}
