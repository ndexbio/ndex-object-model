package org.ndexbio.model.object;

import java.sql.Timestamp;
import java.util.UUID;

public class FileItemSummary {
    private UUID uuid;
    private String type; // "folder", "network", or "shortcut"
    private String name;
    private Timestamp  modificationTime; 
    private String     updatedBy;

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
		this.uuid             = uuid;
		this.type             = type;
		this.name             = name;
		this.modificationTime = modificationTime;
		this.updatedBy        = updatedBy;
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
}