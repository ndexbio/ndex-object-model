package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;

/**
 * Represents the configuration of a source for the integrated search.
 * 
 * @author dotasek
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceConfiguration {
    
    
    private String _description;
    private String _name;
    private String _endPoint;
    private UUID _uuid;

    /**
     * Gets description of source
     * @return description as string
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets description of source
     * @param _description description to set
     */
    public void setDescription(String _description) {
        this._description = _description;
    }

    /**
     * Gets name of source
     * @return name of source
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets name of source
     * @param _name name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * Gets REST endpoint URL
     * @return URL as string
     */
    public String getEndPoint() {
        return _endPoint;
    }

    /**
     * Sets REST endpoint URL
     * @param _endPoint URL as string
     */
    public void setEndPoint(String _endPoint) {
        this._endPoint = _endPoint;
    }

	public UUID getUuid() {
		return _uuid;
	}

	public void setUuid(String _uuid) {
		this._uuid = UUID.fromString(_uuid);
	}

}
