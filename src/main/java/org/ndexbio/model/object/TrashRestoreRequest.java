package org.ndexbio.model.object;

import java.util.List;
import java.util.UUID;

public class TrashRestoreRequest {
    private List<UUID> networks;
    private List<UUID> folders;
    private List<UUID> shortcuts;

    public TrashRestoreRequest() {}

    public List<UUID> getNetworks() {
        return networks;
    }
    public void setNetworks(List<UUID> networks) {
        this.networks = networks;
    }

    public List<UUID> getFolders() {
        return folders;
    }
    public void setFolders(List<UUID> folders) {
        this.folders = folders;
    }

    public List<UUID> getShortcuts() {
        return shortcuts;
    }
    public void setShortcuts(List<UUID> shortcuts) {
        this.shortcuts = shortcuts;
    }
}
