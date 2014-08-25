package org.ndexbio.model.object;

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

	public void setSourceUUID(UUID _sourceUUID) {
		this._sourceUUID = _sourceUUID;
	}

	public UUID getDestinationUUID() {
		return _destinationUUID;
	}

	public void setDestinationUUID(UUID _destinationUUID) {
		this._destinationUUID = _destinationUUID;
	}

	public String getSourceName() {
		return _sourceName;
	}

	public void setSourceName(String _sourceName) {
		this._sourceName = _sourceName;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public void setDestinationName(String _destinationName) {
		this._destinationName = _destinationName;
	}

	public Permissions getPermission() {
		return _permission;
	}

	public void setPermission(Permissions _permission) {
		this._permission =_permission;
	}

	public String getResponder() {
		return _responder;
	}

	public ResponseType getResponse() {
		return _response;
	}

	public void setResponse(ResponseType _response) {
		this._response = _response;
	}
    
    

}
