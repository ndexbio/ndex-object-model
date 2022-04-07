package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Contains information about sources that can
 * be queried by this search
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceResults {
    
    private List<SourceResult> _results;

    /**
     * Constructor
     */
    public SourceResults(){
        this(null);
    }
    
    /**
     * Performs a shallow copy of {@link org.ndexbio.ndexsearch.rest.model.InternalSourceResults}
     * passed in via {@code isr} parameter
     * @param isr {@link org.ndexbio.ndexsearch.rest.model.InternalSourceResults} object to copy from
     */
    public SourceResults(InternalSourceResults isr){
        if (isr == null){
            return;
        }
        _results = isr.getResults();
    }
    
    /**
     * Gets list of sources
     * @return 
     */
    public List<SourceResult> getResults() {
        return _results;
    }

    /**
     * Sets list of sources
     * @param _results 
     */
    public void setResults(List<SourceResult> _results) {
        this._results = _results;
    }
    
}
