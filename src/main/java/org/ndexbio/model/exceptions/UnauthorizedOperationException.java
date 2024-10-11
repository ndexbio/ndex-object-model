/**
 * Copyright (c) 2013, 2016, The Regents of the University of California, The Cytoscape Consortium
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

public class UnauthorizedOperationException extends NdexException {

	private static final long serialVersionUID = 1002L;
	
    public UnauthorizedOperationException(NDExError ndexError)
    {
        super(ndexError);
    }	
    public UnauthorizedOperationException(String message)
    {
        super(message, ErrorCode.NDEx_Unauthorized_Operation_Exception);
    }

	public UnauthorizedOperationException(String message, ErrorCode errorCode) 
    {
        super(message, errorCode);
    }

    
    public UnauthorizedOperationException(String message, Throwable cause)
    {
        super(message, cause, ErrorCode.NDEx_Unauthorized_Operation_Exception);
    }
    
/*    public UnauthorizedOperationException(String message, String description)
    {
        super(message, description, ErrorCode.NDEx_Unauthorized_Operation_Exception);
    } */
    
    public UnauthorizedOperationException(String message, String description, Throwable cause)
    {
        super(message, description, ErrorCode.NDEx_Unauthorized_Operation_Exception, cause);
    }
    
	public static UnauthorizedOperationException createUnVerifiedAccountError(final String accountName, final String accountEmail) {
		return new UnauthorizedOperationException(
				"NDEx user account " + accountName 
						+ " <" + accountEmail 
								+ "> is not verified yet. Please check your email from NDEx and verify the account before sign in to NDEx", 
				ErrorCode.NDEx_User_Account_Not_Verified);
	}
}
