package org.ndexbio.model.object;

import java.util.List;
import java.util.UUID;

public class SharingRemoveRequest {
    private UUID uuid;
    private String type;       // "folder" or "network"
    private List<UUID> members;

    public SharingRemoveRequest() {}

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
    public List<UUID> getMembers() {
        return members;
    }
    public void setMembers(List<UUID> members) {
        this.members = members;
    }
}
