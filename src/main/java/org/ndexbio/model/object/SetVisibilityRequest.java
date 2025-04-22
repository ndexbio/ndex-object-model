package org.ndexbio.model.object;

import org.ndexbio.model.object.network.VisibilityType;

import java.util.Map;
import java.util.UUID;

public class SetVisibilityRequest {

    private VisibilityType visibility;
    private Map<UUID, FileType> items;

    public VisibilityType getVisibility() {
        return visibility;
    }

    public void setVisibility(VisibilityType visibility) {
        this.visibility = visibility;
    }

    public Map<UUID, FileType> getItems() {
        return items;
    }

    public void setItems(Map<UUID, FileType> items) {
        this.items = items;
    }
}
