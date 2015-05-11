package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;

public class UnauthorizedOperationException extends NdexException {

	private static final long serialVersionUID = 1002L;
	
    public UnauthorizedOperationException(String message)
    {
        super(message, ErrorCode.NDEx_Unauthorized_Operation_Exception);
    }
    public UnauthorizedOperationException(String message, Throwable cause)
    {
        super(message, cause, ErrorCode.NDEx_Unauthorized_Operation_Exception);
    }
    public UnauthorizedOperationException(String message, String description)
    {
        super(message, description, ErrorCode.NDEx_Unauthorized_Operation_Exception);
    }
    public UnauthorizedOperationException(String message, String description, Throwable cause)
    {
        super(message, description, ErrorCode.NDEx_Unauthorized_Operation_Exception, cause);
    }
}
