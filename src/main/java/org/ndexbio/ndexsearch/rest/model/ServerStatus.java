/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.ndexsearch.rest.model;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

/**
 * Represents status of the server
 * @author churas
 */
public class ServerStatus {
    
    
    public static final String OK_STATUS = "ok";
    public static final String ERROR_STATUS = "error";
    
    private String _status;
    private int _pcDiskFull;
    private List<Float> _load;
    private List<Integer> _queries;
    private String _restVersion;
    

    public ServerStatus(){
    }

    /**
     * Gets status of server which can be either {@link #OK_STATUS} or
     * {@link #ERROR_STATUS}
     * @return Status as a string
     */
    @Schema(description="Status of server", allowableValues={ServerStatus.OK_STATUS,
                                                             ServerStatus.ERROR_STATUS})
    public String getStatus() {
        return _status;
    }

    public void setStatus(String _status) {
        this._status = _status;
    }

    @Schema(description="Gets how full disk is as a percentage 0 - 100")
    public int getPcDiskFull() {
        return _pcDiskFull;
    }

    public void setPcDiskFull(int _pcDiskFull) {
        this._pcDiskFull = _pcDiskFull;
    }

    @Schema(description="List of 3 floats containing 1 minute, 5 minute, 15minute load")
    public List<Float> getLoad() {
        return _load;
    }

    public void setLoad(List<Float> _load) {
        this._load = _load;
    }

    @Schema(description="List of 5 integers containing # queries in last minute, 5 minutes, 15 minutes, hour, 24 hours")
    public List<Integer> getQueries() {
        return _queries;
    }

    public void setQueries(List<Integer> _queries) {
        this._queries = _queries;
    }

   
    @Schema(description="Gets version of this service")
    public String getRestVersion() {
        return _restVersion;
    }

    public void setRestVersion(String _restVersion) {
        this._restVersion = _restVersion;
    }    
}
