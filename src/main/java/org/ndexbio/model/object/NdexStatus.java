/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
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
