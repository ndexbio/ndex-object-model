package org.ndexbio.cx2.aspect.element.core;

import java.util.LinkedHashMap;

import org.ndexbio.model.exceptions.NdexException;

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
	private Long id;
		
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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


	/**
	 * Basic validaation of a node. Only check if it has an ID and the data integrity of coordinates.
	 * Attributes are not validated. 
	 * @throws NdexException
	 */
	public void validate() throws NdexException {
		if (id == null)
			throw new NdexException("Node id is missing.");
		if ( (x != null && y == null) || 
				x== null && y != null || 
				(z!=null && ( x ==null || y == null)))
			throw new NdexException ("One or more Coordinate values are missing in node " + id + ".");
			
	}

}
