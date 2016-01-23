package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.network.NetworkSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NetworkSearchResult {

	private long numFound;
	private long start;
	private List<NetworkSummary> networks;
	
	public NetworkSearchResult() {
		this.networks = new ArrayList<>();
	}
	public NetworkSearchResult(long numberOfResult, long startAt, List<NetworkSummary> networkList) {
		this.numFound = numberOfResult;
		this.start = startAt;
		this.networks = networkList;
	}

	public long getNumFound() {
		return numFound;
	}

	public void setNumFound(int numFound) {
		this.numFound = numFound;
	} 

	public long getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	} 

	public List<NetworkSummary> getNetworks() {
		return networks;
	}

	public void setNetworks(List<NetworkSummary> networks) {
		this.networks = networks;
	} 

	
}
