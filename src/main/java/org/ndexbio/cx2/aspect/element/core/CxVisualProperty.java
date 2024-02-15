package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CxVisualProperty implements CxAspectElement<CxVisualProperty> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Override
	public int compareTo(CxVisualProperty o) {
		// TODO Auto-generated method stub
		 throw new NotImplementedException();
	}
	
	/**
	 * Cast raw types such as maps creaated from Json String into proper VP values 
	 * @throws NdexException 
	 */
	public void evaluate() throws NdexException {
		for (Map.Entry<String,VisualPropertyMapping> mapping: nodeMappings.entrySet()) {
			String vpName = mapping.getKey();
			VisualPropertyMapping m = mapping.getValue();
			if ( m.getType()==VPMappingType.DISCRETE) {
				for ( Map<String,Object> entry : m.getMappingDef().getMapppingList()) {
					cook ( vpName, MappingDefinition.vp, entry);
				}
				
			} else { // continuous maping
				for ( Map<String,Object> entry : m.getMappingDef().getMapppingList()) {
					cook(vpName, MappingDefinition.maxVPValue, entry);
					cook(vpName, MappingDefinition.minVPValue, entry);
				}				
			}
		}
		
	}
	
	private static void cook(String vpName, String key, Map<String,Object> entry) throws NdexException {
		Object v = entry.get(key);
		if ( v !=null ) {
			Object newVp = VisualPropertyTable.castVPValue(vpName, v);
			entry.put(key, newVp);
		}
	}
}
