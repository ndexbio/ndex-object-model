package org.ndexbio.model.object;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

public class TransferOwnershipRequest {
	private List<UUID> networks;
    private UUID new_owner;

    public TransferOwnershipRequest() {}

    public List<UUID> getNetworks() {
        return networks;
    }
    public void setNetworks(List<UUID> networks) {
        this.networks = networks;
    }

    public UUID getNewOwner() {
        return new_owner;
    }
    public void setNewOwner(UUID new_owner) {
        this.new_owner = new_owner;
    }
}
