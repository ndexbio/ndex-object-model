package org.ndexbio.model.object;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL) 

public class FileItemSummary {
    private UUID uuid;
    private String type; // "folder", "network", or "shortcut"
    private String name;
    private Timestamp  modificationTime; 
    private String     updatedBy;
    private Map<String,Object> attributes;

    public FileItemSummary() {}

    public FileItemSummary(UUID uuid, String type, String name) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;
    }
    
    public FileItemSummary(UUID uuid,
            String type,
            String name,
            Timestamp modificationTime,
            String updatedBy) {
    	this(uuid,type,name);;
		this.modificationTime = modificationTime;
		this.updatedBy        = updatedBy;
	}
    
    public FileItemSummary(UUID uuid,
            String type,
            String name,
            Timestamp modificationTime,
            String updatedBy,
            Map<String,Object> attributes) {
    	this(uuid,type,name,modificationTime,updatedBy);
    	this.attributes = attributes;
	}

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Timestamp getModificationTime() {
    	return modificationTime; 
    }
    public void setModificationTime(Timestamp modificationTime) {
        this.modificationTime = modificationTime;
    }
    
    public String getUpdatedBy() { 
    	return updatedBy; 
    }
    public void setUpdatedBy(String updatedBy) { 
    	this.updatedBy = updatedBy; 
    }
    
    public Map<String,Object> getAttributes() { 
    	return attributes; 
    }
    public void setAttributes(Map<String,Object> attributes) { 
    	this.attributes = attributes; 
    }
}