package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Contains information about sources that can
 * be queried by this search
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceConfigurations {
    
    private List<SourceConfiguration> _sources;

    /**
     * Constructor
     */
    public SourceConfigurations(){
        _sources = null;
    }
    

    
    /**
     * Gets list of source configurations
     * @return 
     */
    public List<SourceConfiguration> getSources() {
        return _sources;
    }

    /**
     * Sets the list of source configurations
     * @param _sources 
     */
    public void setSources(List<SourceConfiguration> _sources) {
        this._sources = _sources;
    }
    
    /**
	 * Examines all sources set via setSources() method for the first SourceConfiguration
	 * where getName() matches the name passed in
	 * @param name Name of SourceConfiguration to return
	 * @return 
	 */
    public SourceConfiguration getSourceConfigurationByName (String name) {
    	if ( _sources == null || name == null ) return null;
			
		for ( SourceConfiguration conf : _sources) {
			if (conf.getName() != null && conf.getName().contentEquals(name) ) {
				return conf;
			}
		}
		
		return null;
	}
    
}
