package org.ndexbio.cxio.core.writers;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.NdexCXNetworkWriter;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.exceptions.NdexException;


/**
 * Tests {@link org.ndexbio.cxio.core.writers.NiceCXNetworkWriterFullTest}
 * @author churas
 */
public class NiceCXNetworkWriterFullTest  {
    
    @Test
    public void testNullWriterAndNetwork() throws NdexException {
        FullCXNiceCXNetworkWriter writer = new FullCXNiceCXNetworkWriter(null);
        // both null
        try {
            writer.writeNiceCXNetwork(null);
            fail("Expected NullPointerException");
        } catch(NullPointerException npe){
            assertEquals("network is null", npe.getMessage());
        }
        
        //ndex writer is null
        writer = new FullCXNiceCXNetworkWriter(null);
        try {
            NiceCXNetwork cxNetwork = new NiceCXNetwork();
            writer.writeNiceCXNetwork(cxNetwork);
            fail("Expected NullPointerException");
        } catch(NullPointerException npe){
            assertEquals("writer is null", npe.getMessage());
        }

    }
    @Test
    public void testNetworkOneNode() throws NdexException {
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            NdexCXNetworkWriter ndexwriter = new NdexCXNetworkWriter(out, true);

            NiceCXNetwork cxNetwork = new NiceCXNetwork();
            NodesElement ne = new NodesElement();
            ne.setId(1);
            ne.setNodeName("hello");
            ne.setNodeRepresents("whoa");
            cxNetwork.addNode(ne);
            FullCXNiceCXNetworkWriter writer = new FullCXNiceCXNetworkWriter(ndexwriter);
            writer.writeNiceCXNetwork(cxNetwork);
            assertTrue(out.toString().contains("{\"nodes\":[{\"@id\":1,\"n\":\"hello\",\"r\":\"whoa\"}]},"));
        }catch(IOException io){
            fail("Unexpected IOexception " + io.getMessage());
        }
    }
    
    @Test
    public void testNetworkTwoNodesOneEdge() throws NdexException {
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            NdexCXNetworkWriter ndexwriter = new NdexCXNetworkWriter(out, true);

            NiceCXNetwork cxNetwork = new NiceCXNetwork();
            NodesElement ne = new NodesElement();
            ne.setId(1);
            ne.setNodeName("hello");
            ne.setNodeRepresents("whoa");
            cxNetwork.addNode(ne);
            
            ne = new NodesElement();
            ne.setId(2);
            ne.setNodeName("bye");
            ne.setNodeRepresents("geez");
            cxNetwork.addNode(ne);
            
            EdgesElement edge = new EdgesElement();
            edge.setId(1L);
            edge.setSource(1L);
            edge.setTarget(2L);
            edge.setInteraction("some");
            cxNetwork.addEdge(edge);
         
            FullCXNiceCXNetworkWriter writer = new FullCXNiceCXNetworkWriter(ndexwriter);
            writer.writeNiceCXNetwork(cxNetwork);
            String res = out.toString();
            assertTrue(res.contains("[{\"numberVerification\":[{\"longNumber\":281474976710655}]},{\"metaData\":[]},"));
            assertTrue(res.contains("{\"nodes\":[{\"@id\":1,\"n\":\"hello\",\"r\":\"whoa\"},"));
            assertTrue(res.contains("{\"@id\":2,\"n\":\"bye\",\"r\":\"geez\"}]},"));
            assertTrue(res.contains("{\"edges\":[{\"@id\":1,\"s\":1,\"t\":2,\"i\":\"some\"}]},"));
        }catch(IOException io){
            fail("Unexpected IOexception " + io.getMessage());
        }
    }

    @Test
    public void testNetworkTwoNodesOneEdgeWithNodeAndEdgeAttributes() throws NdexException {
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            NdexCXNetworkWriter ndexwriter = new NdexCXNetworkWriter(out, true);

            NiceCXNetwork cxNetwork = new NiceCXNetwork();
            NodesElement ne = new NodesElement();
            ne.setId(1);
            ne.setNodeName("hello");
            ne.setNodeRepresents("whoa");
            cxNetwork.addNode(ne);
            ne = new NodesElement();
            ne.setId(2);
            ne.setNodeName("bye");
            ne.setNodeRepresents("geez");
            cxNetwork.addNode(ne);
            
            EdgesElement edge = new EdgesElement();
            edge.setId(1L);
            edge.setSource(1L);
            edge.setTarget(2L);
            edge.setInteraction("some");
            cxNetwork.addEdge(edge);
         
            EdgeAttributesElement edgeAttr = new EdgeAttributesElement(1L, "someedge", "1234", ATTRIBUTE_DATA_TYPE.INTEGER);
            cxNetwork.addEdgeAttribute(edgeAttr);
            
            NodeAttributesElement nae = new NodeAttributesElement(2L, "querynode", "true", ATTRIBUTE_DATA_TYPE.BOOLEAN);
            cxNetwork.addNodeAttribute(nae);
            FullCXNiceCXNetworkWriter writer = new FullCXNiceCXNetworkWriter(ndexwriter);
            writer.writeNiceCXNetwork(cxNetwork);
            String res = out.toString();
            assertTrue(res.contains("[{\"numberVerification\":[{\"longNumber\":281474976710655}]},{\"metaData\":[]},"));
            assertTrue(res.contains("{\"nodes\":[{\"@id\":1,\"n\":\"hello\",\"r\":\"whoa\"},"));
            assertTrue(res.contains("{\"@id\":2,\"n\":\"bye\",\"r\":\"geez\"}]},"));
            assertTrue(res.contains("{\"edges\":[{\"@id\":1,\"s\":1,\"t\":2,\"i\":\"some\"}]},"));
            assertTrue(res.contains("{\"nodeAttributes\":[{\"n\":\"querynode\",\"d\":\"boolean\",\"v\":\"true\",\"po\":2}]},"));
            assertTrue(res.contains("{\"edgeAttributes\":[{\"n\":\"someedge\",\"d\":\"integer\",\"v\":\"1234\",\"po\":1}]},"));
        }catch(IOException io){
            fail("Unexpected IOexception " + io.getMessage());
        }
    }
    
    @Test
    public void testNetworkTwoNodesOneEdgeWithOpaqueAspect() throws NdexException {
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            NdexCXNetworkWriter ndexwriter = new NdexCXNetworkWriter(out, true);

            NiceCXNetwork cxNetwork = new NiceCXNetwork();
            NodesElement ne = new NodesElement();
            ne.setId(1);
            ne.setNodeName("hello");
            ne.setNodeRepresents("whoa");
            cxNetwork.addNode(ne);
            
            ne = new NodesElement();
            ne.setId(2);
            ne.setNodeName("bye");
            ne.setNodeRepresents("geez");
            cxNetwork.addNode(ne);
            
            EdgesElement edge = new EdgesElement();
            edge.setId(1L);
            edge.setSource(1L);
            edge.setTarget(2L);
            edge.setInteraction("some");
            cxNetwork.addEdge(edge);
         
            CyVisualPropertiesElement cpe = new CyVisualPropertiesElement();
            cpe.putProperty("hi", "there");
            cxNetwork.addOpapqueAspect(cpe);
            FullCXNiceCXNetworkWriter writer = new FullCXNiceCXNetworkWriter(ndexwriter);
            writer.writeNiceCXNetwork(cxNetwork);
            String res = out.toString();
            System.out.println("XXXXXX" + res);
            assertTrue(res.contains("[{\"numberVerification\":[{\"longNumber\":281474976710655}]},{\"metaData\":[]},"));
            assertTrue(res.contains("{\"nodes\":[{\"@id\":1,\"n\":\"hello\",\"r\":\"whoa\"},"));
            assertTrue(res.contains("{\"@id\":2,\"n\":\"bye\",\"r\":\"geez\"}]},"));
            assertTrue(res.contains("{\"edges\":[{\"@id\":1,\"s\":1,\"t\":2,\"i\":\"some\"}]},"));
            assertTrue(res.contains("{\"cyVisualProperties\":[{\"properties\":{\"hi\":\"there\"}}]},"));
        }catch(IOException io){
            fail("Unexpected IOexception " + io.getMessage());
        }
    }
}
