package org.ndexbio.model.object;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Request object for restoring items from trash")
public class TrashRestoreRequest {
    @Schema(description = "List of network UUIDs to be restored from trash")
    private List<UUID> networks;
    
    @Schema(description = "List of folder UUIDs to be restored from trash")
    private List<UUID> folders;
    
    @Schema(description = "List of shortcut UUIDs to be restored from trash")
    private List<UUID> shortcuts;

    public TrashRestoreRequest() {}

    public List<UUID> getNetworks() {
        return networks;
    }
    public void setNetworks(List<UUID> networks) {
        this.networks = networks;
    }

    public List<UUID> getFolders() {
        return folders;
    }
    public void setFolders(List<UUID> folders) {
        this.folders = folders;
    }

    public List<UUID> getShortcuts() {
        return shortcuts;
    }
    public void setShortcuts(List<UUID> shortcuts) {
        this.shortcuts = shortcuts;
    }
}
