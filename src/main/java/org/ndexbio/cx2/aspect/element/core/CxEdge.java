package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_NULL)

public class CxEdge extends AttributeDeclaredAspect implements CxAspectElement{
	
	public static final String ASPECT_NAME= "edges";
	
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("s")
	private long source;
	
	@JsonProperty("t")
	private long target;
	
/*	@JsonProperty("v")
	private Map<String, Object> attributes; */
	
	public CxEdge() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSource() {
		return source;
	}

	public void setSource(long source) {
		this.source = source;
	}

	public long getTarget() {
		return target;
	}

	public void setTarget(long target) {
		this.target = target;
	}
	
/*	public Map<String, Object> getAttributes() {
		return this.attributes;
	}
	
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
*/

	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

}
