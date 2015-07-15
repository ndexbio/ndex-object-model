package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;
import org.ndexbio.model.errorcodes.NDExError;


public class ForbiddenOperationException extends NdexException {
	
	private static final long serialVersionUID = 5300170005261658849L;
	
	public ForbiddenOperationException(NDExError ndexError)
    {
        super(ndexError);
    }	
    public ForbiddenOperationException(String message)
    {
        super(message, ErrorCode.NDEx_Forbidden_Operation_Exception);
    }
    public ForbiddenOperationException(String message, Throwable cause)
    {
        super(message, cause, ErrorCode.NDEx_Forbidden_Operation_Exception);
    }
    public ForbiddenOperationException(String message, String description)
    {
        super(message, description, ErrorCode.NDEx_Forbidden_Operation_Exception);
    }
    public ForbiddenOperationException(String message, String description, Throwable cause)
    {
        super(message, description, ErrorCode.NDEx_Forbidden_Operation_Exception, cause);
    }
}

