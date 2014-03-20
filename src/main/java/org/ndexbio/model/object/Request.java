package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request extends NdexObject
{
    private String _fromId;
    private String _fromName;
    private String _toId;
    private String _toName;
    private String _message;
    private String _requestType;
    private String _responder;
    private String _response;
    private String _responseMessage;

    

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Request()
    {
        super();
    }
    


    
    
    
    public String getFrom()
    {
        return _fromName;
    }

    public void setFrom(String fromName)
    {
        _fromName = fromName;
    }

    public String getFromId()
    {
        return _fromId;
    }

    public void setFromId(String fromId)
    {
        _fromId = fromId;
    }

    public String getMessage()
    {
        return _message;
    }

    public void setMessage(String message)
    {
        _message = message;
    }

    public String getRequestType()
    {
        return _requestType;
    }

    public void setRequestType(String requestType)
    {
        _requestType = requestType;
    }
    
    public String getResponder()
    {
        return _responder;
    }
    
    public void setResponder(String responder)
    {
        _responder = responder;
    }
    
    public String getResponse()
    {
        return _response;
    }
    
    public void setResponse(String response)
    {
        _response = response;
    }
    
    public String getResponseMessage()
    {
        return _responseMessage;
    }
    
    public void setResponseMessage(String responseMessage)
    {
        _responseMessage = responseMessage;
    }

    public String getTo()
    {
        return _toName;
    }

    public void setTo(String toName)
    {
        _toName = toName;
    }

    public String getToId()
    {
        return _toId;
    }

    public void setToId(String toId)
    {
        _toId = toId;
    }
}
