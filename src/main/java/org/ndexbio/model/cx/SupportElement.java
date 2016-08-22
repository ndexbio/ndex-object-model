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


import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.cxio.aspects.datamodels.AbstractAspectElement;
import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SupportElement extends AbstractAspectElement{
	
	public static final String ASPECT_NAME = "supports";
	
	private static final String tField = "text";

	
	@JsonProperty ( tField )
	private String text;
	
	@JsonProperty( "citation")
	private Long citationId;
	
	@JsonProperty( "@id")
	private long id;
	
	@JsonProperty( "attributes")
	private Collection<CXSimpleAttribute> props;
	
	public SupportElement() {
		props = new LinkedList<> ();
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Long getCitationId() {
		return citationId;
	}


	public void setCitationId(Long citationId) {
		this.citationId = citationId;
	}


	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}


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


	@Override
	public void write(JsonWriter out) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
