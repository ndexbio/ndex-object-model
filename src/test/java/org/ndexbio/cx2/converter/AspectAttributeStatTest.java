package org.ndexbio.cx2.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxNetworkAttribute;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class AspectAttributeStatTest {

	@Test
	public void test() throws NdexException, JsonProcessingException {
		AspectAttributeStat stats = new AspectAttributeStat();
		
		//testing addEdge()
		EdgesElement edge = new EdgesElement(1L,2L,3L, null);		
		stats.addEdge(edge);
		CxAttributeDeclaration decls = stats.createCxDeclaration();
		
		assertNull (decls.getAttributesInAspect(CxEdge.ASPECT_NAME));
		
		edge.setInteraction("ppi");
		stats.addEdge(edge);
		decls = stats.createCxDeclaration();
		assertEquals("i",decls.getAttributesInAspect(CxEdge.ASPECT_NAME).get("interaction").getAlias());
		assertEquals(ATTRIBUTE_DATA_TYPE.STRING,
				decls.getAttributesInAspect(CxEdge.ASPECT_NAME).get("interaction").getDataType());
		assertNull(decls.getAttributesInAspect(CxEdge.ASPECT_NAME).get("interaction").getDefaultValue());
		
		//testing addNode()
		NodesElement n = new NodesElement(23L, null,null);
		
		stats.addNode(n);
		decls = stats.createCxDeclaration();
		
		assertNull(decls.getAttributesInAspect(CxNode.ASPECT_NAME));
		
		n.setNodeName("AKT1");
		stats.addNode(n);
		decls = stats.createCxDeclaration();

		assertEquals("n", decls.getAttributesInAspect(CxNode.ASPECT_NAME).get("name").getAlias());
		assertNull (decls.getAttributesInAspect(CxNode.ASPECT_NAME).get("name").getDefaultValue());
		
		n.setNodeRepresents("ncbigene:2222");
		for ( int i = 0 ; i < 200; i++ ) {
			stats.addNode(n);
		}
		decls = stats.createCxDeclaration();

		assertEquals("r", decls.getAttributesInAspect(CxNode.ASPECT_NAME).get("represents").getAlias());
		assertNull (decls.getAttributesInAspect(CxNode.ASPECT_NAME).get("represents").getDefaultValue());

		//testing addNodeAttribute
		NodeAttributesElement na = new NodeAttributesElement(null, 20L, "attr1", "33", ATTRIBUTE_DATA_TYPE.INTEGER);
		stats.addNodeAttribute(na);
		decls = stats.createCxDeclaration();
		
		assertEquals(ATTRIBUTE_DATA_TYPE.INTEGER, 
				decls.getAttributesInAspect(CxNode.ASPECT_NAME).get("attr1").getDataType());
		
		na = new  NodeAttributesElement(null, 20L, "n", "33", ATTRIBUTE_DATA_TYPE.INTEGER);
		stats.addNodeAttribute(na);
		decls = stats.createCxDeclaration();
		
		assertEquals(ATTRIBUTE_DATA_TYPE.INTEGER, 
				decls.getAttributesInAspect(CxNode.ASPECT_NAME).get("n").getDataType());
		
		assertNull (decls.getAttributesInAspect(CxNode.ASPECT_NAME).get("name").getAlias());
		
		//testing addEdgeAttribute
		EdgeAttributesElement ea = new EdgeAttributesElement(null, 20L, "attr1", "33", ATTRIBUTE_DATA_TYPE.LONG);
		stats.addEdgeAttribute(ea);
		decls = stats.createCxDeclaration();
		
		assertEquals(ATTRIBUTE_DATA_TYPE.LONG, 
				decls.getAttributesInAspect(CxEdge.ASPECT_NAME).get("attr1").getDataType());
		
		ea = new  EdgeAttributesElement(null, 20L, "i", "33", ATTRIBUTE_DATA_TYPE.INTEGER);
		stats.addEdgeAttribute(ea);
		decls = stats.createCxDeclaration();
		
		assertEquals(ATTRIBUTE_DATA_TYPE.INTEGER, 
				decls.getAttributesInAspect(CxEdge.ASPECT_NAME).get("i").getDataType());
		
		assertNull (decls.getAttributesInAspect(CxEdge.ASPECT_NAME).get("interaction").getAlias());
		
		
		//test networkAttribute
		NetworkAttributesElement a = new NetworkAttributesElement(null, "name","net2", ATTRIBUTE_DATA_TYPE.STRING);
		stats.addNetworkAttribute(a);
		a = new NetworkAttributesElement(null, "avgDegree", "22", ATTRIBUTE_DATA_TYPE.INTEGER);
		stats.addNetworkAttribute(a);
		decls = stats.createCxDeclaration();
		
		assertEquals(ATTRIBUTE_DATA_TYPE.STRING, 
				decls.getAttributesInAspect(CxNetworkAttribute.ASPECT_NAME).get("name").getDataType());
		assertEquals(ATTRIBUTE_DATA_TYPE.INTEGER, 
				decls.getAttributesInAspect(CxNetworkAttribute.ASPECT_NAME).get("avgDegree").getDataType());
		
						
	}

}
