package org.ndexbio.model.cx;

import org.cxio.aspects.datamodels.AbstractAspectElement;
import org.ndexbio.model.object.ProvenanceEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Provenance extends AbstractAspectElement {

	public static final String NAME="provenanceHistory";
	
	private ProvenanceEntity entity;
	
	@Override
	@JsonIgnore
	public String getAspectName() {
				return NAME;
	}

	public ProvenanceEntity getEntity() {
		return entity;
	}

	public void setEntity(ProvenanceEntity entity) {
		this.entity = entity;
	}

}
