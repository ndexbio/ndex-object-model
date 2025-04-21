package org.ndexbio.model.object;

import java.util.UUID;

public class ShortcutRequest {
	private String name;
	private UUID parent;
	private UUID target;
	private FileType targetType;
	
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
	public UUID getTarget() {
		return target;
	}
	public void setTarget(UUID target) {
		this.target = target;
	}
	public FileType getTargetType() {
		return targetType;
	}
	public void setTargetType(FileType target_type) {
		this.targetType = target_type;
	}

}
