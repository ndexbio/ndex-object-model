package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class DefaultVisualProperties {
	
	
	@JsonProperty("edge")
	private Map<String, Object> edgeProperties;
	
	
	@JsonProperty( "network")
	private Map<String,Object> networkProperties;
	
	@JsonProperty("node")
	private Map<String,Object> nodeProperties;
	
	public DefaultVisualProperties () {
		this.edgeProperties = new HashMap<>();
		this.nodeProperties = new HashMap<>();
		this.networkProperties = new HashMap<>();	
	}

	@JsonIgnore
	public Map<String, Object> getEdgeProperties() {
		return edgeProperties;
	}

	@JsonIgnore
	public void setEdgeProperties(Map<String, Object> edgeProperties) {
		this.edgeProperties = edgeProperties;
	}

	@JsonIgnore
	public Map<String, Object> getNetworkProperties() {
		return networkProperties;
	}

	@JsonIgnore
	public void setNetworkProperties(Map<String, Object> networkProperties) {
		this.networkProperties = networkProperties;
	}

	@JsonIgnore
	public Map<String, Object> getNodeProperties() {
		return nodeProperties;
	}

	@JsonIgnore
	public void setNodeProperties(Map<String, Object> nodeProperties) {
		this.nodeProperties = nodeProperties;
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return networkProperties.isEmpty() && nodeProperties.isEmpty() && edgeProperties.isEmpty();
	}

}
