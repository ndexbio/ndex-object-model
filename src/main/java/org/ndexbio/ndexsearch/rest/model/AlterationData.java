package org.ndexbio.ndexsearch.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents alteration/mutation data for genes in query
 * @author churas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlterationData {
	
	private String _gene;
	private String _percentAltered;

	@Schema(description="Gene name/symbol")
	public String getGene() {
		return _gene;
	}

	public void setGene(String _gene) {
		this._gene = _gene;
	}

	@Schema(description="% altered or mutated")
	public String getPercentAltered() {
		return _percentAltered;
	}

	public void setPercentAltered(String _percentAltered) {
		this._percentAltered = _percentAltered;
	}
	
	
	
}
