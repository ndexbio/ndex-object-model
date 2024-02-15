/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cxio.core.readers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.readers.NiceCXNetworkReader;
import org.ndexbio.cxio.metadata.MetaDataCollection;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.exceptions.NdexException;

/**
 *
 * @author churas
 */
public class NiceCXNetworkReaderTest {
    

    @Test
    public void testLoadAndSaveOfWntSignalingNetwork() throws Exception {
        File origWntCX = new File(getClass().getClassLoader().getResource("nicecxnetworkwriterfulltest/wntsignaling.cx").toURI());
        assertTrue(origWntCX.isFile());
        NiceCXNetworkReader reader = new NiceCXNetworkReader();
        NiceCXNetwork origNetwork = null;
        try (FileInputStream fis = new FileInputStream(origWntCX)){
            origNetwork = reader.readNiceCXNetwork(fis);
        }
        assertEquals("WNT Signaling", origNetwork.getNetworkName());
        
        assertEquals(74, origNetwork.getEdges().size());
        assertEquals(32, origNetwork.getNodes().size());
        assertEquals(0, origNetwork.getCitations().size());
        assertEquals(0, origNetwork.getEdgeAssociatedAspects().size());
        assertEquals(1, origNetwork.getNodeAssociatedAspects().size());
        assertEquals(32 ,origNetwork.getNodeAttributes().size());
        assertEquals(74, origNetwork.getEdgeAttributes().size());
        assertEquals(11, origNetwork.getNetworkAttributes().size());
        assertEquals(1, origNetwork.getOpaqueAspectTable().size());
        assertTrue(origNetwork.getMetadata() != null);
        MetaDataCollection metaData = origNetwork.getMetadata();
        assertEquals(32, metaData.getElementCount(NodesElement.ASPECT_NAME).longValue());
        assertEquals(32, metaData.getIdCounter(NodesElement.ASPECT_NAME).longValue());
        assertEquals(74, metaData.getElementCount(EdgesElement.ASPECT_NAME).longValue());
        assertEquals(74, metaData.getIdCounter(EdgesElement.ASPECT_NAME).longValue());
        
        // @TODO it would be good to add more tests to this
    }
    
    
    @Test 
    public void testLoadingCollection() throws URISyntaxException, FileNotFoundException, IOException, NdexException {
        File origWntCX = new File(getClass().getClassLoader().getResource("10_networks_new.cx").toURI());
        assertTrue(origWntCX.isFile());
        NiceCXNetworkReader reader = new NiceCXNetworkReader();
        NiceCXNetwork origNetwork = null;
        try (FileInputStream fis = new FileInputStream(origWntCX)){
            origNetwork = reader.readNiceCXNetwork(fis);
        }

        assertEquals("Collection of small", origNetwork.getNetworkName());

    }
    
}
