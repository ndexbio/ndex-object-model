package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.DeclarationEntry;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeclarationEntryTest {

	@Test
	public void testDeclarationEntry() throws NdexException {
		DeclarationEntry e = new DeclarationEntry();
		e.setAlias("a1");
		e.setDataType(ATTRIBUTE_DATA_TYPE.LONG);
		e.setDefaultValue("foo");
		
		assertEquals(e.getAlias(), "a1");
		assertEquals(e.getDataType(), ATTRIBUTE_DATA_TYPE.LONG);
		assertEquals(e.getDefaultValue(),"foo");
		
	}

	@Test
	public void testGetDataType() throws NdexException, IOException {
		ObjectMapper om = new ObjectMapper();
		DeclarationEntry e = new DeclarationEntry();
		e.setAlias("a1");
		e.setDataType(ATTRIBUTE_DATA_TYPE.INTEGER);
		e.setDefaultValue("foo");

		String jsonStr = om.writeValueAsString(e);
		
		DeclarationEntry e2 = om.readValue(jsonStr, DeclarationEntry.class);
		
		assertEquals (e.getAlias(), e2.getAlias());
		assertEquals (e.getDataType(), e2.getDataType());
		assertEquals (e.getDefaultValue(), e2.getDefaultValue());
		
		
	}

	@Test
	public void testDeserialization() throws NdexException, IOException {

		ObjectMapper om = new ObjectMapper();

		String s1 = "{\"d\": \"list_of_double\",\"v\": [2,3.3]}";
		
		DeclarationEntry e = om.readerFor(DeclarationEntry.class).readValue(s1);

		System.out.println ( om.writeValueAsString(e));
		
		e.processValue();
		
		String s2 = om.writeValueAsString(e);
		
		System.out.println( s2);
		
		
		
	}
	
	@Test (expected = NdexException.class) 
	public void testDeserialization1() throws NdexException, IOException {

		ObjectMapper om = new ObjectMapper();

		String s1 = "{\"v\": [2,3.3]}";
		
		DeclarationEntry e = om.readerFor(DeclarationEntry.class).readValue(s1);

		e.processValue();
	}

}
