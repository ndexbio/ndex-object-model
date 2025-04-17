package org.ndexbio.model.object;

import java.util.Map;
import java.util.UUID;

public class TransferRequest {
	private Map<UUID, FileType> files;    // files to transfer and type "folder" | "network"
    private UUID new_owner;

    public TransferRequest() {}

    public Map<UUID, FileType> getFiles() {
        return files;
    }
    public void setFiles(Map<UUID, FileType> files) {
        this.files = files;
    }

    public UUID getNewOwner() {
        return new_owner;
    }
    public void setNewOwner(UUID new_owner) {
        this.new_owner = new_owner;
    }
}
