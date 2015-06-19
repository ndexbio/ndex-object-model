/**
 * Copyright (c) 2013, 2015
 *  	The Regents of the University of California, The Cytoscape Consortium
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
