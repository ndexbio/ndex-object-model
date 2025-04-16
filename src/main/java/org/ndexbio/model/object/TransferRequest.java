package org.ndexbio.model.object;

import java.util.UUID;

public class TransferRequest {
    private UUID uuid;      
    private String type;    // "folder" | "network"
    private UUID to_user;

    public TransferRequest() {}

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

    public UUID getTo_user() {
        return to_user;
    }
    public void setTo_user(UUID to_user) {
        this.to_user = to_user;
    }
}
