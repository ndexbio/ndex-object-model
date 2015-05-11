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
    private String ndexExceptionInJason;
    
    public NdexException(String message)
    {
        super(message);
        ndexError = new NDExError(errorCode, message, null);
        ndexExceptionInJason = ndexExceptionToJason();
    }
    public NdexException(String message, Throwable cause)
    {
        super(message, cause);
        ndexError = new NDExError(errorCode, message, null);
        ndexExceptionInJason = ndexExceptionToJason();
    }
    public NdexException(String message, String description)
    {
        super(message);
        ndexError = new NDExError(errorCode, message, description);
        ndexExceptionInJason = ndexExceptionToJason();
    }
    public NdexException(String message, String description, Throwable cause)
    {
        super(message, cause);
        this.ndexError = new NDExError(errorCode, message, description);
        ndexExceptionInJason = ndexExceptionToJason();
    }  
    public NdexException(String message, ErrorCode errorCode) {
		super(message);
        this.ndexError = new NDExError(errorCode, message, null);
        ndexExceptionInJason = ndexExceptionToJason();
    }
	public NdexException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.ndexError = new NDExError(errorCode, message, null);
        ndexExceptionInJason = ndexExceptionToJason();
	}
	public NdexException(String message, String description, ErrorCode errorCode) {
		super(message);
		this.ndexError = new NDExError(errorCode, message, description);
		ndexExceptionInJason = ndexExceptionToJason();
	}
	public NdexException(String message, String description, ErrorCode errorCode, Throwable cause) {
		super(message, cause);
		this.ndexError = new NDExError(errorCode, message, description);
		ndexExceptionInJason = ndexExceptionToJason();
	}
	
	public String getNdexExceptionInJason() { return this.ndexExceptionInJason; }
	
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
