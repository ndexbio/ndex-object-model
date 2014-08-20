package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class SimpleNetworkQuery extends SimpleQuery {

private String _accountName;
private Permissions _permission;
private boolean _includeGroups;

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

	public void setIncludeGroups(boolean _includeGroups) {
		this._includeGroups = _includeGroups;
	}
	
}
