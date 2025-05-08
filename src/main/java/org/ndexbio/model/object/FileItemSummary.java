package org.ndexbio.model.object;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Summary information about a file item")
public class FileItemSummary {
    @Schema(description = "Unique identifier of the file", required = true)
    private UUID uuid;
    
    @Schema(description = "Type of the file (folder, network, or shortcut)", required = true)
    private FileType type; // "folder", "network", or "shortcut"
    
    @Schema(description = "Name of the file", required = true)
    private String name;
    
    @Schema(description = "Last modification timestamp")
    private Timestamp modificationTime;
    
    @Schema(description = "Username of the user who last updated the file")
    private String updatedBy;
    
    @Schema(description = "Additional attributes associated with the file")
    private Map<String,Object> attributes;

    public FileItemSummary() {}

    public FileItemSummary(UUID uuid, FileType type, String name) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;
    }
    
    public FileItemSummary(UUID uuid,
    		FileType type,
            String name,
            Timestamp modificationTime,
            String updatedBy) {
    	this(uuid,type,name);;
		this.modificationTime = modificationTime;
		this.updatedBy        = updatedBy;
	}
    
    public FileItemSummary(UUID uuid,
    		FileType type,
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

    public FileType getType() {
        return type;
    }
    public void setType(FileType type) {
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