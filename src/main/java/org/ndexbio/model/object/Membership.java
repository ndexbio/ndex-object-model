/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
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

	public void setMemberUUID(UUID memberUUID) {
		this._memberUUID = memberUUID;
	}

	public UUID getResourceUUID() {
		return _resourceUUID;
	}

	public void setResourceUUID(UUID resourceUUID) {
		this._resourceUUID = resourceUUID;
	}

	public String getMemberAccountName() {
		return _memberAccountName;
	}

	public void setMemberAccountName(String memberAccountName) {
		this._memberAccountName = memberAccountName;
	}

}
