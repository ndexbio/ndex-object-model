/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUser
{
    private String _emailAddress;
    private String _password;
    private String _accountName;
    private String _firstname;
    private String _lastname;
    private String _description;
    private String _websiteURL;
    private String _imageURL;
    
    
    
    public String getEmailAddress()
    {
        return _emailAddress;
    }
    
    public void setEmailAddress(String emailAddress)
    {
        this._emailAddress = emailAddress;
    }
    
    public String getPassword()
    {
        return _password;
    }
    
    public void setPassword(String password)
    {
        this._password = password;
    }
    
    public String getAccountName()
    {
        return _accountName;
    }
    
    public void setAccountName(String accountName)
    {
        this._accountName = accountName;
    }

	public String getFirstName() {
		return _firstname;
	}

	public void setFirstName(String firstname) {
		this._firstname = firstname;
	}

	public String getLastName() {
		return _lastname;
	}

	public void setLastName(String lastname) {
		this._lastname = lastname;
	}

	public String getImage() {
		return _imageURL;
	}

	public void setImage(String imageURL) {
		this._imageURL = imageURL;
	}

	public String getWebsite() {
		return _websiteURL;
	}

	public void setWebsite(String websiteURL) {
		this._websiteURL = websiteURL;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}
}
