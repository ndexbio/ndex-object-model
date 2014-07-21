package org.ndexbio.model.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "accountType")
@JsonSubTypes(value = { @Type(value = Group.class, name = "Group"), @Type(value = User.class, name = "User") })
public abstract class Account extends NdexExternalObject implements PropertiedObject
{
    private String _imageURL;
    private String _description;
    private String _website;
    private String _accountName;
    private String _password;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Account()
    {
        super();
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
    
	private List<NdexProperty> _properties;
	private List<NdexProperty> _presentationProperties;


	public List<NdexProperty> getProperties() {
		return _properties;
	}

	public List<NdexProperty> getPresentationProperties() {
		return _presentationProperties;
	}

	public void setProperties(List<NdexProperty> properties) {
		_properties = properties;
	}

	public void setPresentationProperties(List<NdexProperty> properties) {
		_presentationProperties = properties;
	}


	public String getAccountName() {
		return _accountName;
	}


	public void setAccountName(String _accountName) {
		this._accountName = _accountName;
	}


	public String getPassword() {
		return _password;
	}


	public void setPassword(String _password) {
		this._password = _password;
	}

}
