package org.ndexbio.ndexsearch.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

/**
 * Represents a Query
 * @author churas
 */
public class Query {
    
    private List<String> _geneList;
    private List<String> _sourceList;

    @Schema(description="List of genes")
    public List<String> getGeneList() {
        return _geneList;
    }

    public void setGeneList(List<String> _geneList) {
        this._geneList = _geneList;
    }

    @Schema(description="List of sources")
    public List<String> getSourceList() {
        return _sourceList;
    }

    public void setSourceList(List<String> _sourceList) {
        this._sourceList = _sourceList;
    }
}
