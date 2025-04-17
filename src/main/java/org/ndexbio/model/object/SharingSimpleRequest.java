package org.ndexbio.model.object;

import java.util.Map;
import java.util.UUID;

public class SharingSimpleRequest {
    private Map<UUID, FileType> files;      // The object to share/unshare and type "network" | "folder"

    public SharingSimpleRequest() {}

    public Map<UUID, FileType> getFiles() {
        return files;
    }
    public void setFiles(Map<UUID, FileType> files) {
        this.files = files;
    }
}

