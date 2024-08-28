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

	/**
	 * Constructor
	 */
	public AlterationData(){
		
	}
	
	/**
	 * Gets gene name/symbol
	 * @return the gene
	 */
	@Schema(description="Gene name/symbol")
	public String getGene() {
		return _gene;
	}

	/**
	 * Sets gene name/symbol
	 * 
	 * @param _gene gene to set
	 */
	public void setGene(String _gene) {
		this._gene = _gene;
	}

	/**
	 * Gets percent altered or mutated
	 * @return percent altered
	 */
	@Schema(description="% altered or mutated")
	public String getPercentAltered() {
		return _percentAltered;
	}

	/**
	 * Sets percent altered or mutated
	 * @param _percentAltered percent altered
	 */
	public void setPercentAltered(String _percentAltered) {
		this._percentAltered = _percentAltered;
	}
}
