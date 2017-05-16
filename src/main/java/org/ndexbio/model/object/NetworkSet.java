package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NetworkSet extends NdexExternalObject {

	private String name;
	private String description;
	private UUID ownerId;
	private List<UUID> networks;
	private boolean showcased;
	
    private Map<String, Object> properties;

	
	public NetworkSet () {
		super();
		networks = new ArrayList<>(30);
		properties = new HashMap<>();
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

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public boolean isShowcased() {
		return showcased;
	}

	public void setShowcased(boolean showcased) {
		this.showcased = showcased;
	}
	
}
