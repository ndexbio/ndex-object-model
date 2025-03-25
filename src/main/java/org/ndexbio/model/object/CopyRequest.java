package org.ndexbio.model.object;

import java.util.UUID;

public class CopyRequest {
    private UUID from_uuid;
    private String type;    // "network", "folder" or "shortcut"
    private UUID to_path;   // Destination folder ID

    public CopyRequest() {}

    public UUID getFrom_uuid() {
        return from_uuid;
    }
    public void setFrom_uuid(UUID from_uuid) {
        this.from_uuid = from_uuid;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public UUID getTo_path() {
        return to_path;
    }
    public void setTo_path(UUID to_path) {
        this.to_path = to_path;
    }
}
