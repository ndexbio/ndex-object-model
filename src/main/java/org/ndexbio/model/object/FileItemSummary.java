package org.ndexbio.model.object;

import java.util.UUID;

public class FileItemSummary {
    private UUID uuid;
    private String type; // "folder", "network", or "shortcut"
    private String name;

    public FileItemSummary() {}

    public FileItemSummary(UUID uuid, String type, String name) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}