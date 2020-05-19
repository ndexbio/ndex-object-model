package org.ndexbio.cx2.aspect.element.core;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "x","y","z" })
public class CxNode extends AttributeDeclaredAspect implements CxAspectElement {
	
	public final static String ASPECT_NAME = "nodes";
	
	@JsonProperty("id")
	private long id;
	
/*	@JsonProperty("v")
	private Map<String, Object> attributes;
*/	
	@JsonProperty("x")
	private Double x;
	
	@JsonProperty("y")
	private Double y;
	
	@JsonProperty("z")
	private Double z;
	
	public CxNode() {
		
	}
	
	public CxNode (long id, LinkedHashMap<String, Object> attributes ) {
		this.setId(id);
		this.setAttributes(attributes);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

/*	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
*/
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}


	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}


}
