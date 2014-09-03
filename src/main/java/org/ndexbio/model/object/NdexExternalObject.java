package org.ndexbio.model.object;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NdexExternalObject extends NdexObject {
	
	private UUID _externalId; 
	private Date _creationTime;
	
	private Date _modificationTime;
	
	public NdexExternalObject () {
		setCreationTime(new Date());
		setModificationTime(new Date());
	}

	public Date getModificationTime() {
		return _modificationTime;
	}

	public void setModificationTime(Date _modificationDate) {
		this._modificationTime = _modificationDate;
	}

	public Date getCreationTime() {
		return _creationTime;
	}

	public void setCreationTime(Date _creationDate) {
		this._creationTime = _creationDate;
	}

	public UUID getExternalId() {
		return _externalId;
	}

	public void setExternalId(UUID externalId) {
		this._externalId = externalId;
	}

}
