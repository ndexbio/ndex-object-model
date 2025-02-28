package org.ndexbio.model.object;

import java.util.UUID;

public class Folder extends NdexExternalObject {
	private String name;
	private UUID parent;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getParent() {
		return parent;
	}
	public void setParent(UUID parent) {
		this.parent = parent;
	}

}
