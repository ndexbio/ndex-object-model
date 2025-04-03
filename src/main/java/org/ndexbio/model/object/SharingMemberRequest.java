package org.ndexbio.model.object;

import java.util.Map;
import java.util.UUID;

public class SharingMemberRequest {
    private UUID uuid;
    private String type;       // "folder" or "network"
    private Map<UUID,String> members;

    public SharingMemberRequest() {}

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
    public Map<UUID,String> getMembers() {
        return members;
    }
    public void setMembers(Map<UUID,String> members) {
        this.members = members;
    }
}
