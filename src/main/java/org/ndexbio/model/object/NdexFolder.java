package org.ndexbio.model.object;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Represents a folder in the NDEx system")
public class NdexFolder extends NdexExternalObject {
	@Schema(description = "Name of the folder", required = true)
	private String name;
	
	@Schema(description = "UUID of the parent folder", required = true)
	private UUID parent;

	@Schema(description = "Description of the folder")
	private String description;

	@Schema(description = "Owner ID of the folder")
	private String owner_id;

	@Schema(description = "Owner username of the folder")
	private String owner;



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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

}
