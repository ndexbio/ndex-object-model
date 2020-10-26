package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CxNodeTest {

	@Test
	public void test() throws JsonProcessingException, NdexException {

		CxNode n = new CxNode();
		
		n.setId(12L);
		n.setX(11.0);
        n.setY(22.0);
        n.setZ(0.0);
        
        assertEquals(Long.valueOf(12L), n.getId());
        assertEquals(n.getX(), Double.valueOf(11.0));
        assertEquals(n.getY(), Double.valueOf(22.0));
        assertEquals(n.getZ(), Double.valueOf(0.0));
        
        
        ObjectMapper om = new ObjectMapper();
        
        String s = om.writeValueAsString(n);
        
        System.out.println(s);
        
        String s1 = "{\"id\":12,\"x\":11.0,\"y\":22,\"z\":0, \"v\":{\"n\":[23,32], \"ab\": 33, \"about\": \"something\"}}";
        CxNode n2 = om.readerFor(CxNode.class).readValue(s1);
        
        List<Integer> nv = new ArrayList<> ();
        
        nv.add(23);
        nv.add(32);
        assertEquals(n2.getAttributes().get("n"), nv);
        
        
        System.out.println(n2.getAttributes());
        
        String s3 = om.writeValueAsString(n2);
        
        assertEquals ( // "{\"v\":{\"n\":[23,32]},\"id\":12,\"x\":11.0,\"y\":22.0,\"z\":0.0}",
        		"{\"id\":12,\"x\":11.0,\"y\":22.0,\"z\":0.0,\"v\":{\"n\":[23,32],\"ab\":33,\"about\":\"something\"}}",
        		s3);
        
        
        
        // test attribute validating function.
		DeclarationEntry de = new DeclarationEntry();
		de.setDataType(ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE);
		
		Map<String, DeclarationEntry> m = new HashMap<>();
		m.put("n", de);
		
		de = new DeclarationEntry();
		de.setDataType(ATTRIBUTE_DATA_TYPE.INTEGER);
		m.put("ab", de);
		
		de = new DeclarationEntry();
		de.setDataType(ATTRIBUTE_DATA_TYPE.STRING);
		m.put("about", de);
		
        n2.validateAttribute(m,true);
        
        @SuppressWarnings("unchecked")
		List<Double> l = (List<Double>) n2.getAttributes().get("n");
        assertEquals ( Boolean.TRUE, (l.get(0) instanceof Double));
        
        s1 = "{\"id\":12,\"x\":11.0,\"y\":22}";
        n2 = om.readValue(s1, CxNode.class);
        s3 = om.writeValueAsString(n2);
        assertEquals("{\"id\":12,\"x\":11.0,\"y\":22.0}", s3);
        
	}
	
	@Test 
	public void test2() throws JsonMappingException, JsonProcessingException {
		String s1 = "{\"x\": 22, \"y\": 33.3}";
		ObjectMapper om = new ObjectMapper();
		CxNode n2 = om.readerFor(CxNode.class).readValue(s1);
		
		assertNull (n2.getId());
		
		try {
			n2.validate();
			fail ("missing node id was not caught.");
		} catch (NdexException e) {
			assertEquals ( "Node id is missing.", e.getMessage());
		}
	}

	@Test (expected = NdexException.class)
	public void test3() throws JsonMappingException, JsonProcessingException, NdexException {
		String s1 = "{\"x\": 22, \"id\":23 }";
		ObjectMapper om = new ObjectMapper();
		CxNode n2 = om.readerFor(CxNode.class).readValue(s1);
				
		n2.validate();
	}}
