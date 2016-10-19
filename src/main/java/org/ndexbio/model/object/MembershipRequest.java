package org.ndexbio.model.object;

import java.util.UUID;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class MembershipRequest {
    private UUID groupid;     
    private Permissions type;
    private String message;
    
    public MembershipRequest() throws NdexException {
    	setGroupid(null);   	
    	setType(Permissions.MEMBER);
    }

	public Permissions getType() {
		return type;
	}

	public void setType(Permissions permission) throws NdexException {
		if ( permission != Permissions.GROUPADMIN || permission != Permissions.MEMBER)
			throw new NdexException("Permission type for request can only be READ or WRITE.");
		this.type = permission;
	}

	public UUID getGroupid() {
		return groupid;
	}

	public void setGroupid(UUID networkid) {
		this.groupid = networkid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
