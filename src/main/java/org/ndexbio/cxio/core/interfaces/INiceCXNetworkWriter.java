/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.cxio.core.interfaces;

import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.exceptions.NdexException;

/**
 * Interface for classes that want to implement writers for NiceCXNetworks
 * @author churas
 */
public interface INiceCXNetworkWriter {
    
    /**
     * Writes {@code network} with help of {@code writer}
     * @param network NiceCXNetwork to write out
     * @throws NdexException 
     */
    public void writeNiceCXNetwork(NiceCXNetwork network) throws NdexException;
    
}
