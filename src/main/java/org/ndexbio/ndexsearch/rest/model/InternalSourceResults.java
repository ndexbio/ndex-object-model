package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Contains any data that is part of 
 * {@link org.ndexbio.ndexsearch.rest.model.SourceResults} but
 * should not be returned to external tools/clients
 * 
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalSourceResults extends SourceResults {
    
}
