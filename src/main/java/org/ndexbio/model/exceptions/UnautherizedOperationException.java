package org.ndexbio.model.exceptions;

public class UnautherizedOperationException extends NdexException {

	private static final long serialVersionUID = 1002L;

    public UnautherizedOperationException(String message)
	{
	   super(message);
	}

	public UnautherizedOperationException(String message, Throwable cause)
	{
	        super(message, cause);
	}


}
