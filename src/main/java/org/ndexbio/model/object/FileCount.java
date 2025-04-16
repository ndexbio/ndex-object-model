package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

public class FileCount {
	private long network;
	private long folder;
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
