package org.ndexbio.model.object;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Account
{
    private String _emailAddress;
    private String _firstName;
    private String _lastName;
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public User()
    {
        super();
        _type = this.getClass().getSimpleName();
    //    initCollections();
    }
    
    public String getEmailAddress()
    {
        return _emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        _emailAddress = emailAddress;
    }

    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName(String firstName)
    {
        _firstName = firstName;
    }

    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName(String lastName)
    {
        _lastName = lastName;
    }
    
}
