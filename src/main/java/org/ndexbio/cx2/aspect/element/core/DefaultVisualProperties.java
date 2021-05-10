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
	private VisualPropertyTable edgeProperties;
	
	
	@JsonProperty( "network")
	private Map<String,Object> networkProperties;
	
	@JsonProperty("node")
	private VisualPropertyTable nodeProperties;
	
	public DefaultVisualProperties () {
		this.edgeProperties = new VisualPropertyTable();
		this.nodeProperties = new VisualPropertyTable();
		this.networkProperties = new HashMap<>();	
	}

	@JsonIgnore
	public VisualPropertyTable getEdgeProperties() {
		return edgeProperties;
	}

	@JsonIgnore
	public void setEdgeProperties(VisualPropertyTable edgeProperties) {
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
	public VisualPropertyTable getNodeProperties() {
		return nodeProperties;
	}

	@JsonIgnore
	public void setNodeProperties(VisualPropertyTable nodeProperties) {
		this.nodeProperties = nodeProperties;
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return networkProperties.isEmpty() && 
				nodeProperties.getVisualProperties().isEmpty() && 
				edgeProperties.getVisualProperties().isEmpty();
	}

}
