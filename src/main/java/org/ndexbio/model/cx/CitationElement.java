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

import org.cxio.util.CxConstants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CitationElement extends NdexAspectElement {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5794827391191607917L;

	final public static String ASPECT_NAME           = "citations";
	
	@JsonProperty(CxConstants.ID)
	private long id ;
	
	@JsonProperty("dc:title")	
	private String title;
	
	@JsonProperty("dc:contributor")	
	private Collection<String> contributor;

	@JsonProperty("dc:identifier")	

	private String identifier;
	
	@JsonProperty("dc:type")	
	private String citationType;

	@JsonProperty("dc:description")	

	private String description;
	
	@JsonProperty("attributes")	

	private Collection<CXSimpleAttribute> props;
	
	public CitationElement() {
		props = new LinkedList<>();
	}

	@Override
	@JsonIgnore
	public  String getAspectName() {
		return ASPECT_NAME;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection<String> getContributor() {
		return contributor;
	}

	public void setContributor(Collection<String> contributor) {
		this.contributor = contributor;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCitationType() {
		return citationType;
	}

	public void setCitationType(String citationType) {
		this.citationType = citationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
/*
	public Collection<String> getEdges() {
		return edges;
	}

	public void setEdges(Collection<String> edges) {
		this.edges = edges;
	}

	public Collection<String> getNodes() {
		return nodes;
	}

	public void setNodes(Collection<String> nodes) {
		this.nodes = nodes;
	}  */
/*
	public Collection<CXSupport> getSupports() {
		return supports;
	}

	public void setSupports(Collection<CXSupport> supports) {
		this.supports = supports;
	} */


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<CXSimpleAttribute> getProps() {
		return props;
	}

	public void setProps(Collection<CXSimpleAttribute> props) {
		this.props = props;
	}

}
