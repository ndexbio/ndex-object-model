/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.object.network;


import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexExternalObject;
import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.PropertiedObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class NetworkSummary extends NdexExternalObject implements PropertiedObject {

    private String _description;
    private int _edgeCount;
    private boolean _isComplete;
    private boolean _isLocked;
    private VisibilityType _visibility;
    private long _readOnlyCommitId;
    private long _readOnlyCacheId;
    private String _name;
    private int _nodeCount;
    private String _owner;
    
    private String _URI;
    
//	private long _highestElementId;
	private String _version;
	
	private List<NdexPropertyValuePair> _properties;
//	private List<SimplePropertyValuePair> _presentationProperties;
//    private Map<String, MetaDataElement> _aspectDictionary;

	public NetworkSummary () {
		super();
//        _type = this.getClass().getSimpleName();

        _isComplete = false;
        _isLocked = false;
        _readOnlyCommitId = -1;
        _readOnlyCacheId  = -1;
    //    setVisibility(VisibilityType.PRIVATE);
        _edgeCount = 0;
        _nodeCount = 0;
        
        _properties = new ArrayList<> (10);
//    	_presentationProperties = new ArrayList<> (10);

	}

    public String getDescription()
    {
        return _description;
    }
    
    public void setDescription(String description)
    {
        _description = description;
    }
    
    public int getEdgeCount()
    {
        return _edgeCount;
    }

    public void setEdgeCount(int edgeCount)
    {
        _edgeCount = edgeCount;
    }
	
	
	public String getVersion() {
		return _version;
	}


	public void setVersion(String version) {
		this._version = version;
	}


	public VisibilityType getVisibility() {
		return _visibility;
	}


	public void setVisibility(VisibilityType visibility) {
		this._visibility = visibility;
	}

    public boolean getIsComplete()
    {
        return _isComplete;
    }
    
    public void setIsComplete(boolean isComplete)
    {
        _isComplete = isComplete;
    }

    public boolean getIsLocked()
    {
        return _isLocked;
    }
    
    public void setIsLocked(boolean isLocked)
    {
        _isLocked = isLocked;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public void setName(String name)
    {
        _name = name;
    }

    public int getNodeCount()
    {
        return _nodeCount;
    }

    public void setNodeCount(int nodeCount)
    {
        _nodeCount = nodeCount;
    }

	@Override
	public List<NdexPropertyValuePair> getProperties() {
		return _properties;
	}

/*	@Override
	public List<SimplePropertyValuePair> getPresentationProperties() {
		return _presentationProperties;
	} */

	@Override
	public void setProperties(List<NdexPropertyValuePair> properties) {
		_properties = properties;
	}

/*	@Override
	public void setPresentationProperties(List<SimplePropertyValuePair> properties) {
		_presentationProperties = properties;
	} */

	public String getURI() {
		return _URI;
	}

	public void setURI(String URI) {
		this._URI = URI;
	}

	public String getOwner() {
		return _owner;
	}

	public void setOwner(String owner) {
		this._owner = owner;
	}

	public long getReadOnlyCommitId() {
		return _readOnlyCommitId;
	}

	public void setReadOnlyCommitId(long readOnlyCommitId) {
		this._readOnlyCommitId = readOnlyCommitId;
	}

	public long getReadOnlyCacheId() {
		return _readOnlyCacheId;
	}

	public void setReadOnlyCacheId(long cacheId) {
		this._readOnlyCacheId = cacheId;
	}

/*	
	public Map<String, MetaDataElement> get_aspectDictionary() {
		return _aspectDictionary;
	}

	public void set_aspectDictionary(Map<String, MetaDataElement> _aspectDictionary) {
		this._aspectDictionary = _aspectDictionary;
	}

*/
    
}
