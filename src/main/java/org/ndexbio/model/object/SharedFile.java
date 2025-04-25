package org.ndexbio.model.object;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

public class SharedFile {
	private UUID uuid;
	private FileType type;		// FOLDER or NETWORK
	private UUID ownerId;
	private String owner;
	private FileItemSummary fileSummary;
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public FileType getType() {
		return type;
	}
	public void setType(FileType type) {
		this.type = type;
	}
	public UUID getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public FileItemSummary getFileSummary() {
		return fileSummary;
	}
	public void setFileSummary(FileItemSummary fileSummary) {
		this.fileSummary = fileSummary;
	}

}
