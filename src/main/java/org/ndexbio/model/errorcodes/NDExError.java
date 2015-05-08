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
	private String stackTrace() {
    	// get stack trace; 
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StringBuilder sbStackTrace = new StringBuilder();
	    for (StackTraceElement element : stackTrace) {
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
