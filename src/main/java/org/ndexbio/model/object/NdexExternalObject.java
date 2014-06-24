package org.ndexbio.model.object;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NdexExternalObject extends NdexObject {
	
	private UUID _externalId; 
	private Date _creationDate;
	
	private Date _modificationDate;
	
	public NdexExternalObject () {
		setCreationDate(new Date());
		setModificationDate(new Date());
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date _modificationDate) {
		this._modificationDate = _modificationDate;
	}

	public Date getCreationDate() {
		return _creationDate;
	}

	public void setCreationDate(Date _creationDate) {
		this._creationDate = _creationDate;
	}

	public UUID getExternalId() {
		return _externalId;
	}

	public void setExternalId(UUID externalId) {
		this._externalId = externalId;
	}

}
