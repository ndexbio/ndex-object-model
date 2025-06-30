package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generalized query object for searching files (networks, folders, shortcuts, etc).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleFileQuery extends SimpleQuery {

    private String _accountName;
    private Permissions _permission;

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
} 