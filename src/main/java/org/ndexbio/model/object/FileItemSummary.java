package org.ndexbio.model.object;

import java.lang.Boolean;
import java.sql.Timestamp;
import java.util.List;
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
    
    @Schema(description = "Indicates if the file is read-only")
    private Boolean isReadOnly;
    
    @Schema(description = "Error message if the file has issues")
	private String errorMessage;
	
    @Schema(description = "List of warnings associated with the file")
	private List<String> warnings;
	
    @Schema(description = "Indicates if the file processing is completed")
	private Boolean isCompleted;
    
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
            String updatedBy,
            Map<String,Object> attributes) {
    	this(uuid,type,name);
        this.modificationTime = modificationTime;
        this.updatedBy        = updatedBy;
        this.attributes      = attributes;
    }

    public FileItemSummary(UUID uuid,
    		FileType type,
            String name,
            Timestamp modificationTime,
            String updatedBy,
            Map<String,Object> attributes, 
            Boolean isReadOnly, 
            String errorMessage, 
            List<String> warnings, 
            Boolean isCompleted) {
    	this(uuid,type,name,modificationTime,updatedBy,attributes);
    	this.isReadOnly = isReadOnly;
        this.errorMessage = errorMessage;
        this.warnings = warnings;
        this.isCompleted = isCompleted;
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
    public Boolean getIsReadOnly() { 
    	return isReadOnly; 
    }
    public void setIsReadOnly(Boolean isReadOnly) { 
    	this.isReadOnly = isReadOnly;
    }

    public String getErrorMessage() { 
    	return errorMessage; 
    }
    public void setErrorMessage(String errorMessage) { 
    	this.errorMessage = errorMessage; 
    }
    public List<String> getWarnings() { 
    	return warnings; 
    }
    public void setWarnings(List<String> warnings) { 
    	this.warnings = warnings; 
    }
    public Boolean getIsCompleted() { 
    	return isCompleted; 
    }
    public void setIsCompleted(Boolean isCompleted) { 
    	this.isCompleted = isCompleted; 
    }
}