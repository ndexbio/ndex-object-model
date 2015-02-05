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
    private boolean _isDeleted;
	
	
	public NdexExternalObject () {
		setCreationTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		this._modificationTime = this._creationTime;
		_isDeleted = false;
	}

	public Timestamp getModificationTime() {
		return _modificationTime;
	}

	public void setModificationTime(Timestamp modificationTime) {
		this._modificationTime = modificationTime;
	}

	public Timestamp getCreationTime() {
		return _creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this._creationTime = creationTime;
	}

	public UUID getExternalId() {
		return _externalId;
	}

	public void setExternalId(UUID externalId) {
		this._externalId = externalId;
	}
	
	public boolean getIsDeleted() {
		return _isDeleted;
	}


	public void setIsDeleted(boolean isDeleted) {
		this._isDeleted = isDeleted;
	}


}
