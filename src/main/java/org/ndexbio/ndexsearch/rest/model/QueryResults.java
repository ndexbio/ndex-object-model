package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
