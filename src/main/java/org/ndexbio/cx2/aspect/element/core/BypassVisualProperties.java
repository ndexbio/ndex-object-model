package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Abstract class for node and edge bypass visual properties.
 * 
 * @author jingchen
 *
 */
public abstract class BypassVisualProperties<T extends BypassVisualProperties<?> > implements CxAspectElement<T> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty ("id")
	private Long id;
    
    @JsonProperty ("v")
	private Map<String, Object> visualProperties;
	
    public BypassVisualProperties() { visualProperties = new HashMap<> (); } 
    
    @JsonIgnore
    public void setId(Long id) { this.id = id; }
    
    @JsonIgnore
    public void setVisualProperties ( Map<String,Object> properties) {
    	this.visualProperties = properties;
    }
    
    @JsonIgnore
    public Long getId() { return id;}
    
    @JsonIgnore
    public Map<String,Object> getVisualProperties () { return this.visualProperties;}
}
