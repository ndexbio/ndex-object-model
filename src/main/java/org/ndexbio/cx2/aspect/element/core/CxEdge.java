package org.ndexbio.cx2.aspect.element.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "s","t" })
public class CxEdge extends AttributeDeclaredAspect<CxEdge> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	
	public String addCX1EdgeAttribute(EdgeAttributesElement elmt, CxAttributeDeclaration decls) throws NdexException {
		return addCX1Attribute(elmt, decls, CxEdge.ASPECT_NAME);
	}
	
	@JsonIgnore
	public String getInteraction(Map<String, DeclarationEntry> attrDecls) {
		return getStringAttr(attrDecls, INTERACTION);

	}


	@Override
	public int compareTo(CxEdge o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<EdgeAttributesElement> getAttributesAsCX1List(Map<String, DeclarationEntry> attrDecls) {
		List<EdgeAttributesElement> result = new ArrayList<>(this.attributes.size());
		replaceShortenedName(attrDecls);
		for ( Map.Entry<String, Object> entry : this.attributes.entrySet()) {
			String attrName = entry.getKey();
			if ( ! attrName.equals(INTERACTION)) {
				DeclarationEntry d = attrDecls.get(attrName);
				ATTRIBUTE_DATA_TYPE type = d.getDataType();
				if ( type.isSingleValueType()) {
					String vs = entry.getValue().toString();
					result.add(new EdgeAttributesElement(null, id, attrName, entry.getValue().toString(), type ));
				} else {
					List<Object> vl = (List<Object>)entry.getValue();
					result.add(
							new EdgeAttributesElement(null,
							id, attrName, 
							vl.stream().map(n -> { return n.toString();}).collect(Collectors.toList()),type));
				}
			}
		}
		
		return result;
	}
	
	public EdgesElement getCX1Edge(Map<String, DeclarationEntry> attrDecls) {
		return new EdgesElement ( id, source, target, getInteraction ( attrDecls));
	}
}
