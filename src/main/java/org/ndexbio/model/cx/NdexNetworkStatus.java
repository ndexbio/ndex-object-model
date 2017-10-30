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
package org.ndexbio.model.cx;

import java.sql.Timestamp;

import org.ndexbio.model.object.network.VisibilityType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NdexNetworkStatus extends NdexAspectElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1769777133637306296L;

	public final static String ASPECT_NAME = "ndexStatus";
	
	private String externalId;
	private Timestamp creationTime;
	private Timestamp modificationTime;
	private VisibilityType visibility;
	private boolean published;
//	private String version;
	private int nodeCount;
	private int edgeCount;
	private String owner;
	private String ndexServerURI;
	private boolean readOnly;
	
//	private String sourceFormat; move to network property
	
	public NdexNetworkStatus() {
		nodeCount = -1;
		edgeCount = -1;
	}

	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
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

	public VisibilityType getVisibility() {
		return visibility;
	}

	public void setVisibility(VisibilityType visibility) {
		this.visibility = visibility;
	}


	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

	public void setEdgeCount(int edgeCount) {
		this.edgeCount = edgeCount;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getNdexServerURI() {
		return ndexServerURI;
	}

	public void setNdexServerURI(String ndexServerURI) {
		this.ndexServerURI = ndexServerURI;
	}


	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
