package org.ndexbio.model.object;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents the status of a shortcut's target")
public enum ShortcutTargetStatus {
	@Schema(description = "Target is active and accessible")
	ACTIVE,
	
	@Schema(description = "Target is in trash but can be restored")
	IN_TRASH,
	
	@Schema(description = "Target has been permanently deleted")
	DELETED
}

