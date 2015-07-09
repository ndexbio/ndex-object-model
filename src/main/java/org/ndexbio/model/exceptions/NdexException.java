package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;
import org.ndexbio.model.errorcodes.NDExError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class NdexException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    private NDExError ndexError;
    private static ErrorCode errorCode = ErrorCode.NDEx_Exception;
    
    public NdexException(NDExError ndexError)
    {
        super(ndexError.getMessage());
        this.ndexError = ndexError;
    }    
    public NdexException(String message)
    {
        super(message);
        ndexError = new NDExError(errorCode, message, null);
    }
    public NdexException(String message, Throwable cause)
    {
        super(message, cause);
        ndexError = new NDExError(errorCode, message, null);
    }
    public NdexException(String message, String description)
    {
        super(message);
        ndexError = new NDExError(errorCode, message, description);
    }
    public NdexException(String message, String description, Throwable cause)
    {
        super(message, cause);
        this.ndexError = new NDExError(errorCode, message, description);
    }  
    public NdexException(String message, ErrorCode errorCode) {
		super(message);
        this.ndexError = new NDExError(errorCode, message, null);
    }
	public NdexException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.ndexError = new NDExError(errorCode, message, null);
	}
	public NdexException(String message, String description, ErrorCode errorCode) {
		super(message);
		this.ndexError = new NDExError(errorCode, message, description);
	}
	public NdexException(String message, String description, ErrorCode errorCode, Throwable cause) {
		super(message, cause);
		this.ndexError = new NDExError(errorCode, message, description);
	}

	public NDExError getNDExError() { return this.ndexError; }
	
	public String getNdexExceptionInJason() { return ndexExceptionToJason(); }
	
	private String ndexExceptionToJason() {
	    ObjectMapper objectMapper = new ObjectMapper();
	    String jasonString = null;

		try {
			jasonString = objectMapper.writeValueAsString(ndexError);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		    // e.printStackTrace();
		} catch (Exception e) {
		   // TODO Auto-generated catch block
		   // e.printStackTrace();
	    }
    	return jasonString;  	
    }
    
}
