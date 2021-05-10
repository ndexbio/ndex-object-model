package org.ndexbio.cx2.converter;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.ndexbio.cxio.core.readers.NiceCXNetworkReader;
import org.ndexbio.cxio.core.writers.NiceCXCX2Writer;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.exceptions.NdexException;

public class CXToCX2Converter {

	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, NdexException {

		if ( args.length != 2 ) {
			System.out.println("Usage: java -cp <jar> org.ndexbio.cx2.converter.CXToCX2Converter <CXFilePath> <CX2FilePath>\n" );
			System.exit(1);
		}
		
		String src = args[0];
		String tgt = args[1];
		
		File origWntCX = new File(src);
        
	    NiceCXNetworkReader reader = new NiceCXNetworkReader();
	    NiceCXNetwork origNetwork = null;
	    try (FileInputStream fis = new FileInputStream(origWntCX)){
	            origNetwork = reader.readNiceCXNetwork(fis);
	    }
	    
	    try (FileOutputStream out = new FileOutputStream (tgt) ) {
	    	NiceCXCX2Writer cx2Writer = new  NiceCXCX2Writer (out);
	    	cx2Writer.writeAsCX2(origNetwork);
	    }

			
	}

}
