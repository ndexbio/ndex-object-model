package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

/**
 *
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResults extends QueryStatus {

    private List<SourceQueryResults> _sources;
    
    private ValidatedQueryGenes _validatedGenes;

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
	public ValidatedQueryGenes getValidatedGenes() {
		return _validatedGenes;
	}
	public void setValidatedGenes(ValidatedQueryGenes _valdatedGenes) {
		this._validatedGenes = _valdatedGenes;
	} 
    
    
}
