package org.ndexbio.model.object;


import java.sql.Timestamp;
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
    private boolean isDeleted;
    private Timestamp startTime;
    private Timestamp finishTime;
    private String _message; 
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Task()
    {
        super();
        this._type = this.getClass().getSimpleName();
        setIsDeleted(false);
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



	public boolean getIsDeleted() {
		return isDeleted;
	}



	public void setIsDeleted(boolean is_Deleted) {
		this.isDeleted = is_Deleted;
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
}
