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
    private String _backgroundImage;
    private String _description;
    private String _foregroundImage;
    private String _website;
    private String _accountName;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Account()
    {
        super();
    }
    
    
    public String getBackgroundImage()
    {
        return _backgroundImage;
    }
    
    public void setBackgroundImage(String backgroundImage)
    {
        _backgroundImage = backgroundImage;
    }
    
    public String getDescription()
    {
        return _description;
    }
    
    public void setDescription(String description)
    {
        _description = description;
    }
    
    public String getForegroundImage()
    {
        return _foregroundImage;
    }
    
    public void setForegroundImage(String foregroundImage)
    {
        _foregroundImage = foregroundImage;
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

}
