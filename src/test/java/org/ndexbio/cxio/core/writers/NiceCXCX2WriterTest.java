package org.ndexbio.cxio.core.writers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.ndexbio.cxio.core.readers.NiceCXNetworkReader;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.exceptions.NdexException;

public class NiceCXCX2WriterTest {

	@Test
	public void test() throws URISyntaxException, FileNotFoundException, IOException, NdexException {
		
		File origWntCX = new File(getClass().getClassLoader().getResource("nicecxnetworkwriterfulltest/wntsignaling.cx").toURI());
	        
		assertTrue(origWntCX.isFile());
	    NiceCXNetworkReader reader = new NiceCXNetworkReader();
	    NiceCXNetwork origNetwork = null;
	    try (FileInputStream fis = new FileInputStream(origWntCX)){
	            origNetwork = reader.readNiceCXNetwork(fis);
	    }
	    
	    String tmpFileName = "tempwntsignaling.cx2";
	    try (FileOutputStream out = new FileOutputStream (tmpFileName) ) {
	    	NiceCXCX2Writer cx2Writer = new  NiceCXCX2Writer (out);
	    	cx2Writer.writeAsCX2(origNetwork);
	    	fail("CX2 converter errror was not caught.");
	    } catch ( NdexException e) {
	    	assertEquals("Duplicate edges attribute on id: 0. Attribute 'DIRECT' has value (t) and (YES)",
	    			e.getMessage());
	    	File f = new File(tmpFileName);
	    	f.delete();
	    }
	    
	}
	
	
	@Test
	public void test1() throws URISyntaxException, FileNotFoundException, IOException, NdexException {
		
		File origWntCX = new File(getClass().getClassLoader().getResource("network1 - 3n2e.cx").toURI());
	        
		assertTrue(origWntCX.isFile());
	    NiceCXNetworkReader reader = new NiceCXNetworkReader();
	    NiceCXNetwork origNetwork = null;
	    try (FileInputStream fis = new FileInputStream(origWntCX)){
	            origNetwork = reader.readNiceCXNetwork(fis);
	    }
	    
	    String tmpFileName = "temp3n2e.cx2";
	    try (FileOutputStream out = new FileOutputStream (tmpFileName) ) {
	    	NiceCXCX2Writer cx2Writer = new  NiceCXCX2Writer (out);
	    	cx2Writer.writeAsCX2(origNetwork);
	    }
	    
	    //TODO: validate the result
	}
	
	@Test
	public void test2() throws URISyntaxException, FileNotFoundException, IOException, NdexException {
		
		File origWntCX = new File(getClass().getClassLoader().getResource("wntsignaling_fixed.cx").toURI());
	        
		assertTrue(origWntCX.isFile());
	    NiceCXNetworkReader reader = new NiceCXNetworkReader();
	    NiceCXNetwork origNetwork = null;
	    try (FileInputStream fis = new FileInputStream(origWntCX)){
	            origNetwork = reader.readNiceCXNetwork(fis);
	    }
	    
	    String tmpFileName = "tempwntsignaling_fixed.cx2";
	    try (FileOutputStream out = new FileOutputStream (tmpFileName) ) {
	    	NiceCXCX2Writer cx2Writer = new  NiceCXCX2Writer (out);
	    	cx2Writer.writeAsCX2(origNetwork);
	    } 
	    
	    //TODO: validate the result
	    
	}


}
