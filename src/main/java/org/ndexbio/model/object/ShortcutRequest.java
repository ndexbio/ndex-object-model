package org.ndexbio.model.object;

import java.util.UUID;
import org.ndexbio.model.object.network.VisibilityType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Request object for creating a new shortcut")
public class ShortcutRequest {
	@Schema(description = "Name of the shortcut", required = true)
	private String name;
	
	@Schema(description = "UUID of the parent folder")
	private UUID parent;
	
	@Schema(description = "UUID of the target item", required = true)
	private UUID target;
	
	@Schema(description = "Type of the target item", required = true)
	private FileType targetType;

	@Schema(description = "Visibility of the shortcut (defaults to PRIVATE when omitted)")
	private VisibilityType visibility;

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
	public VisibilityType getVisibility() {
		return visibility;
	}
	public void setVisibility(VisibilityType visibility) {
		this.visibility = visibility;
	}

}
