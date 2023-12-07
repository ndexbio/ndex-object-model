package org.ndexbio.cx2.io;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.ndexbio.cx2.aspect.element.core.CxNode;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CX2AspectWriterTest {

	@Test
	public void test() throws IOException {
		String testFileName = "aspectFile";
		
		try (CX2AspectWriter writer = new CX2AspectWriter(testFileName)) {
			
			CxNode n = new CxNode();
			
			n.setId(12L);
			n.setX(11.0);
	        n.setY(22.0);
	        n.setZ(0.0);
	        
	        writer.writeCXElement(n);
	        
			CxNode n2 = new CxNode();
			
			n2.setId(13L);
			n2.setX(22.0);
	        n2.setY(33.0);
	        n2.setZ(1.0);
	        
	        writer.writeCXElement(n2);
	        
			writer.close();
			
			ObjectMapper om = new ObjectMapper();
			CxNode[] nodes = om.readValue(new File ( testFileName), CxNode[].class);
			
			assertEquals(Long.valueOf(12L), nodes[0].getId());
			assertEquals(Long.valueOf(13L), nodes[1].getId());
			
		}
		
	
		File f = new File(testFileName);
		f.delete();
		
	}

}
