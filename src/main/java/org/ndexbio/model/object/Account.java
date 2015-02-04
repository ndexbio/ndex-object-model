package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "accountType")
@JsonSubTypes(value = { @Type(value = Group.class, name = "Group"), @Type(value = User.class, name = "User") })
public abstract class Account extends NdexExternalObject 
{
    private String _imageURL;
    private String _description;
    private String _website;
    private String _accountName;
    private boolean _isDeleted;
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Account()
    {
        super();
        setDeleted(false);
    }
    
    
    public String getImage()
    {
        return _imageURL;
    }
    
    public void setImage(String imageURL)
    {
        _imageURL = imageURL;
    }
    
    public String getDescription()
    {
        return _description;
    }
    
    public void setDescription(String description)
    {
        _description = description;
    }
    
    public String getWebsite()
    {
        return _website;
    }
    
    public void setWebsite(String website)
    {
        _website = website;
    }
    
	public String getAccountName() {
		return _accountName;
	}


	public void setAccountName(String accountName) {
		this._accountName = accountName;
	}


	public boolean isDeleted() {
		return _isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this._isDeleted = isDeleted;
	}

}
