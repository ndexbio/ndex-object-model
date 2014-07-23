package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class SimpleNetworkQuery extends SimpleQuery {

private String _accountName;
	
	public void setAccountName(String accountName) {
		_accountName = accountName;
	}
	
	public String getAccountName() {
		return _accountName;
	}
	
}
