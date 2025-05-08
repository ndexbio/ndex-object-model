package org.ndexbio.model.object;

import org.ndexbio.model.object.network.VisibilityType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Request object for setting visibility of files")
public class SetVisibilityRequest {
    @Schema(description = "Visibility type to be set", required = true)
    private VisibilityType visibility;
    
    @Schema(description = "Map of item UUIDs and their types to be updated", required = true)
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
