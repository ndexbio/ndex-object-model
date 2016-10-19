package org.ndexbio.model.object;

import java.util.UUID;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PermissionRequest {
    private UUID networkid;     // can be a user or a group for network permissions, will be a user for group membership.
    private Permissions permission;
    private String message;
    
    public PermissionRequest() throws NdexException {
    	setNetworkid(null);   	
    	permission = Permissions.READ;
    }

	public Permissions getPermission() {
		return permission;
	}

	public void setPermission(Permissions permission) throws NdexException {
		if ( permission != Permissions.READ && permission != Permissions.WRITE)
			throw new NdexException("Permission type for request can only be READ or WRITE.");
		this.permission = permission;
	}

	public UUID getNetworkid() {
		return networkid;
	}

	public void setNetworkid(UUID networkid) {
		this.networkid = networkid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
