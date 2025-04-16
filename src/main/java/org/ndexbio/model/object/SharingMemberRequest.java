package org.ndexbio.model.object;

import java.util.Map;
import java.util.UUID;

public class SharingMemberRequest {
    private Map<UUID, FileType> files;       // map of file UUID and type: "folder" or "network"
    private Map<UUID, Permissions> members;

    public SharingMemberRequest() {}

    public Map<UUID, FileType> getFiles() {
        return files;
    }
    public void setFiles(Map<UUID, FileType> files) {
        this.files = files;
    }
    public Map<UUID,Permissions> getMembers() {
        return members;
    }
    public void setMembers(Map<UUID,Permissions> members) {
        this.members = members;
    }
}
