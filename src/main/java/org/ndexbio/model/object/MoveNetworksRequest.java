package org.ndexbio.model.object;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for moving networks to a target folder")
public class MoveNetworksRequest {
    @Schema(description = "UUID of the target folder where networks will be moved to", required = true)
    private UUID targetFolder;
    
    @Schema(description = "List of network UUIDs to be moved", required = true)
    private List<UUID> networks;

    public UUID getTargetFolder() {
        return targetFolder;
    }

    public void setTargetFolder(UUID targetFolder) {
        this.targetFolder = targetFolder;
    }

    public List<UUID> getNetworks() {
        return networks;
    }

    public void setNetworks(List<UUID> networks) {
        this.networks = networks;
    }
}

