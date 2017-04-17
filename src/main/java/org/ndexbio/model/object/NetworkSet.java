package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NetworkSet extends NdexExternalObject {

	private String name;
	private String description;
	private UUID ownerId;
	private List<UUID> networks;
	
	public NetworkSet () {
		super();
		networks = new ArrayList<>(30);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}
	public List<UUID> getNetworks() {
		return networks;
	}
	public void setNetworks(List<UUID> networks) {
		this.networks = networks;
	}
	
}
