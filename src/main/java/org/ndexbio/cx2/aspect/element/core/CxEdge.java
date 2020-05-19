package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "s","t" })
public class CxEdge extends AttributeDeclaredAspect implements CxAspectElement{
	
	public static final String ASPECT_NAME= "edges";
	
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("s")
	private long source;
	
	@JsonProperty("t")
	private long target;
		
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
	

	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

}
