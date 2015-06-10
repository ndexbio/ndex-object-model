/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
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
package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleNetworkQuery extends SimpleQuery {

	private String _accountName;
	private Permissions _permission;
	private boolean _includeGroups;
	private boolean _canRead;

	
	public SimpleNetworkQuery() {
		_canRead = false;
	}
	
	public void setAccountName(String accountName) {
		this._accountName = accountName;
	}
	
	public String getAccountName() {
		return _accountName;
	}
	
	public void setPermission(Permissions permission) {
		this._permission = permission;
	}
	
	public Permissions getPermission() {
		return _permission;
	}

	public boolean getIncludeGroups() {
		return _includeGroups;
	}

	public void setIncludeGroups(boolean includeGroups) {
		this._includeGroups = includeGroups;
	}

	public boolean getCanRead() {
		return _canRead;
	}

	/**
	 * Set this parameter to true if you want to find all the network that you have permission 
	 * to read, write and administered. DISCOVERABLE only networks will be excluded from the search result.
	 *  
	 * @param canRead
	 */
	public void setCanRead(boolean canRead) {
		this._canRead = canRead;
	}
	
}
