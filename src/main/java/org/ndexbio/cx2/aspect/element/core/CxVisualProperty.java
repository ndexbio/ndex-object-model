package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CxVisualProperty implements CxAspectElement {

	public static final String ASPECT_NAME = "visualProperties";

	@JsonProperty("default")
	private DefaultVisualProperties defaultProps;
	
	@JsonProperty("edgeMapping")
	private Map<String, VisualPropertyMapping>  edgeMappings;
	
	@JsonProperty("nodeMapping")
	private Map<String, VisualPropertyMapping>  nodeMappings;  

	
	public CxVisualProperty() {
		edgeMappings = new HashMap<>();
		nodeMappings = new HashMap<>();
		defaultProps = new DefaultVisualProperties();
	}
	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	@JsonIgnore
	public DefaultVisualProperties getDefaultProps() {
		return defaultProps;
	}

	@JsonIgnore
	public void setDefaultProps(DefaultVisualProperties defaultProps) {
		this.defaultProps = defaultProps;
	}

	@JsonIgnore
	public Map<String, VisualPropertyMapping> getEdgeMappings() {
		return edgeMappings;
	}

	@JsonIgnore
	public void setEdgeMappings(Map<String, VisualPropertyMapping> edgeMappings) {
		this.edgeMappings = edgeMappings;
	}

	@JsonIgnore
	public Map<String, VisualPropertyMapping> getNodeMappings() {
		return nodeMappings;
	}

	@JsonIgnore
	public void setNodeMappings(Map<String, VisualPropertyMapping> nodeMappings) {
		this.nodeMappings = nodeMappings;
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return defaultProps.isEmpty() && edgeMappings.isEmpty() && nodeMappings.isEmpty();
	}
}
