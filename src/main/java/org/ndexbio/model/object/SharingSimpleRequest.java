package org.ndexbio.model.object;

import java.util.Map;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Request object for simple file sharing or unsharing")
public class SharingSimpleRequest {
    @Schema(description = "Map of file UUIDs and their types to be shared/unshared", required = true)
    private Map<UUID, FileType> files;      // The object to share/unshare and type "network" | "folder"

    public SharingSimpleRequest() {}

    public Map<UUID, FileType> getFiles() {
        return files;
    }
    public void setFiles(Map<UUID, FileType> files) {
        this.files = files;
    }
}

