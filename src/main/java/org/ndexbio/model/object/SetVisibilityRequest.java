package org.ndexbio.model.object;

import org.ndexbio.model.object.network.VisibilityType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

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
