package org.ndexbio.cxio.core.writers;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.NdexCXNetworkWriter;
import org.ndexbio.cxio.core.readers.NiceCXNetworkReader;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.exceptions.NdexException;


/**
 * Tests {@link org.ndexbio.cxio.core.writers.FullNiceCXNetworkWriterFull}
 * @author churas
 */
public class NiceCXNetworkWriterFullTest  {
    
	 @TempDir
	    Path tempDir;
    
    @Test
    public void testNullWriterAndNetwork() throws NdexException {
        NiceCXNetworkWriter writer = new NiceCXNetworkWriter(null);
        // both null
        try {
            writer.writeNiceCXNetwork(null);
            fail("Expected NullPointerException");
        } catch(NullPointerException npe){
            assertEquals("network is null", npe.getMessage());
        }
        
        //ndex writer is null
        writer = new NiceCXNetworkWriter(null);
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
            NiceCXNetworkWriter writer = new NiceCXNetworkWriter(ndexwriter);
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
         
            NiceCXNetworkWriter writer = new NiceCXNetworkWriter(ndexwriter);
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
            NiceCXNetworkWriter writer = new NiceCXNetworkWriter(ndexwriter);
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
            cxNetwork.addOpaqueAspect(cpe);
            NiceCXNetworkWriter writer = new NiceCXNetworkWriter(ndexwriter);
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
    
    @Test
    public void testLoadAndSaveOfWntSignalingNetwork() throws Exception {
        File origWntCX = new File(getClass().getClassLoader().getResource("nicecxnetworkwriterfulltest/wntsignaling.cx").toURI());
        assertTrue(origWntCX.isFile());
        NiceCXNetworkReader reader = new NiceCXNetworkReader();
        NiceCXNetwork origNetwork = null;
        try (FileInputStream fis = new FileInputStream(origWntCX)){
            origNetwork = reader.readNiceCXNetwork(fis);
        }
        
        
       // File tempFolder = _folder.newFolder();
        File tempCXOut =  tempDir.resolve("wntOut.cx").toFile(); // new File(tempFolder + File.separator + "wntOut.cx");
        
        try {
            try (FileOutputStream fos = new FileOutputStream(tempCXOut)){
                NiceCXNetworkWriter writer = new NiceCXNetworkWriter(fos, false);
                writer.writeNiceCXNetwork(origNetwork);
            }
            
            NiceCXNetwork readNetwork = null;
            try (FileInputStream fis = new FileInputStream(tempCXOut)){
                readNetwork = reader.readNiceCXNetwork(fis);
            }
            //basic checks
            assertTrue(origNetwork.getNetworkName().equals(readNetwork.getNetworkName()));
            assertEquals(origNetwork.getEdges().size(),readNetwork.getEdges().size());
            assertEquals(origNetwork.getNodes().size(),readNetwork.getNodes().size());
            assertEquals(origNetwork.getCitations().size(), readNetwork.getCitations().size());
            assertEquals(origNetwork.getEdgeAssociatedAspects().size(), readNetwork.getEdgeAssociatedAspects().size());
            assertEquals(origNetwork.getNodeAssociatedAspects().size(), readNetwork.getNodeAssociatedAspects().size());
            assertEquals(origNetwork.getNodeAttributes().size(), readNetwork.getNodeAttributes().size());
            assertEquals(origNetwork.getEdgeAttributes().size(), readNetwork.getEdgeAttributes().size());
            assertEquals(origNetwork.getNetworkAttributes().size(), readNetwork.getNetworkAttributes().size());
            assertEquals(origNetwork.getOpaqueAspectTable().size(), readNetwork.getOpaqueAspectTable().size());
            
            //MetaData checks
            
            //node comparison checks
            for(Long nodeId : origNetwork.getNodes().keySet()){
                NodesElement origNode = origNetwork.getNodes().get(nodeId);
                NodesElement readNode = readNetwork.getNodes().get(nodeId);
                assertTrue(origNode.getAspectName().equals(readNode.getAspectName()));
                assertTrue(origNode.getNodeName().equals(readNode.getNodeName()));
                assertTrue(origNode.getNodeRepresents().equals(readNode.getNodeRepresents()));
                
                
            }
            
            //edge comparison checks
            for(Long edgeId : origNetwork.getEdges().keySet()){
                EdgesElement origEdge = origNetwork.getEdges().get(edgeId);
                EdgesElement readEdge = readNetwork.getEdges().get(edgeId);
                assertTrue(origEdge.getInteraction().equals(readEdge.getInteraction()));
                assertTrue(origEdge.getAspectName().equals(readEdge.getAspectName()));
                assertTrue(origEdge.getSource().equals(readEdge.getSource()));
                assertTrue(origEdge.getTarget().equals(readEdge.getTarget()));
            }
            
            
        } catch(NdexException ne){
            fail("Unexpected exception: " + ne.getMessage());
        }
        finally {
            tempCXOut.delete();
        }
    }
}
