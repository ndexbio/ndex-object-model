package org.ndexbio.model.exceptions;

public class UnauthorizedOperationException extends NdexException {

	private static final long serialVersionUID = 1002L;

    public UnauthorizedOperationException(String message)
	{
	   super(message);
	}

	public UnauthorizedOperationException(String message, Throwable cause)
	{
	        super(message, cause);
	}


}
