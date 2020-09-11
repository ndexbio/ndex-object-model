/**
 * Copyright (c) 2013, 2016, The Regents of the University of California, The Cytoscape Consortium
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.ndexbio.model.object.NdexExternalObject;
import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.PropertiedObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(Include.NON_NULL)

public class NetworkSummary extends NdexExternalObject implements PropertiedObject {

    private String _description;
    private int _edgeCount;
    private VisibilityType _visibility;
    private String _name;
    private int _nodeCount;
    private String _owner;
    
    private UUID ownerUUID;
    
    private boolean isReadOnly;
    
	private String _version;

	private String _URI;

	private Set<Long> subnetworkIds;
	
	private List<NdexPropertyValuePair> _properties;
	
	private String errorMessage;
	private boolean isValid;
	private List<String> warnings;
	
	private boolean isShowcase;

	private boolean isCompleted;
	private String doi;
	private boolean isCertified;
	private NetworkIndexLevel indexLevel;
	
	private boolean hasLayout;
	private boolean hasSample;
	
	// null means cx1. Valid values are cx and cx2.
	private String cxFormat; 
	
//	private boolean cxFileSize;
	
	public String getCxFormat() {
		return cxFormat;
	}

	public void setCxFormat(String cxFormat) {
		this.cxFormat = cxFormat;
	}

	public NetworkSummary () {
		super();

        _edgeCount = 0;
        _nodeCount = 0;
        
        _properties = new ArrayList<> (10);
        this.subnetworkIds = new HashSet<>();
        isReadOnly = false;
        warnings = new LinkedList<>();
        indexLevel=NetworkIndexLevel.NONE;

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

    public boolean getIsReadOnly()
    {
        return isReadOnly;
    }
    
    public void setIsReadOnly(boolean isReadOnly)
    {
        this.isReadOnly = isReadOnly;
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


	@Override
	public void setProperties(List<NdexPropertyValuePair> properties) {
		_properties = properties;
	}

	/**
	 * Get the property with the given name.
	 * @param name
	 * @return The property object with that given name. null if the property is not found. 
	 */
	public NdexPropertyValuePair getPropertyByName(String name) {
		for ( NdexPropertyValuePair p : _properties ) {
			if ( p.getPredicateString().equals(name)) {
				return p;
			}
		}
		return null;
	}

	public String getOwner() {
		return _owner;
	}

	public void setOwner(String owner) {
		this._owner = owner;
	}

	public String getURI() {
		return _URI;
	}

	public void setURI(String URI) {
		this._URI = URI;
	}

	public Set<Long> getSubnetworkIds() {
		return subnetworkIds;
	}

	public void setSubnetworkIds(Set<Long> subnetworkIds) {
		this.subnetworkIds = subnetworkIds;
	}

	public UUID getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(UUID ownerUUID) {
		this.ownerUUID = ownerUUID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	public boolean getIsShowcase() {
		return isShowcase;
	}

	public void setIsShowcase(boolean displayInHomePage) {
		this.isShowcase = displayInHomePage;
	}
/*
	public boolean isIndexed() {
		return isIndexed;
	}

	public void setIndexed(boolean isIndexed) {
		this.isIndexed = isIndexed;
	}
*/
	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public boolean getIsCertified() {
		return isCertified;
	}

	public void setIsCertified(boolean certified) {
		this.isCertified = certified;
	}

	public NetworkIndexLevel getIndexLevel() {
		return indexLevel;
	}

	public void setIndexLevel(NetworkIndexLevel solrIndexLevel) {
		this.indexLevel = solrIndexLevel;
	}

	public boolean getHasLayout() {
		return hasLayout;
	}

	public void setHasLayout(boolean hasLayout) {
		this.hasLayout = hasLayout;
	}

	public boolean getHasSample() {
		return hasSample;
	}

	public void setHasSample(boolean hasSample) {
		this.hasSample = hasSample;
	}

}
