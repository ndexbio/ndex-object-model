package org.ndexbio.model.object;

import java.security.Permission;
import java.util.Map;
import java.util.UUID;

public class SharingMemberRequest {
    private Map<UUID, FileType> files;       // map of file UUID and type: "folder" or "network"
    private Map<UUID,Permission> members;

    public SharingMemberRequest() {}

    public Map<UUID, FileType> getFiles() {
        return files;
    }
    public void setFiles(Map<UUID, FileType> files) {
        this.files = files;
    }
    public Map<UUID,Permission> getMembers() {
        return members;
    }
    public void setMembers(Map<UUID,Permission> members) {
        this.members = members;
    }
}
