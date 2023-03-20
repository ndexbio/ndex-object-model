package org.ndexbio.model.object;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(Include.NON_NULL)

public class NdexObjectUpdateStatus {
	
	private UUID uuid;
	
	private Timestamp modificationTime;
	
	
	public NdexObjectUpdateStatus() {}
	
	public NdexObjectUpdateStatus(UUID uuid, Timestamp t) {
		this.uuid = uuid;
		this.modificationTime = t;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Timestamp getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Timestamp modificationTime) {
		this.modificationTime = modificationTime;
	}

}
