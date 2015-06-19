/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.object;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Account
{
    private String _emailAddress;
    private String _firstName;
    private String _lastName;
    private long    _diskQuota;  //in byte
    private long    _diskUsed;   //in byte
    
    
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

	public long getDiskQuota() {
		return _diskQuota;
	}

	public void setDiskQuota(long diskQuota) {
		this._diskQuota = diskQuota;
	}

	public long getDiskUsed() {
		return _diskUsed;
	}

	public void setDiskUsed(long diskUsed) {
		this._diskUsed = diskUsed;
	}
    
}
