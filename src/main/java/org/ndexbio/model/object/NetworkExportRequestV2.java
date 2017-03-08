package org.ndexbio.model.object;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


/**
 * exportFormat is case insensitive. always get converted to lowercase by NDEx.
 * @author chenjing
 *
 */
public class NetworkExportRequestV2 {
	private String exportFormat;
	private Set<UUID> networkIds;
	
	public NetworkExportRequestV2 () {}

	
	
	public String getExportFormat() {
		return exportFormat;
	}

	
	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}

	public Set<UUID> getNetworkIds() {
		return networkIds;
	}

	public void setNetworkIds(Set<UUID> uuids) {
		this.networkIds = uuids;
	}
	
	
	
}
