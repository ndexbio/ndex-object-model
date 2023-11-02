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


import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(Include.NON_NULL)

public class NetworkSummaryV3  {

  
	private UUID uuid; 
	private Timestamp creationTime;
	private Timestamp modificationTime;
	private String updatedBy;
	
	private String description;
    private Integer _edgeCount;
    private VisibilityType _visibility;
    private String name;
    private Integer _nodeCount;
    private String _owner;
    
    private UUID ownerUUID;
    
    private Boolean isReadOnly;
    
	//private String _version;

	//private String _URI;

	private Set<Long> subnetworkIds;
	
	private NetworkProperties _properties;
	
	private String errorMessage;
	//private Boolean isValid;
	private List<String> warnings;
	
	private Boolean isShowcase;

	private Boolean isCompleted;
	private String doi;
	private Boolean isCertified;
	private NetworkIndexLevel indexLevel;
	
	private Boolean hasLayout;
	private Boolean hasSample;
	
	// null means cx1. Valid values are cx and cx2.
	private String cxFormat; 
	
	private Long cxFileSize;
	
	private Long cx2FileSize;
	
	public String getCxFormat() {
		return cxFormat;
	}

	public void setCxFormat(String cxFormat) {
		this.cxFormat = cxFormat;
	}

	public NetworkSummaryV3 () {
	}

 
    
    public Integer getEdgeCount()
    {
        return _edgeCount;
    }

    public void setEdgeCount(int edgeCount)
    {
        _edgeCount = Integer.valueOf(edgeCount);
    }
	
	
	public VisibilityType getVisibility() {
		return _visibility;
	}


	public void setVisibility(VisibilityType visibility) {
		this._visibility = visibility;
	}

    public Boolean getIsReadOnly()
    {
        return isReadOnly;
    }
    
    public void setIsReadOnly(boolean isReadOnly)
    {
        this.isReadOnly = Boolean.valueOf(isReadOnly);
    }


    public Integer getNodeCount()
    {
        return _nodeCount;
    }

    public void setNodeCount(int nodeCount)
    {
        _nodeCount = Integer.valueOf(nodeCount);
    }

	public NetworkProperties getProperties() {
		return _properties;
	}

	public void setProperties(NetworkProperties properties) {
		_properties = properties;
	}

	public String getOwner() {
		return _owner;
	}

	public void setOwner(String owner) {
		this._owner = owner;
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

/*	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = Boolean.valueOf(isValid);
	}
*/
	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	public Boolean getIsShowcase() {
		return isShowcase;
	}

	public void setIsShowcase(boolean displayInHomePage) {
		this.isShowcase = Boolean.valueOf(displayInHomePage);
	}
/*
	public boolean isIndexed() {
		return isIndexed;
	}

	public void setIndexed(boolean isIndexed) {
		this.isIndexed = isIndexed;
	}
*/
	public Boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = Boolean.valueOf(isCompleted);
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Boolean getIsCertified() {
		return isCertified;
	}

	public void setIsCertified(boolean certified) {
		this.isCertified = Boolean.valueOf(certified);
	}

	public NetworkIndexLevel getIndexLevel() {
		return indexLevel;
	}

	public void setIndexLevel(NetworkIndexLevel solrIndexLevel) {
		this.indexLevel = solrIndexLevel;
	}

	public Boolean getHasLayout() {
		return hasLayout;
	}

	public void setHasLayout(boolean hasLayout) {
		this.hasLayout = Boolean.valueOf(hasLayout);
	}

	public Boolean getHasSample() {
		return hasSample;
	}

	public void setHasSample(boolean hasSample) {
		this.hasSample = Boolean.valueOf(hasSample);
	}

	public Long getCxFileSize() {
		return cxFileSize;
	}

	public void setCxFileSize(long cxFileSize) {
		this.cxFileSize = Long.valueOf(cxFileSize);
	}

	public Long getCx2FileSize() {
		return cx2FileSize;
	}

	public void setCx2FileSize(long cx2FileSize) {
		this.cx2FileSize = Long.valueOf(cx2FileSize);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Timestamp modificationTime) {
		this.modificationTime = modificationTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
