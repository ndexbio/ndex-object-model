/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
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
