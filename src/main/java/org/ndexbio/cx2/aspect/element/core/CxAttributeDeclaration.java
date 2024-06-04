package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonSerialize(using = AttributeDeclarationSerializer.class)
//@JsonDeserialize(using = AttributeDeclarationDeserializer.class)
public class CxAttributeDeclaration implements CxAspectElement<CxAttributeDeclaration>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String ASPECT_NAME = "attributeDeclarations";
	
	/*
	 * Key1: aspect name
	 * Key2: attribute name in that aspect.
	 */
	private Map<String, Map<String,DeclarationEntry>> declarations;
	
	public CxAttributeDeclaration() {declarations = new HashMap<>();}

	@JsonAnyGetter
	public Map<String,Map<String, DeclarationEntry>> getDeclarations() {
		return declarations;
	}
	
	
	/**
	 * 
	 * @param aspectName
	 * @param attributes
	 * @throws NdexException 
	 */
	@JsonAnySetter
	public void add(String aspectName, Map<String,DeclarationEntry> attributes) throws NdexException {
		// check if default value was declared in network attribute declaration
		if ( aspectName.equals(CxNetworkAttribute.ASPECT_NAME)) {
			for ( Map.Entry<String,DeclarationEntry> e : attributes.entrySet()) {
				DeclarationEntry d = e.getValue();
				if (d.getDefaultValue() !=null) 
					throw new NdexException ("Declaring default value '"+ d.getDefaultValue()+ "' on network attribute '" + e.getKey() 
					   + "' is not allowed according to CX2 specification." );
				if ( d.getAlias()!=null) {
					throw new NdexException ("Declaring an alias for network attribute '" + e.getKey() 
					   + "' is not allowed according to CX2 specification." );
				}
			}
		}
		this.declarations.put(aspectName, attributes);
	}

	
	@JsonIgnore
	public void addAttributeDeclaration(String aspectName, String attributeName, DeclarationEntry declaration) {
		Map<String, DeclarationEntry> aspectAttributes = declarations.get(aspectName);
		if ( aspectAttributes == null) {
			aspectAttributes = new TreeMap<>();
			declarations.put(aspectName, aspectAttributes);
		}
		aspectAttributes.put(attributeName, declaration);
	}
	

	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	@JsonIgnore
	public Map<String,DeclarationEntry> getAttributesInAspect(String aspectName) {
		return declarations.get(aspectName);
	}
	
	
	public void addNewDeclarations(CxAttributeDeclaration newDeclarations) throws NdexException {
		for ( Map.Entry<String, Map<String, DeclarationEntry>> entry : newDeclarations.getDeclarations().entrySet()) {
			Map<String,DeclarationEntry> decls = getAttributesInAspect(entry.getKey());
			
			if ( decls == null) {
				add ( entry.getKey(), entry.getValue());
			} else {
				for ( Map.Entry<String, DeclarationEntry> newDeclaration : entry.getValue().entrySet()) {
					if ( entry.getKey().equals(CxNetworkAttribute.ASPECT_NAME)) {
						if ( newDeclaration.getValue().getDefaultValue()!=null)
							throw new NdexException ("Declaring default value '"+ newDeclaration.getValue().getDefaultValue()+ "' on network attribute '" 
					        + newDeclaration.getKey() + "' is not allowed according to CX2 specification." );
						if ( newDeclaration.getValue().getAlias()!=null) {
							throw new NdexException ("Declaring an alias for network attribute '" + newDeclaration.getKey() 
							   + "' is not allowed according to CX2 specification." );
						}
					}
					DeclarationEntry oldValue = decls.put ( newDeclaration.getKey(), newDeclaration.getValue());
					if (oldValue != null) 
						throw new NdexException ("Atribute " + newDeclaration.getKey() + " in aspect " +
								entry.getKey() + " is declared more than once.");
				}
			}
		}
		
	}
	
	public void removeAspectDeclaration(String aspectName) {
		this.declarations.remove(aspectName);
	}

	@Override
	public int compareTo(CxAttributeDeclaration o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
