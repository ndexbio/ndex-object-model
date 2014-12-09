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
