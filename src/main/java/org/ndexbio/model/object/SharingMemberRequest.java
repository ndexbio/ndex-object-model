package org.ndexbio.model.object;

import java.util.Map;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Request object for sharing files with users")
public class SharingMemberRequest {
    @Schema(description = "Map of file UUIDs and their types to be shared", required = true)
    private Map<UUID, FileType> files;       // map of file UUID and type: "folder" or "network"
    
    @Schema(description = "Map of member UUIDs and their permissions. If permission is null, the file permission will be revoked for the member.", required = true)
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
