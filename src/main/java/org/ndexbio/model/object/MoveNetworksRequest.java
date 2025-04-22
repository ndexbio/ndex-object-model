package org.ndexbio.model.object;

import java.util.List;
import java.util.UUID;


public class MoveNetworksRequest {
    private UUID targetFolder;
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

