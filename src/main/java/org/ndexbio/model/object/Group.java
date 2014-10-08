package org.ndexbio.model.object;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group extends Account
{
    private String _organizationName;
 
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Group()
    {
        super();

        _type = this.getClass().getSimpleName();

    } 
    
    public String getOrganizationName()
    {
        return _organizationName;
    }
    
    public void setOrganizationName(String organizationName)
    {
        _organizationName = organizationName;
    }
    
}
