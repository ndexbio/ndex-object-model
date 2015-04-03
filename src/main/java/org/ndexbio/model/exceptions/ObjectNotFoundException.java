package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;

public class ObjectNotFoundException extends NdexException
{
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message)
    {
        super(message, ErrorCode.NDEx_Ndex_Object_Not_Found_Exception);
    }
    public ObjectNotFoundException(String message, Throwable cause)
    {
        super(message, cause, ErrorCode.NDEx_Ndex_Object_Not_Found_Exception);
    }
    public ObjectNotFoundException(String message, String description)
    {
        super(message, description, ErrorCode.NDEx_Ndex_Object_Not_Found_Exception);
    }
    public ObjectNotFoundException(String message, String description, Throwable cause)
    {
        super(message, description, ErrorCode.NDEx_Ndex_Object_Not_Found_Exception, cause);
    }
}

