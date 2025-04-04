package org.ndexbio.model.object;

import java.util.UUID;

public class SharingSimpleRequest {
    private UUID uuid;      // The object to share/unshare
    private String type;    // "network" | "folder"

    public SharingSimpleRequest() {}

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
}

