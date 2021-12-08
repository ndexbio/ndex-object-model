package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryStatus {
    
    public static final String SUBMITTED_STATUS = "submitted";
    public static final String PROCESSING_STATUS = "processing";
    public static final String COMPLETE_STATUS = "complete";
    public static final String FAILED_STATUS = "failed";

    private String _status;
    private String _message;
    private int _progress;
    private long _wallTime;
    private int _numberOfHits;
    private int _start;
    private int _size;
    private String _source;
    private long _startTime;
    private List<String> _inputSourceList;
    private List<String> _query;
    
    public QueryStatus(){
        
    }
    
    /**
     * Creates new {@link #EnrichmentQueryStatus} object setting {@link #getStartTime() }
     * to {@code startTime} passed into this method.
     * @param startTime Current time in milliseconds, usually set with value from {@link java.lang.System.currentTimeMillis()}
     */
    public QueryStatus(long startTime){
        _startTime = startTime;
    }
    
    /**
     * Creates new {@link #QueryStatus} by performing shallow copy of
     * {@code qr} passed in
     * @param qr {@link org.ndexbio.ndexsearch.rest.model.QueryResults} to copy from
     */
    public QueryStatus(QueryResults qr){
        if (qr == null){
            return;
        }
        _status = qr.getStatus();
        _message = qr.getMessage();
        _progress = qr.getProgress();
        _wallTime = qr.getWallTime();
        _numberOfHits = qr.getNumberOfHits();
        _start = qr.getStart();
        _size = qr.getSize();
        _source = qr.getSource();
        _startTime = qr.getStartTime();
        _inputSourceList = qr.getInputSourceList();
        _query = qr.getQuery();
    }
 
    /**
     * Updates start time with value from {@code eqs} passed in if that
     * time is greater then the time in this object.
     * @param eqs
     * @return Returns this object
     */
    public QueryStatus updateStartTime(QueryStatus eqs){
        if (eqs == null){
            return this;
        }
        if (eqs.getStartTime() > _startTime){
            _startTime = eqs.getStartTime();
        }
        return this;
    }

    public List<String> getInputSourceList() {
        return _inputSourceList;
    }

    public void setInputSourceList(List<String> _inputSourceList) {
        this._inputSourceList = _inputSourceList;
    }

    public List<String> getQuery() {
        return _query;
    }

    public void setQuery(List<String> _query) {
        this._query = _query;
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

    public int getStart() {
        return _start;
    }

    public void setStart(int _start) {
        this._start = _start;
    }

    public int getSize() {
        return _size;
    }

    public void setSize(int _size) {
        this._size = _size;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String _source) {
        this._source = _source;
    }

    public long getStartTime() {
        return _startTime;
    }

    public void setStartTime(long _startTime) {
        this._startTime = _startTime;
    }
}
