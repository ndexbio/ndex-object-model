package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "s","t" })
public class CxEdge extends AttributeDeclaredAspect {
	
	public static final String ASPECT_NAME= "edges";
	
	public static final String INTERACTION ="interaction";
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("s")
	private Long source;
	
	@JsonProperty("t")
	private Long target;
		
	public CxEdge() {}
	
	
	public CxEdge(Long id, Long source, Long target) {
		
		this.id = id;
		this.source = source;
		this.target = target;			
	}
	
	public CxEdge(Long id) {	
		this.id = id;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public Long getTarget() {
		return target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}
	

	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	
	public void addCX1EdgeAttribute(EdgeAttributesElement elmt, CxAttributeDeclaration decls) throws NdexException {
		addCX1Attribute(elmt, decls, CxEdge.ASPECT_NAME);
	}
	
	@JsonIgnore
	public String getInteraction(Map<String, DeclarationEntry> attrDecls) {
		return getStringAttr(attrDecls, INTERACTION);

	}
}
