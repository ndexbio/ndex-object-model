package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;

/**
 * Contains a mapping of genes to mutation frequencies
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MutationFrequencies {
	
	private Map<String, Double> _mutationFrequencies;

	@Schema(description="Map of genes to mutation frequencies")
	public Map<String, Double> getMutationFrequencies() {
		return _mutationFrequencies;
	}

	public void setMutationFrequencies(Map<String, Double> _genes) {
		this._mutationFrequencies = _genes;
	}
	
	
}
