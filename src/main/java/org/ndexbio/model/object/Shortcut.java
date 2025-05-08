package org.ndexbio.model.object;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Represents a shortcut in the NDEx system")
public class Shortcut extends NdexExternalObject {
	@Schema(description = "Name of the shortcut", required = true)
	private String name;
	
	@Schema(description = "UUID of the parent folder", required = true)
	private UUID parent;
	
	@Schema(description = "UUID of the target item", required = true)
	private UUID target;
	
	@Schema(description = "Type of the target item", required = true)
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
	public void setTargetType(FileType targetType) {
		this.targetType = targetType;
	}
}
