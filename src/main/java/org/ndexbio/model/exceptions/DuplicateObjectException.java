package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;
//import org.ndexbio.model.exceptions.NdexException;

public class DuplicateObjectException extends NdexException
{
    private static final long serialVersionUID = 1L;

    public DuplicateObjectException(String message)
    {
        super(message, ErrorCode.NDEx_Duplicate_Object_Exception);
    }
    public DuplicateObjectException(String message, Throwable cause)
    {
        super(message, cause, ErrorCode.NDEx_Duplicate_Object_Exception);
    }
    public DuplicateObjectException(String message, String description)
    {
        super(message, description, ErrorCode.NDEx_Duplicate_Object_Exception);
    }
    public DuplicateObjectException(String message, String description, Throwable cause)
    {
        super(message, description, ErrorCode.NDEx_Duplicate_Object_Exception, cause);
    }
}
