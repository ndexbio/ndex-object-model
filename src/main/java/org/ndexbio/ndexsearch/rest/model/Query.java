package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;

/**
 * Represents a Query
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
    
    private List<String> _geneList;
    private List<String> _sourceList;
	private Map<String, String> _geneAnnotationServices;
	private List<AlterationData> _alterationData;
	
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
	
	@Schema(description="Optional map of gene Annotation services")
	public Map<String, String> getGeneAnnotationServices(){
		return this._geneAnnotationServices;
	}
	
	public void setGeneAnnotationServices(Map<String, String> _geneAnnotationServices){
		this._geneAnnotationServices = _geneAnnotationServices;
	}

	public List<AlterationData> getAlterationData() {
		return _alterationData;
	}

	@Schema(description="Optional Alteration data for genes in query")
	public void setAlterationData(List<AlterationData> _alterationData) {
		this._alterationData = _alterationData;
	}
	
	
}
