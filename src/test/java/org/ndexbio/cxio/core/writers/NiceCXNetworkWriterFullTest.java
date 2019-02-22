package org.ndexbio.cxio.core.writers;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
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

}
