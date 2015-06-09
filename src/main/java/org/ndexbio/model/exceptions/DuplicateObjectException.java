/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package org.ndexbio.model.exceptions;

import org.ndexbio.model.errorcodes.ErrorCode;
//import org.ndexbio.model.exceptions.NdexException;
import org.ndexbio.model.errorcodes.NDExError;

public class DuplicateObjectException extends NdexException
{
    private static final long serialVersionUID = 1L;
    
    public DuplicateObjectException(NDExError ndexError)
    {
        super(ndexError);
    }
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
