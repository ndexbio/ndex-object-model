package org.ndexbio.model.object;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request extends NdexExternalObject
{
    private UUID _sourceUUIDd;
    private String _sourceName;
    private UUID _destinationUUID;
    private String _destinationName;
    private String _message;
    private RequestType _requestType;
    private String _responder;
    private ResponseType _response;
    private String _responseMessage;

    

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Request()
    {
        super();
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

	public UUID getSourceUUIDd() {
		return _sourceUUIDd;
	}

	public void setSourceUUIDd(UUID _sourceUUIDd) {
		this._sourceUUIDd = _sourceUUIDd;
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

	public RequestType getRequestType() {
		return _requestType;
	}

	public void setRequestType(RequestType _requestType) {
		this._requestType = _requestType;
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