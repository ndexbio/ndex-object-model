package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Generalized query object for searching files (networks, folders, shortcuts, etc).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleFileQuery extends SimpleQuery {

    private String _accountName;
    private Permissions _permission;
	
	@Schema(description = "Type of File. if null can be anything")
	private FileType _type;

    public SimpleFileQuery() {
    }

    public void setAccountName(String accountName) {
        this._accountName = accountName;
    }

    public String getAccountName() {
        return _accountName;
    }

    public void setPermission(Permissions permission) {
        this._permission = permission;
    }

    public Permissions getPermission() {
        return _permission;
    }

	/**
	 * Gets type of file, null assumes file can be anytype
	 * @return 
	 */
	public FileType getType() {
		return _type;
	}

	public void setType(FileType type) {
		this._type = type;
	}
	
	
} 