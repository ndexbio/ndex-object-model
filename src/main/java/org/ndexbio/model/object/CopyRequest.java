package org.ndexbio.model.object;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Request object for copying a file to a target folder")
public class CopyRequest {
    @Schema(description = "UUID of the file to be copied", required = true)
    private UUID fileId;
    
    @Schema(description = "Type of the file (network or shortcut)", required = true)
    private FileType type;    // "network" or "shortcut"
    
    @Schema(description = "UUID of the target folder where the file will be copied to", required = true)
    private UUID targetId;   // Target folder ID

    public CopyRequest() {}

    public UUID getFileId() {
        return fileId;
    }
    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public FileType getType() {
        return type;
    }
    public void setType(FileType type) {
        this.type = type;
    }

    public UUID getTargetId() {
        return targetId;
    }
    public void setTargetId(UUID targetId) {
        this.targetId = targetId;
    }
}
