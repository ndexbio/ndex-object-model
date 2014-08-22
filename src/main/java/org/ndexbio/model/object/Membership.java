package org.ndexbio.model.object;

import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership extends NdexObject //NdexExternalObject
{
    private Permissions _memberPermissions;
    private MembershipType _membershipType;
    private UUID _memberUUID;
    private UUID _resourceUUID;
    private String _resourceName;
    private String _memberAccountName;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Membership()
    {
    	super();
        _type = this.getClass().getSimpleName();
    }
      
    public Permissions getPermissions()
    {
        return _memberPermissions;
    }
    
    public void setPermissions(Permissions memberPermissions)
    {
        _memberPermissions = memberPermissions;
    }
    
    
    public String getResourceName()
    {
        return _resourceName;
    }
    
    public void setResourceName(String resourceName)
    {
        _resourceName = resourceName;
    }

	public MembershipType getMembershipType() {
		return _membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this._membershipType = membershipType;
	}

	public UUID getMemberUUID() {
		return _memberUUID;
	}

	public void setMemberUUID(UUID _memberUUID) {
		this._memberUUID = _memberUUID;
	}

	public UUID getResourceUUID() {
		return _resourceUUID;
	}

	public void setResourceUUID(UUID _resourceUUID) {
		this._resourceUUID = _resourceUUID;
	}

	public String getMemberAccountName() {
		return _memberAccountName;
	}

	public void setMemberAccountName(String _memberAccountName) {
		this._memberAccountName = _memberAccountName;
	}

}
