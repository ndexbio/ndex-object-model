package org.ndexbio.cxio.core.writers;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.interfaces.INiceCXNetworkWriter;
import org.ndexbio.cxio.core.NdexCXNetworkWriter;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.metadata.MetaDataCollection;
import org.ndexbio.model.cx.NamespacesElement;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.cx.Provenance;
import org.ndexbio.model.exceptions.NdexException;

/**
 * Writes {@link org.ndexbio.model.cx.NiceCXNetwork} (minus provenance) to output
 * stream in CX format leveraging
 * {@link org.ndexbio.cxio.core.NdexCXNetworkWriter} passed in via constructor
 * @author churas
 */
public class NiceCXNetworkWriter implements INiceCXNetworkWriter {
    
    private NdexCXNetworkWriter _writer;
    
    public NiceCXNetworkWriter(OutputStream outputStream, boolean compatibleToOldCXSpec) throws NdexException {
        try {
            _writer = new NdexCXNetworkWriter(outputStream, compatibleToOldCXSpec);
        }catch(IOException io){
            throw new NdexException("Caught IO exception trying to construct NdexCXNetworkWriter objec", io);
        }
            
    }
    /**
     * Constructor
     * @param writer Used to write network in {@link #writeNiceCXNetwork(org.ndexbio.model.cx.NiceCXNetwork) }
     */
    public NiceCXNetworkWriter(NdexCXNetworkWriter writer){
        _writer = writer;
    }
    
    /**
     * Writes {@code network} network in CX format using {@code writer} passed in 
     * via constructor (provenance is NOT written out)
     * @param network Network to write
     * @throws NdexException If there is a problem writing output
     * @throws NullPointerException If network or writer is null
     */
    @Override
    public void writeNiceCXNetwork(NiceCXNetwork network) throws NdexException{
        if (network == null){
            throw new NullPointerException("network is null");
        }
        if (_writer == null){
            throw new NullPointerException("writer is null");
        }
        try {
            _writer.start();
            MetaDataCollection mdc = network.getMetadata();
            mdc.remove(Provenance.ASPECT_NAME);
            _writer.writeMetadata(mdc);
            writeContextAspect(network);
            writeNetworkAttributesAspect(network);
            writeOpaqueAspects(network);
            writeNodesAspect(network);
            writeNodeAttributes(network);
            writeNodeAssociatedAttributes(network);
            writeEdgesAspect(network);
            writeEdgeAttributes(network);
            writeEdgeAssociatedAttributes(network);
            _writer.end(true, "");
        } catch(IOException io){
            
        } 
    }

    /**
     * Writes out @context aspect
     * @param network
     * @param postmd
     * @throws IOException If there was an error writing to writer
     * @throws JsonProcessingException If there is an error writing to json format
     */
    private void writeContextAspect(final NiceCXNetwork network)
			throws IOException, JsonProcessingException {
        NamespacesElement element = network.getNamespaces();
        if (element == null || element.isEmpty()){
            return;
        }
        writeAspect(network, element.getAspectName(), Arrays.asList(element));
    }

    /**
     * Writes
     * @param network
     * @param aspectName
     * @param elements
     * @throws IOException
     * @throws JsonProcessingException 
     */
    private void writeAspect(final NiceCXNetwork network, final String aspectName,
                               Collection<AspectElement> elements) 
            throws IOException, JsonProcessingException {
        if (aspectName == null){
            return;
        }
        if (elements == null || elements.isEmpty()){
            return;
        }
        _writer.startAspectFragment(aspectName);
        _writer.openFragment();
        for(AspectElement element : elements){
            _writer.writeElement(element);
        }
        _writer.closeFragment();
        _writer.endAspectFragment();
    }
    
    private void writeAssociatedAspect(final NiceCXNetwork network, final String aspectName,
                               Map<Long, Collection<AspectElement>> elementMap)
          throws IOException, JsonProcessingException {
         if (aspectName == null){
            return;
        }
        if (elementMap == null || elementMap.isEmpty()){
            return;
        }
        _writer.startAspectFragment(aspectName);
        _writer.openFragment();
        for (Long id : elementMap.keySet()){
            for(AspectElement element : elementMap.get(id)){
                _writer.writeElement(element);
            }
        }
        _writer.closeFragment();
        _writer.endAspectFragment();
    }
    /**
     * Writes out network attributes aspect
     * @param network
     * @param postmd
     * @throws IOException
     * @throws JsonProcessingException 
     */
    private void writeNetworkAttributesAspect(final NiceCXNetwork network)
			throws IOException, JsonProcessingException {

       writeAspect(network, NetworkAttributesElement.ASPECT_NAME,
                  (Collection<AspectElement>)(Object)network.getNetworkAttributes());
    }
    
    /**
     * Writes out all the opaque aspects
     * @param network
     * @throws IOException
     * @throws JsonProcessingException 
     */
    private void writeOpaqueAspects(final NiceCXNetwork network) 
            throws IOException, JsonProcessingException{
        
        Map<String, Collection<AspectElement>> opaques = network.getOpaqueAspectTable();
        for (String aspectName : opaques.keySet()){
            writeAspect(network, aspectName,
                  (Collection<AspectElement>)(Object)opaques.get(aspectName));
        }
    }
    
    /**
     * Writes NodesAspect
     * @param network
     * @throws IOException
     * @throws JsonProcessingException 
     */
    private void writeNodesAspect(final NiceCXNetwork network) 
            throws IOException, JsonProcessingException{
        writeAspect(network, NodesElement.ASPECT_NAME,
                  (Collection<AspectElement>)(Object)network.getNodes().values());
    }
    
    /**
     * Writes NodeAttributes aspect
     * @param network
     * @throws IOException
     * @throws JsonProcessingException 
     */
    private void writeNodeAttributes(final NiceCXNetwork network)
            throws IOException, JsonProcessingException{
        
        if (network.getNodeAttributes() == null || network.getNodeAttributes().isEmpty()){
            return;
        }
        _writer.startAspectFragment(NodeAttributesElement.ASPECT_NAME);
        _writer.openFragment();

        for(Collection<NodeAttributesElement> elementCol : network.getNodeAttributes().values()){
            for(AspectElement element : elementCol){
                _writer.writeElement(element);
            }
        }
        _writer.closeFragment();
        _writer.endAspectFragment();
    }

    /**
     * Writes Edges aspect
     * @param network
     * @throws IOException
     * @throws JsonProcessingException 
     */
    private void writeEdgesAspect(final NiceCXNetwork network) 
            throws IOException, JsonProcessingException{
        
        writeAspect(network, EdgesElement.ASPECT_NAME,
                  (Collection<AspectElement>)(Object)network.getEdges().values());
    }
    
    /**
     * Writes EdgeAttributes aspect
     * @param network
     * @throws IOException
     * @throws JsonProcessingException 
     */
    private void writeEdgeAttributes(final NiceCXNetwork network)
            throws IOException, JsonProcessingException{
        if (network.getEdgeAttributes() == null || network.getEdgeAttributes().isEmpty()){
            return;
        }
        _writer.startAspectFragment(EdgeAttributesElement.ASPECT_NAME);
        _writer.openFragment();

        for(Collection<EdgeAttributesElement> elementCol : network.getEdgeAttributes().values()){
            for(AspectElement element : elementCol){
                _writer.writeElement(element);
            }
        }
        _writer.closeFragment();
        _writer.endAspectFragment();    }
    
    private void writeNodeAssociatedAttributes(final NiceCXNetwork network)
            throws IOException, JsonProcessingException{
        Map<String, Map<Long, Collection<AspectElement>>> nodeAssoc = network.getNodeAssociatedAspects();
        if (nodeAssoc == null){
            return;
        }          
        for(String aspectName: nodeAssoc.keySet()){
           writeAssociatedAspect(network, aspectName, nodeAssoc.get(aspectName));
        }
    }
    
    private void writeEdgeAssociatedAttributes(final NiceCXNetwork network)
            throws IOException, JsonProcessingException{
        Map<String, Map<Long, Collection<AspectElement>>> edgeAssoc = network.getEdgeAssociatedAspects();
        if (edgeAssoc == null){
            return;
        }
        for(String aspectName: edgeAssoc.keySet()){
            writeAssociatedAspect(network, aspectName, edgeAssoc.get(aspectName));
        }
    }
    

}
