package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class SimpleNetworkQuery extends SimpleQuery {

private String _accountName;
private Permissions _permission;

	public void setAccountName(String accountName) {
		_accountName = accountName;
	}
	
	public String getAccountName() {
		return _accountName;
	}
	
	public void setPermission(Permissions permission) {
		_permission = permission;
	}
	
	public Permissions getPermission() {
		return _permission;
	}
	
}
