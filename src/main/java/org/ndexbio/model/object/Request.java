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

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request extends NdexExternalObject
{
    private UUID _sourceUUID;
    private String _sourceName;
    private UUID _destinationUUID;
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
    public Request()
    {
        super();
        this._type = this.getClass().getSimpleName();
    }
    
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
