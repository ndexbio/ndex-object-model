package org.ndexbio.model.object;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NdexExternalObject extends NdexObject {
	
	private UUID _externalId; 
	private Timestamp _creationTime;
	
	private Timestamp _modificationTime;
	
	public NdexExternalObject () {
		setCreationTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		this._modificationTime = this._creationTime;
	}

	public Timestamp getModificationTime() {
		return _modificationTime;
	}

	public void setModificationTime(Timestamp _modificationTime) {
		this._modificationTime = _modificationTime;
	}

	public Timestamp getCreationTime() {
		return _creationTime;
	}

	public void setCreationTime(Timestamp _creationTime) {
		this._creationTime = _creationTime;
	}

	public UUID getExternalId() {
		return _externalId;
	}

	public void setExternalId(UUID externalId) {
		this._externalId = externalId;
	}

}
