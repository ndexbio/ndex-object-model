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
package org.ndexbio.model.network.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class EdgeCollectionQuery {

	private EdgeByNodePropertyFilter _nodeFilter;
	private EdgeByEdgePropertyFilter _edgeFilter;
	private String _queryName;
	
	private int _edgeLimit; // -1 means no limit;

	public EdgeByNodePropertyFilter getNodeFilter() {
		return _nodeFilter;
	}

	public void setNodeFilter(EdgeByNodePropertyFilter nodeFilter) {
		this._nodeFilter = nodeFilter;
	}

	public EdgeByEdgePropertyFilter getEdgeFilter() {
		return _edgeFilter;
	}

	public void setEdgeFilter(EdgeByEdgePropertyFilter edgeFilter) {
		this._edgeFilter = edgeFilter;
	}

	public int getEdgeLimit() {
		return _edgeLimit;
	}

	public void setEdgeLimit(int edgeLimit) {
		this._edgeLimit = edgeLimit;
	}

	public String getQueryName() {
		return _queryName;
	}

	public void setQueryName(String queryName) {
		this._queryName = queryName;
	}
	
}
