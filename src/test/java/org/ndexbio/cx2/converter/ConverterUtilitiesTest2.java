package org.ndexbio.cx2.converter;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ConverterUtilitiesTest2 {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException, NdexException {

		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.STRING, null),null);
		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.STRING, "foo"),"foo");
		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.BOOLEAN, "true"),Boolean.TRUE);
		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.DOUBLE, "33.445"),Double.valueOf("33.445"));
		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.LONG, "33"),Long.valueOf(33));
		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.INTEGER, "-445"),Integer.valueOf(-445));
		

		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.LIST_OF_STRING, "[\"foo\",\"bar\"]"),
				Arrays.asList("foo","bar"));
	
		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.LIST_OF_BOOLEAN, "[\"true\",\"false\"]"),
				Arrays.asList(true,false));
	

		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE, "[\"23.4\",\"-55.23\"]"),
				Arrays.asList(23.4,-55.23));
	
		List<Long> r = (List<Long>)ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.LIST_OF_LONG, "[\"23\",\"-55\"]");
			
		assertEquals(r.size(),2);
		assertEquals(r.get(0), Long.valueOf(23));
		assertEquals(r.get(1), Long.valueOf(-55));
	
		assertEquals(ConverterUtilities.cvtPropPairStringValueToObj(ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER, "[\"4\",\"23\"]"),
				Arrays.asList(4,23));
	

		
	}

}
