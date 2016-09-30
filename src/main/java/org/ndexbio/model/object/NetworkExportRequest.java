package org.ndexbio.model.object;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NetworkExportRequest {
	private String networkFormat;
	private Set<UUID> networkIds;
	
	public NetworkExportRequest () {}

	public String getNetworkFormat() {
		return networkFormat;
	}

	public void setNetworkFormat(String networkFormat) {
		this.networkFormat = networkFormat;
	}

	public Set<UUID> getNetworkIds() {
		return networkIds;
	}

	public void setNetworkIds(Set<UUID> uuids) {
		this.networkIds = uuids;
	}
	
	
	
}
