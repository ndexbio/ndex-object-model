package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;
import org.ndexbio.model.errorcodes.NDExError;

public class ObjectNotFoundException extends NdexException
{
    private static final long serialVersionUID = 1L;
    
    public ObjectNotFoundException(NDExError ndexError)
    {
        super(ndexError);
    }
    public ObjectNotFoundException(String message)
    {
        super(message, ErrorCode.NDEx_Object_Not_Found_Exception);
    }
    public ObjectNotFoundException(String message, Throwable cause)
    {
        super(message, cause, ErrorCode.NDEx_Object_Not_Found_Exception);
    }
    public ObjectNotFoundException(String message, String description)
    {
        super(message, description, ErrorCode.NDEx_Object_Not_Found_Exception);
    }
    public ObjectNotFoundException(String message, String description, Throwable cause)
    {
        super(message, description, ErrorCode.NDEx_Object_Not_Found_Exception, cause);
    }
}

