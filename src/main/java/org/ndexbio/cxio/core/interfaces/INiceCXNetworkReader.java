/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.cxio.core.interfaces;

import java.io.InputStream;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.exceptions.NdexException;

/**
 * Implementing interfaces support generating {@link org.ndexbio.model.cx.NiceCXNetwork} 
 * objects from an {@link java.io.InputStream}
 * @author churas
 */
public interface INiceCXNetworkReader {
 
    
    /**
     * Reads {@code in} and generates {@link org.ndexbio.model.cx.NiceCXNetwork} 
     * object
     * @param in {@link java.io.InputStream} to extract the {@link org.ndexbio.model.cx.NiceCXNetwork} from
     * @return {@link org.ndexbio.model.cx.NiceCXNetwork} object representing data from {@code in}
     * @throws NdexException If there was a problem parsing the input.
     */
    public NiceCXNetwork readNiceCXNetwork(final InputStream in) throws NdexException;
}
