package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
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
	 * Copy constructor that performs deep copy
	 * @param sr 
	 */
	public SourceResults(SourceResults sr){
		this(sr, false);
	}
	
	/**
	 * Copy constructor that performs a deep copy with option
	 * of omitting networks contained within DatabaseResult objects
	 * @param sr
	 * @param skipNetworks If true, skip copy of networks within DatabaseResult
	 *                     objects
	 */
	public SourceResults(SourceResults sr, boolean skipNetworks){
		if (sr == null){
			return;
		}
		if (sr.getResults() != null){
			_results = new ArrayList<>();
			for (SourceResult sResult : sr.getResults()){
				_results.add(new SourceResult(sResult, skipNetworks));
			}
		}
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
