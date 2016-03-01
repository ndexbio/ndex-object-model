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
package org.ndexbio.model.errorcodes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NDExError {
	private ErrorCode errorCode   = null;
	private String    message     = null;
	private String    description = null;
	private String    stackTrace  = null;
	private String    threadId    = null;
	private String    timeStamp   = null;  
	
	// example of timestamp with Format "yyyy-MM-dd H:mm:ss,SSS" is "2015-04-02 13:51:37,167";
	// the same format is used by our logger for entries in ndex.log 
	private static String timeStampFormat = "yyyy-MM-dd H:mm:ss,SSS";
	
	public NDExError() {}
	
	public NDExError(ErrorCode errorCode, String message, String description) {
		this.errorCode   = errorCode;
		this.message     = message;
		this.description = description;
		this.stackTrace  = stackTrace();
		this.threadId    = threadId();
		this.timeStamp   = timeStamp();
	}
	@JsonProperty("errorCode")
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonProperty("stackTrace")
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	@JsonProperty("threadId")
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	@JsonProperty("timeStamp")
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}	
	private static String stackTrace() {
    	// get stack trace; 
		StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
		StringBuilder sbStackTrace = new StringBuilder();
	    for (StackTraceElement element : stackTraces) {
	        sbStackTrace.append(element.toString());
	        sbStackTrace.append("\n");
	    }
	    return sbStackTrace.toString();
    } 
    private static String threadId() {
    	return String.valueOf(Thread.currentThread().getId());
    }
    private static String timeStamp() {
    	return new java.text.SimpleDateFormat(timeStampFormat).format(new Date());
	}    
}
