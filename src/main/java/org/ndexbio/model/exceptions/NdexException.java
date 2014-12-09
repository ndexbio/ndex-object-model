package org.ndexbio.model.exceptions;


public class NdexException extends Exception
{
    private static final long serialVersionUID = 1L;


    public NdexException(String message)
    {
        super(message);
    }

    public NdexException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
