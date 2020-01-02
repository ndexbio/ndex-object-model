package org.ndexbio.ndexsearch.rest.model;

import java.util.List;

/**
 * Contains information about sources that can
 * be queried by this search
 * @author churas
 */
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
    
    
    public SourceConfiguration getSourceConfigurationByName (String name) {
    	if ( _sources == null || name == null ) return null;
    	
		for ( SourceConfiguration conf : _sources) {
			if (conf.getName().contentEquals(name) ) {
				return conf;
			}
		}
		
		return null;
	}
    
}
