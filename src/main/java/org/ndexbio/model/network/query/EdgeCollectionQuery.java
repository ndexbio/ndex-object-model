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
