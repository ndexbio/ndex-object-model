package org.ndexbio.model.object;

import java.util.UUID;

public class TransferRequest {
    private UUID uuid;      
    private FileType type;    // "folder" | "network"
    private UUID to_user;

    public TransferRequest() {}

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

    public UUID getTo_user() {
        return to_user;
    }
    public void setTo_user(UUID to_user) {
        this.to_user = to_user;
    }
}
