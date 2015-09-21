package org.ndexbio.model.cx;

import java.sql.Timestamp;

import org.cxio.core.interfaces.AspectElement;
import org.ndexbio.model.object.network.VisibilityType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NdexNetworkStatus implements AspectElement {

	public final static String NAME = "ndexStatus";
	
	private String externalId;
	private Timestamp creationTime;
	private Timestamp modificationTime;
	private VisibilityType visibility;
	private boolean isPublished;
	private String version;
	private int nodeCount;
	private int edgeCount;
	private String owner;
	private String ndexServerURI;
	
	public NdexNetworkStatus() {
	}

	@Override
	@JsonIgnore
	public String getAspectName() {
		return NAME;
	}

	@Override
	@JsonIgnore
	public long getSum() {
		return 0;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Timestamp modificationTime) {
		this.modificationTime = modificationTime;
	}

	public VisibilityType getVisibility() {
		return visibility;
	}

	public void setVisibility(VisibilityType visibility) {
		this.visibility = visibility;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

	public void setEdgeCount(int edgeCount) {
		this.edgeCount = edgeCount;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getNdexServerURI() {
		return ndexServerURI;
	}

	public void setNdexServerURI(String ndexServerURI) {
		this.ndexServerURI = ndexServerURI;
	}

}
