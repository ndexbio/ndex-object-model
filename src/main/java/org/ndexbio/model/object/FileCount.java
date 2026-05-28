package org.ndexbio.model.object;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Object containing counts of different file types")
public class FileCount {
	@Schema(description = "Number of network files", required = true)
	private long network;
	
	@Schema(description = "Number of folder files", required = true)
	private long folder;
	
	@Schema(description = "Number of shortcut files", required = true)
	private long shortcut;
	
	public long getNetwork() {
		return network;
	}
	public void setNetwork(long network) {
		this.network = network;
	}
	public long getFolder() {
		return folder;
	}
	public void setFolder(long folder) {
		this.folder = folder;
	}
	public long getShortcut() {
		return shortcut;
	}
	public void setShortcut(long shortcut) {
		this.shortcut = shortcut;
	}
	
}
