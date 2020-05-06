package org.ndexbio.cx2.aspect.element.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonSerialize(using = AttributeDeclarationSerializer.class)
//@JsonDeserialize(using = AttributeDeclarationDeserializer.class)
public class CxAttributeDeclaration implements CxAspectElement{
	
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
	
	@JsonAnySetter
	public void add(String key, Map<String,DeclarationEntry> e) {
		this.declarations.put(key, e);
	}


	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}

	
}
