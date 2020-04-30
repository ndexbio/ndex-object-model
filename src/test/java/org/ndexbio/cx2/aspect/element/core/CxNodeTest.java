package org.ndexbio.cx2.aspect.element.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxNode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CxNodeTest {

	@Test
	public void test() throws JsonProcessingException {

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
        
        assertEquals ( s3, "{\"id\":12,\"v\":{\"n\":[23,32]},\"x\":11.0,\"y\":22.0,\"z\":0.0}");
        

	}

}
