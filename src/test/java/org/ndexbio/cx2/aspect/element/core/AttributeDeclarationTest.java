package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.DeclarationEntry;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AttributeDeclarationTest {
	
	@Test
	public void test() throws IOException, NdexException {
		
		CxAttributeDeclaration ad = new CxAttributeDeclaration();
		DeclarationEntry de = new DeclarationEntry();
		de.setAlias("t");
		de.setDataType(ATTRIBUTE_DATA_TYPE.LONG);
		de.setDefaultValue(Long.valueOf(22l));
		
		//Map<String, DeclarationEntry> rec = new HashMap<>();
		Map<String, DeclarationEntry> m = new HashMap<>();
		m.put("name", de);
		ad.add("nodes", m);
		
		//ad.setDeclarations(rec);
		
		ObjectMapper om = new ObjectMapper();
	    String jsonString = om.writeValueAsString(ad);
	     
	    assertEquals("{\"nodes\":{\"name\":{\"v\":22,\"a\":\"t\",\"d\":\"long\"}}}", jsonString )  ;
	      
	    CxAttributeDeclaration rd2 = om.readValue(jsonString, CxAttributeDeclaration.class);
	    
	    assertEquals ("t",rd2.getAttributesInAspect("nodes").get("name").getAlias());
	    assertEquals ( ATTRIBUTE_DATA_TYPE.LONG, rd2.getDeclarations().get("nodes").get("name").getDataType());
	    System.out.println (rd2.getDeclarations());
	    
	}	
	
	@Test
	public void test2() throws NdexException {
		
		CxAttributeDeclaration ad = new CxAttributeDeclaration();
		DeclarationEntry de = new DeclarationEntry();
		de.setAlias("t");
		de.setDataType(ATTRIBUTE_DATA_TYPE.LONG);
		de.setDefaultValue(Long.valueOf(22l));
		
		//Map<String, DeclarationEntry> rec = new HashMap<>();
		Map<String, DeclarationEntry> m = new HashMap<>();
		m.put("name", de);
		ad.add("nodes", m);
		

		DeclarationEntry de2 = new DeclarationEntry();
		de2.setDataType(ATTRIBUTE_DATA_TYPE.STRING);
		de2.setDefaultValue("foo");
		Map<String, DeclarationEntry> m2 = new HashMap<>();
		m2.put("ptype", de2);
		CxAttributeDeclaration ad2 = new CxAttributeDeclaration();
		ad2.add("edges", m2);
		
		ad.addNewDeclarations(ad2);
		
		Map<String, DeclarationEntry> aspAttrs = ad.getAttributesInAspect("edges");
		
		
	    assertEquals(aspAttrs.get("ptype").getDataType(), ATTRIBUTE_DATA_TYPE.STRING )  ;
	      
	  
	    DeclarationEntry de3 = new DeclarationEntry();
		de3.setDataType(ATTRIBUTE_DATA_TYPE.STRING);
		de3.setDefaultValue("bar");
		Map<String, DeclarationEntry> m3 = new HashMap<>();
		m3.put("mt", de3);
		CxAttributeDeclaration ad3 = new CxAttributeDeclaration();
		ad3.add("nodes", m3);
		
		ad.addNewDeclarations(ad3);
		
		aspAttrs = ad.getAttributesInAspect("nodes");
		assertEquals ( aspAttrs.get("mt").getDefaultValue(), "bar");
		
		
		//TODO: test duplicated entry
	    
	}	
	
	@Test
	public void test3() throws NdexException {
		
		CxAttributeDeclaration ad = new CxAttributeDeclaration();
		DeclarationEntry de = new DeclarationEntry();
		de.setDataType(ATTRIBUTE_DATA_TYPE.LONG);
		de.setDefaultValue(Long.valueOf(22l));
		
		//Map<String, DeclarationEntry> rec = new HashMap<>();
		Map<String, DeclarationEntry> m = new HashMap<>();
		m.put("name", de);
		try {
			ad.add(CxNetworkAttribute.ASPECT_NAME, m);
			fail("Expected NdexException to be thrown.");
		} catch (NdexException e) {
			assertEquals("Declaring default value '22' on network attribute 'name' is not allowed according to CX2 specification.",e.getMessage());
		}	    
	}	

	
	@Test
	public void test4() throws NdexException {
		
		CxAttributeDeclaration ad = new CxAttributeDeclaration();
		DeclarationEntry de = new DeclarationEntry();
		de.setAlias("t");
		de.setDataType(ATTRIBUTE_DATA_TYPE.LONG);
		
		//Map<String, DeclarationEntry> rec = new HashMap<>();
		Map<String, DeclarationEntry> m = new HashMap<>();
		m.put("name", de);
		try {
			ad.add(CxNetworkAttribute.ASPECT_NAME, m);
			fail("Expected NdexException to be thrown.");
		} catch (NdexException e) {
			assertEquals("Declaring an alias on network attribute 'name' is not allowed according to CX2 specification.",e.getMessage());
		}	    
	}	

}
