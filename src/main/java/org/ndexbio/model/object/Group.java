package org.ndexbio.model.object;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group extends Account
{
    private String _organizationName;
    private List<Membership> _members;
 /*   private List<Membership> _networkMemberships;
    private List<Request> _requests; */

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Group()
    {
        super();

        _type = this.getClass().getSimpleName();

       // initCollections();
    } 
    
/*    public List<Membership> getMembers()
    {
        return _members;
    }
    
    public void setMembers(List<Membership> members)
    {
        _members = members;
    }

    public String getName()
    {
        return _organizationName;
    }
    
    public void setName(String name)
    {
        _organizationName = name;
    }

    public List<Membership> getNetworks()
    {
        return _networkMemberships;
    }

    public void setNetworks(List<Membership> networkMemberships)
    {
        _networkMemberships = networkMemberships;
    }
  */  
    public String getOrganizationName()
    {
        return _organizationName;
    }
    
    public void setOrganizationName(String organizationName)
    {
        _organizationName = organizationName;
    }

	public List<Membership> getMembers() {
		return _members;
	}

	public void setMembers(List<Membership> _members) {
		this._members = _members;
	}
    
/*    public List<Request> getRequests()
    {
        return _requests;
    }
    
    public void setRequests(List<Request> requests)
    {
        _requests = requests;
    }
*/
    

    /**************************************************************************
    * Initializes the collections. 
    **************************************************************************/
/*    private void initCollections()
    {
        _members = new ArrayList<Membership>();
        _networkMemberships = new ArrayList<Membership>();
        _requests = new ArrayList<Request>();
    }
*/    
    
}
