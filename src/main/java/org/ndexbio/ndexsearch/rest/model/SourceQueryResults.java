package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.UUID;

/**
 * Represents a list of query results against a sources
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceQueryResults {
    
    private UUID _sourceUUID;
    private String _sourceName;
    private String _status;
    private String _message;
    private int _progress;
    private long _wallTime;
    private int _numberOfHits;
    private int _sourceRank;
    private String _sourceTaskId;
    private List<SourceQueryResult> _results;

    public UUID getSourceUUID() {
        return _sourceUUID;
    }

    public void setSourceUUID(UUID _sourceUUID) {
        this._sourceUUID = _sourceUUID;
    }

    public String getSourceName() {
        return _sourceName;
    }

    public void setSourceName(String _sourceName) {
        this._sourceName = _sourceName;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String _status) {
        this._status = _status;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String _message) {
        this._message = _message;
    }

    public int getProgress() {
        return _progress;
    }

    public void setProgress(int _progress) {
        this._progress = _progress;
    }

    public long getWallTime() {
        return _wallTime;
    }

    public void setWallTime(long _wallTime) {
        this._wallTime = _wallTime;
    }

    public int getNumberOfHits() {
        return _numberOfHits;
    }

    public void setNumberOfHits(int _numberOfHits) {
        this._numberOfHits = _numberOfHits;
    }

    public int getSourceRank() {
        return _sourceRank;
    }

    public void setSourceRank(int _sourceRank) {
        this._sourceRank = _sourceRank;
    }

    public List<SourceQueryResult> getResults() {
        return _results;
    }

    public void setResults(List<SourceQueryResult> _results) {
        this._results = _results;
    }

	public String getSourceTaskId() {
		return _sourceTaskId;
	}

	public void setSourceTaskId(String _sourceTaskId) {
		this._sourceTaskId = _sourceTaskId;
	}
    
    
    
}
