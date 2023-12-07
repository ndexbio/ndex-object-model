package org.ndexbio.cx2.io;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.ndexbio.cx2.aspect.element.core.CxAspectElement;
import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxNetworkAttribute;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cx2.aspect.element.core.CxVisualProperty;
import org.ndexbio.cx2.aspect.element.core.DefaultVisualProperties;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;

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
		
		try (FileInputStream input = new FileInputStream(absolutePath + "/cx2_tiny.cx2")) {
			CXReader reader = new CXReader (input);
			
			for (CxAspectElement elmt : reader) {
				switch (elmt.getAspectName()) {
				case CxAttributeDeclaration.ASPECT_NAME:
					CxAttributeDeclaration dec = (CxAttributeDeclaration)elmt;
					System.out.println(om.writeValueAsString( dec));
					assertEquals(2, dec.getDeclarations().size());
					assertEquals(3, dec.getAttributesInAspect("edges").size());
					break;
				case CxNode.ASPECT_NAME: // Node
					break;
				case CxEdge.ASPECT_NAME: // Edge 
					break;
				case CxNetworkAttribute.ASPECT_NAME:
					break;
				case CxVisualProperty.ASPECT_NAME:
					CxVisualProperty vp = (CxVisualProperty) elmt;
					DefaultVisualProperties dvp = vp.getDefaultProps();
					assertEquals ( 1, dvp.getNetworkProperties().size());
					assertEquals (7, dvp.getNodeProperties().getVisualProperties().size());
					assertEquals ( 2, dvp.getEdgeProperties().getVisualProperties().size());
					assertEquals ( 2, vp.getEdgeMappings().size());
					assertEquals ( 1, vp.getNodeMappings().size());
					
					assertEquals ("#323232", dvp.getEdgeProperties().get("EDGE_LINE_COLOR"));
					assertEquals (35.0, dvp.getNodeProperties().get("NODE_HEIGHT"));
					//System.out.println(om.writeValueAsString(vp));
					break;
					
				default:
					System.out.println(elmt.getAspectName());
					assertEquals("visualEditorProperties", elmt.getAspectName());
					//System.out.println (elmt);
					break;
				}
			}
			
		}
		
	} 
	
	@Test
	public void test2() throws JsonParseException, IOException {
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		
		ObjectMapper om = new ObjectMapper();
		
		try (FileInputStream input = new FileInputStream(absolutePath + "/cx2_tiny_double_and_longattribute.cx")) {
			CXReader reader = new CXReader (input);
			
			for (CxAspectElement elmt : reader) {
				switch (elmt.getAspectName()) {
				case CxAttributeDeclaration.ASPECT_NAME:
					CxAttributeDeclaration dec = (CxAttributeDeclaration)elmt;
					System.out.println(om.writeValueAsString(dec));
					break;
				case CxNode.ASPECT_NAME: // Node
					CxNode node = (CxNode) elmt;
					System.out.println(om.writeValueAsString(node));
					Object o = node.getAttributes().get("longV");
					assertTrue ( o instanceof Long);
					break;
				case CxEdge.ASPECT_NAME: // Edge 
					CxEdge edge = (CxEdge) elmt;
					System.out.println(om.writeValueAsString(edge));
					
					Object v = edge.getAttributes().get("dV");
					assertTrue ( v instanceof Double);
					break;
				case NodeAttributesElement.ASPECT_NAME: // node attributes
					break;
				case NetworkAttributesElement.ASPECT_NAME: // network attributes
					break;
				case EdgeAttributesElement.ASPECT_NAME:
					break;
				default:
				//	System.out.println(elmt.getAspectName());
				//	System.out.println (elmt);
					break;
				}
			}
			
		}
		
	} 
}
