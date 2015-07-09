/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
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
    
    public NdexException(NDExError ndexError)
    {
        super(ndexError.getMessage());
        this.ndexError = ndexError;
    }    
    public NdexException(String message)
    {
        super(message);
        ndexError = new NDExError(errorCode, message, null);
    }
    public NdexException(String message, Throwable cause)
    {
        super(message, cause);
        ndexError = new NDExError(errorCode, message, null);
    }
    public NdexException(String message, String description)
    {
        super(message);
        ndexError = new NDExError(errorCode, message, description);
    }
    public NdexException(String message, String description, Throwable cause)
    {
        super(message, cause);
        this.ndexError = new NDExError(errorCode, message, description);
    }  
    public NdexException(String message, ErrorCode errorCode) {
		super(message);
        this.ndexError = new NDExError(errorCode, message, null);
    }
	public NdexException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.ndexError = new NDExError(errorCode, message, null);
	}
	public NdexException(String message, String description, ErrorCode errorCode) {
		super(message);
		this.ndexError = new NDExError(errorCode, message, description);
	}
	public NdexException(String message, String description, ErrorCode errorCode, Throwable cause) {
		super(message, cause);
		this.ndexError = new NDExError(errorCode, message, description);
	}

	public NDExError getNDExError() { return this.ndexError; }
	
	public String getNdexExceptionInJason() { return ndexExceptionToJason(); }
	
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
