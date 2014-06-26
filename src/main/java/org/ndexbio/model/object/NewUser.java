package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUser
{
    private String _emailAddress;
    private String _password;
    private String _username;
    private String _firstname;
    private String _lastname;

    
    
    public String getEmailAddress()
    {
        return _emailAddress;
    }
    
    public void setEmailAddress(String emailAddress)
    {
        _emailAddress = emailAddress;
    }
    
    public String getPassword()
    {
        return _password;
    }
    
    public void setPassword(String password)
    {
        _password = password;
    }
    
    public String getUsername()
    {
        return _username;
    }
    
    public void setUsername(String username)
    {
        _username = username;
    }

	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String _firstname) {
		this._firstname = _firstname;
	}

	public String getLastname() {
		return _lastname;
	}

	public void setLastname(String _lastname) {
		this._lastname = _lastname;
	}
}
