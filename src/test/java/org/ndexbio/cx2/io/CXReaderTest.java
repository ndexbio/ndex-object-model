package org.ndexbio.cx2.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxAspectElement;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cxio.aspects.datamodels.CartesianLayoutElement;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CXReaderTest {

/*	@Test
    //empty network
	public void test() throws JsonParseException, IOException {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		int counter = 0;
		try (FileInputStream input = new FileInputStream(absolutePath + "/cx2_empty.cx")) {
			CXReader reader = new CXReader (input);
			for ( AspectElement elmt : reader ) {
				counter ++;
			}
		}

		assertEquals ( 0, counter);
//		reader.getAspectFragmentIterator();
	}
*/	
	
	
	@Test
	public void test1() throws JsonParseException, IOException {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		
		ObjectMapper om = new ObjectMapper();
		
		try (FileInputStream input = new FileInputStream(absolutePath + "/cx2_tiny.cx")) {
			CXReader reader = new CXReader (input);
			
			for (CxAspectElement elmt : reader) {
				switch (elmt.getAspectName()) {
				case CxAttributeDeclaration.ASPECT_NAME:
					CxAttributeDeclaration dec = (CxAttributeDeclaration)elmt;
					om.writeValue(System.out, dec);
					break;
				case CxNode.ASPECT_NAME: // Node
					break;
				case CxEdge.ASPECT_NAME: // Edge 
					break;
				case NodeAttributesElement.ASPECT_NAME: // node attributes
					break;
				case NetworkAttributesElement.ASPECT_NAME: // network attributes
					break;
				case EdgeAttributesElement.ASPECT_NAME:
					break;
				default:
					System.out.println(elmt.getAspectName());
					System.out.println (elmt);
					break;
				}
			}
			
		}
		
	} 
}
