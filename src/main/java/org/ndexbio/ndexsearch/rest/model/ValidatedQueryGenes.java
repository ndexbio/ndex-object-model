package org.ndexbio.ndexsearch.rest.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ValidatedQueryGenes {

	
	private Set<String> queryGenes;
	private Set<String> invalid;
	
	private Map<String,String> normalizedGenes;


	public ValidatedQueryGenes() {
		setQueryGenes(new TreeSet<>());
		setInvalid(new TreeSet<>());
		
		setNormalizedGenes(new HashMap<>());
	}


	public Set<String> getQueryGenes() {
		return queryGenes;
	}


	public void setQueryGenes(Set<String> queryGenes) {
		this.queryGenes = queryGenes;
	}


	public Set<String> getInvalid() {
		return invalid;
	}


	public void setInvalid(Set<String> invalid) {
		this.invalid = invalid;
	}


	public Map<String,String> getNormalizedGenes() {
		return normalizedGenes;
	}


	public void setNormalizedGenes(Map<String,String> normalizedGenes) {
		this.normalizedGenes = normalizedGenes;
	}
	
	
}
