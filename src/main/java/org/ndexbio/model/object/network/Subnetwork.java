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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Subnetwork extends PropertiedNetworkElement {

	
	private SubnetworkType _subnetworktype; 
	private String _name; 
	private long[] _nodeIds;  //sorted if we need to do lookups?
	private long[] _edgeIds;

	public Subnetwork () {
		super();
        _type = this.getClass().getSimpleName();

	}
 
	public SubnetworkType getSubnetworktype() {
		return _subnetworktype;
	}

	public void setSubnetworktype(SubnetworkType subnetworktype) {
		this._subnetworktype = subnetworktype;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public long[] getNodeIds() {
		return _nodeIds;
	}

	public void setNodeIds(long[] nodeIds) {
		this._nodeIds = nodeIds;
	}

	public long[] getEdgeIds() {
		return _edgeIds;
	}

	public void setEdgeIds(long[] edgeIds) {
		this._edgeIds = edgeIds;
	}

}
