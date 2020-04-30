package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.assertEquals;

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
	    
	    assertEquals ( ATTRIBUTE_DATA_TYPE.LONG, rd2.getDeclarations().get("nodes").get("name").getDataType());
	    System.out.println (rd2.getDeclarations());

	}	
}
