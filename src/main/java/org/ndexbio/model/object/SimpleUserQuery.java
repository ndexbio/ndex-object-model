package org.ndexbio.model.object;

public class SimpleUserQuery extends SimpleQuery{
	
	private String _accountName;
	
	public void setAccountName(String accountName) {
		_accountName = accountName;
	}
	
	public String getAccountName() {
		return _accountName;
	}
}
