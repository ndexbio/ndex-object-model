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
import com.fasterxml.jackson.databind.ObjectMapper;

public class CxNodeTest {

	@Test
	public void test() throws JsonProcessingException, NdexException {

		CxNode n = new CxNode();
		
		n.setId(12);
		n.setX(11.0);
        n.setY(22.0);
        n.setZ(0.0);
        
        assertEquals(n.getId(), 12);
        assertEquals(n.getX(), Double.valueOf(11.0));
        assertEquals(n.getY(), Double.valueOf(22.0));
        assertEquals(n.getZ(), Double.valueOf(0.0));
        
        
        ObjectMapper om = new ObjectMapper();
        
        String s = om.writeValueAsString(n);
        
        System.out.println(s);
        
        String s1 = "{\"id\":12,\"x\":11.0,\"y\":22,\"z\":0, \"v\":{\"n\":[23,32]}}";
        CxNode n2 = om.readerFor(CxNode.class).readValue(s1);
        
        List<Integer> nv = new ArrayList<> ();
        
        nv.add(23);
        nv.add(32);
        assertEquals(n2.getAttributes().get("n"), nv);
        
        
        System.out.println(n2.getAttributes());
        
        String s3 = om.writeValueAsString(n2);
        
        assertEquals (  "{\"v\":{\"n\":[23,32]},\"id\":12,\"x\":11.0,\"y\":22.0,\"z\":0.0}",
        		s3);
        
        
		DeclarationEntry de = new DeclarationEntry();
		de.setDataType(ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE);
		
		Map<String, DeclarationEntry> m = new HashMap<>();
		m.put("n", de);
			
        n2.validateAttribute(m);
        
        @SuppressWarnings("unchecked")
		List<Double> l = (List<Double>) n2.getAttributes().get("n");
        assertEquals ( Boolean.TRUE, (l.get(0) instanceof Double));
        
        

	}

}
