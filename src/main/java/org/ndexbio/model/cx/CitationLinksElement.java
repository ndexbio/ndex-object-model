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

import java.util.Collection;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CitationLinksElement extends NdexAspectElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6019854108353879020L;

	@JsonProperty("po")	
	private Collection<Long> sourceIds ;
	
	@JsonProperty("citations")	
	private Collection<Long> citationIds; 

	public CitationLinksElement() {
		citationIds = new LinkedList<>();
	}

	public CitationLinksElement(Collection<Long> sourceIds, Collection<Long> citationIds) {
		this.sourceIds = sourceIds;
		this.citationIds = citationIds;
	}


	public Collection<Long> getSourceIds() {
		return sourceIds;
	}

	public void setSourceIds(Collection<Long> sourceId) {
		this.sourceIds = sourceId;
	}

	@JsonIgnore
	public Collection<Long> getCitationIds() {
		return citationIds;
	}

	@JsonIgnore
	public void setCitationIds(Collection<Long> citationId) {
		this.citationIds = citationId;
	}

}
