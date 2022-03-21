package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;

/**
 * Contains a mapping of genes to mutation frequencies
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneList {
	
	private List<String> _genes;

	public List<String> getGenes() {
		return _genes;
	}

	public void setGenes(List<String> _genes) {
		this._genes = _genes;
	}
}
