package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;
import org.ndexbio.model.errorcodes.NDExError;

public class BadRequestException extends NdexException {

	
private static final long serialVersionUID = 5300170005261658849L;
	
	public BadRequestException(NDExError ndexError)
    {
        super(ndexError);
    }	
    public BadRequestException(String message)
    {
        super(message, ErrorCode.NDEx_Bad_Request_Exception);
    }
  /*  public BadRequestException(String message, Throwable cause)
    {
        super(message, cause, ErrorCode.NDEx_Bad_Request_Exception);
    }
    public BadRequestException(String message, String description)
    {
        super(message, description, ErrorCode.NDEx_Bad_Request_Exception);
    }
    public BadRequestException(String message, String description, Throwable cause)
    {
        super(message, description, ErrorCode.NDEx_Bad_Request_Exception, cause);
    } */
}
