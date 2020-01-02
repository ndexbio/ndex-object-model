/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.ndexsearch.rest.model;

import java.util.List;

/**
 *
 * @author churas
 */
public class QueryResults extends QueryStatus {

    private List<SourceQueryResults> _sources;

    public QueryResults(){
        super();
    }
    public QueryResults(long startTime){
        super(startTime);
    }

    public QueryResults updateStartTime(QueryResults eqs) {
        super.updateStartTime(eqs);
        return this;
    }

    public List<SourceQueryResults> getSources() {
        return _sources;
    }

    public void setSources(List<SourceQueryResults> _sources) {
        this._sources = _sources;
    } 
}
