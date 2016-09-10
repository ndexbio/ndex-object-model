/**
 * Copyright (c) 2013, 2016, The Regents of the University of California, The Cytoscape Consortium
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

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request extends NdexExternalObject
{
    private UUID _sourceUUID;     // can be a user or a group for network permissions, will be a user for group membership.
    private String _sourceName;
    private UUID _destinationUUID;  // can be a group or network depend on permission type.
    private String _destinationName;
    private String _message;
    private Permissions _permission;
    private String _responder;
    private ResponseType _response;
    private String _responseMessage;
    private Timestamp _responseTime;

    

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
 /*   public Request()
    {
        super();
        this._type = this.getClass().getSimpleName();
    } */
    
    public String getMessage()
    {
        return _message;
    }

    public void setMessage(String message)
    {
        _message = message;
    }

    
    public void setResponder(String responder)
    {
        _responder = responder;
    }
    
    public String getResponseMessage()
    {
        return _responseMessage;
    }
    
    public void setResponseMessage(String responseMessage)
    {
        _responseMessage = responseMessage;
    }

	public UUID getSourceUUID() {
		return _sourceUUID;
	}

	public void setSourceUUID(UUID sourceUUID) {
		this._sourceUUID = sourceUUID;
	}

	public UUID getDestinationUUID() {
		return _destinationUUID;
	}

	public void setDestinationUUID(UUID destinationUUID) {
		this._destinationUUID = destinationUUID;
	}

	public String getSourceName() {
		return _sourceName;
	}

	public void setSourceName(String sourceName) {
		this._sourceName = sourceName;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public void setDestinationName(String destinationName) {
		this._destinationName = destinationName;
	}

	public Permissions getPermission() {
		return _permission;
	}

	public void setPermission(Permissions permission) {
		this._permission = permission;
	}

	public String getResponder() {
		return _responder;
	}

	public ResponseType getResponse() {
		return _response;
	}

	public void setResponse(ResponseType response) {
		this._response = response;
	}

	public Timestamp getResponseTime() {
		return _responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this._responseTime = responseTime;
	}
    
    

}
