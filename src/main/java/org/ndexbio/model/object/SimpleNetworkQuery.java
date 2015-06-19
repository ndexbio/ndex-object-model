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
