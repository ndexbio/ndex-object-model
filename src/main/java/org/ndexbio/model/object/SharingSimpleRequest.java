package org.ndexbio.model.object;

import java.util.UUID;

public class SharingSimpleRequest {
    private UUID uuid;      // The object to share/unshare
    private FileType type;    // "network" | "folder"

    public SharingSimpleRequest() {}

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
}

