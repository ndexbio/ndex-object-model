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

import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.PropertiedObject;
import org.ndexbio.model.object.SimplePropertyValuePair;

public abstract class PropertiedNetworkElement extends NetworkElement implements
		PropertiedObject {

	
	private List<NdexPropertyValuePair> _properties;
	private List<SimplePropertyValuePair> _presentationProperties;

    
	 public PropertiedNetworkElement()
	 {
	        super();
	        _type = this.getClass().getSimpleName();
	        _properties = new ArrayList<>();
	        _presentationProperties = new ArrayList<>();
//	        this.initializeCollections();
	 }
	
	
	@Override
	public List<NdexPropertyValuePair> getProperties() {
		return _properties;
	}

	@Override
	public List<SimplePropertyValuePair> getPresentationProperties() {
		return _presentationProperties;
	}

	@Override
	public void setProperties(List<NdexPropertyValuePair> properties) {
		_properties = properties;
	}

	@Override
	public void setPresentationProperties(List<SimplePropertyValuePair> properties) {
		_presentationProperties = properties;
	}

	public String getPropertyAsString (String propertyName) {
		for (NdexPropertyValuePair p : this.getProperties()) {
			if ( p.getPredicateString().equals(propertyName))
				return p.getValue();
		}
		return null;
	}

}
