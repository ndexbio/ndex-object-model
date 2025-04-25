package org.ndexbio.model.object;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

public class CopyRequest {
    private UUID fileId;
    private FileType type;    // "network" or "shortcut"
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
