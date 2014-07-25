package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUser
{
    private String _emailAddress;
    private String _password;
    private String _accountName;
    private String _firstname;
    private String _lastname;
    private String _description;
    private String _websiteURL;
    private String _imageURL;
    
    
    
    public String getEmailAddress()
    {
        return _emailAddress;
    }
    
    public void setEmailAddress(String emailAddress)
    {
        this._emailAddress = emailAddress;
    }
    
    public String getPassword()
    {
        return _password;
    }
    
    public void setPassword(String password)
    {
        this._password = password;
    }
    
    public String getAccountName()
    {
        return _accountName;
    }
    
    public void setAccountName(String accountName)
    {
        this._accountName = accountName;
    }

	public String getFirstName() {
		return _firstname;
	}

	public void setFirstName(String firstname) {
		this._firstname = firstname;
	}

	public String getLastName() {
		return _lastname;
	}

	public void setLastName(String lastname) {
		this._lastname = lastname;
	}

	public String getImage() {
		return _imageURL;
	}

	public void setImage(String imageURL) {
		this._imageURL = imageURL;
	}

	public String getWebsite() {
		return _websiteURL;
	}

	public void setWebsite(String websiteURL) {
		this._websiteURL = websiteURL;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}
}
