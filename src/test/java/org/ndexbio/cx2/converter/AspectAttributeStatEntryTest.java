package org.ndexbio.cx2.converter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

public class AspectAttributeStatEntryTest {

	@Test
	public void test() throws NdexException {
		AspectAttributeStatEntry e = new AspectAttributeStatEntry();
		String e1 = e.addDatatype(ATTRIBUTE_DATA_TYPE.DOUBLE);
		assertNull (e1);
		assertNull (e.addDatatype(ATTRIBUTE_DATA_TYPE.DOUBLE));
		
		e.setAlias("foo");
		assertEquals(ATTRIBUTE_DATA_TYPE.DOUBLE,e.getDataType());
		assertEquals("foo", e.getAlias());
	}
	
	@Test 
	public void test1() {
		AspectAttributeStatEntry e = new AspectAttributeStatEntry();
		e.addDatatype(ATTRIBUTE_DATA_TYPE.DOUBLE);
		String error = e.addDatatype(ATTRIBUTE_DATA_TYPE.STRING);
		assertEquals ( "data type string is inconsistent with previously declared data type double",
				error);
		
	}
	
	
	@Test
	public void test2() {
		AspectAttributeStatEntry e = new AspectAttributeStatEntry();
		assertNull(e.addDatatype(ATTRIBUTE_DATA_TYPE.STRING));
		
		
		for (int i = 0 ; i < 40 ; i++ ) {
			e.addValue("foo");
		}

		for ( int i = 0 ; i < 25 ; i++ ) {
			e.addValue("bar");
			
		}
		
		assertNull( e.getDefaultValue(65));
		
		for ( int i = 0 ; i < 30 ; i ++  ) {
			e.addValue("foo");
		}
		
		assertEquals("foo", e.getDefaultValue(30+25+40));
		assertNull( e.getDefaultValue(30+25+40+1),"foo");
		
		
		for ( int i = 0 ; i < 100 ; i ++  ) {
			e.addValue("bar");
		}
		
		assertEquals("bar", e.getDefaultValue(100+30+25+40));
		
		assertNull( e.getDefaultValue(100+30+25+40+1),"bar");
		
	}

}
