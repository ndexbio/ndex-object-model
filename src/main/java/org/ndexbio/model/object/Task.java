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
package org.ndexbio.model.object;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.ndexbio.model.object.network.FileFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Task extends NdexExternalObject
{
    private String _description;
    private Priority _priority;
    private int _progress;
    private String _resource;
    private Status _status;
    private TaskType _taskType;
    private FileFormat _format;
   
    private UUID _taskOwnerId;
    private Timestamp startTime;
    private Timestamp finishTime;
    private String _message; 
    private Map<String, Object> _attributes;
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Task()
    {
        super();
        this._type = this.getClass().getSimpleName();
        setIsDeleted(false);
        setAttributes(new HashMap<String, Object> ());
        _priority = Priority.LOW;
    }


    
    public String getDescription()
    {
        return this._description;
    }
    
    public void setDescription(String description)
    {
        this._description = description;
    }
    
    public Priority getPriority()
    {
        return _priority;
    }
    
    public void setPriority(Priority priority)
    {
        _priority = priority;
    }
    
    public int getProgress()
    {
        return _progress;
    }
    
    public void setProgress(int progress)
    {
        _progress = progress;
    }
    
    public String getResource()
    {
        return _resource;
    }
    
    public void setResource(String resource)
    {
        _resource = resource;
    }

    public Status getStatus()
    {
        return _status;
    }

    public void setStatus(Status status)
    {
        _status = status;
    }
    
    public TaskType getTaskType()
    {
        return _taskType;
    }
    
    public void setTaskType(TaskType type)
    {
        _taskType = type;
    }



	public UUID getTaskOwnerId() {
		return _taskOwnerId;
	}



	public void setTaskOwnerId(UUID taskOwnerId) {
		this._taskOwnerId = taskOwnerId;
	}



	public FileFormat getFormat() {
		return _format;
	}



	public void setFormat(FileFormat format) {
		this._format = format;
	}


	public Timestamp getStartTime() {
		return startTime;
	}



	public void setStartTime(Timestamp start_time) {
		this.startTime = start_time;
	}



	public Timestamp getFinishTime() {
		return finishTime;
	}



	public void setFinishTime(Timestamp finish_time) {
		this.finishTime = finish_time;
	}



	public String getMessage() {
		return _message;
	}



	public void setMessage(String message) {
		this._message = message;
	}



	public Map<String, Object> getAttributes() {
		return _attributes;
	}



	public void setAttributes(Map<String, Object> attributes) {
		this._attributes = attributes;
	}
	
	public void setAttribute(String attrName, Object value) {
	  _attributes.put(attrName, value);
	  
	}
	
	public Object getAttribute(String attrName) {
		return _attributes.get(attrName);
	}
}
