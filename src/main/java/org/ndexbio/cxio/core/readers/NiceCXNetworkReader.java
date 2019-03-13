/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.cxio.core.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.ndexbio.cxio.aspects.datamodels.CartesianLayoutElement;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.aspects.readers.CartesianLayoutFragmentReader;
import org.ndexbio.cxio.aspects.readers.CyGroupsFragmentReader;
import org.ndexbio.cxio.aspects.readers.CyTableColumnFragmentReader;
import org.ndexbio.cxio.aspects.readers.CyViewsFragmentReader;
import org.ndexbio.cxio.aspects.readers.CyVisualPropertiesFragmentReader;
import org.ndexbio.cxio.aspects.readers.EdgeAttributesFragmentReader;
import org.ndexbio.cxio.aspects.readers.EdgesFragmentReader;
import org.ndexbio.cxio.aspects.readers.GeneralAspectFragmentReader;
import org.ndexbio.cxio.aspects.readers.HiddenAttributesFragmentReader;
import org.ndexbio.cxio.aspects.readers.NetworkAttributesFragmentReader;
import org.ndexbio.cxio.aspects.readers.NetworkRelationsFragmentReader;
import org.ndexbio.cxio.aspects.readers.NodeAttributesFragmentReader;
import org.ndexbio.cxio.aspects.readers.NodesFragmentReader;
import org.ndexbio.cxio.aspects.readers.SubNetworkFragmentReader;
import org.ndexbio.cxio.core.CxElementReader2;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.core.interfaces.AspectFragmentReader;
import org.ndexbio.cxio.core.interfaces.INiceCXNetworkReader;
import org.ndexbio.cxio.metadata.MetaDataCollection;
import org.ndexbio.cxio.metadata.MetaDataElement;
import org.ndexbio.model.cx.CitationElement;
import org.ndexbio.model.cx.EdgeCitationLinksElement;
import org.ndexbio.model.cx.EdgeSupportLinksElement;
import org.ndexbio.model.cx.FunctionTermElement;
import org.ndexbio.model.cx.NamespacesElement;
import org.ndexbio.model.cx.NdexNetworkStatus;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.cx.NodeCitationLinksElement;
import org.ndexbio.model.cx.NodeSupportLinksElement;
import org.ndexbio.model.cx.Provenance;
import org.ndexbio.model.cx.SupportElement;
import org.ndexbio.model.exceptions.NdexException;

/**
 * Instances of this class take data from an {@link java.io.InputStream} which
 * is assumed to be in CX format and
 * returns a {@link org.ndexbio.model.cx.NiceCXNetwork} object leveraging
 * the {@link org.ndexbio.cxio.core.CxElementReader2} to parse the CX
 * coming in the {@link java.io.InputStream}
 * 
 * NOTE: This code was taken from ndex-java-client {@code NdexRestClientUtilities} class
 *       with small modifications to make it easier to reduce lines of code in methods.
 * @author unknown
 */
public class NiceCXNetworkReader implements INiceCXNetworkReader {

    
    private Set<AspectFragmentReader> _readers;
    
    /**
     * Constructor that creates default set of {@link org.ndexbio.cxio.core.interfaces.AspectFragmentReader} objects used
     * to parse {@link java.io.InputStream} passed in {@link #readNiceCXNetwork(java.io.InputStream)}
     */
    public NiceCXNetworkReader(){
        this(null);
    }
    
    /**
     * Constructor that lets caller override default {@link org.ndexbio.cxio.core.interfaces.AspectFragmentReader} objects used
     * to parse {@link java.io.InputStream} passed in {@link #readNiceCXNetwork(java.io.InputStream)}
     * @param readers Custom set of {@link org.ndexbio.cxio.core.interfaces.AspectFragmentReader} objects
     */
    public NiceCXNetworkReader(Set<AspectFragmentReader> readers){
        if (readers != null){
            _readers = readers;
            return;
        }
        createReaders();
    }
    
    /**
     * Creates Set of {@link org.ndexbio.cxio.aspects.readers.AbstractFragmentReader} 
     * objects used to parse the incoming CX data. 
     */
    private void createReaders(){
        _readers = new HashSet<>(23);
		  
        _readers.add(EdgesFragmentReader.createInstance());
        _readers.add(EdgeAttributesFragmentReader.createInstance());
        _readers.add(NetworkAttributesFragmentReader.createInstance());
        _readers.add(NodesFragmentReader.createInstance());
        _readers.add(NodeAttributesFragmentReader.createInstance());
        
        _readers.add(new GeneralAspectFragmentReader<NdexNetworkStatus> (NdexNetworkStatus.ASPECT_NAME,
                      NdexNetworkStatus.class));
        _readers.add(new GeneralAspectFragmentReader<NamespacesElement> (NamespacesElement.ASPECT_NAME,NamespacesElement.class));
        _readers.add(new GeneralAspectFragmentReader<FunctionTermElement> (FunctionTermElement.ASPECT_NAME,FunctionTermElement.class));
        _readers.add(new GeneralAspectFragmentReader<CitationElement> (CitationElement.ASPECT_NAME,CitationElement.class));
        _readers.add(new GeneralAspectFragmentReader<SupportElement> (SupportElement.ASPECT_NAME,SupportElement.class));

        _readers.add(new GeneralAspectFragmentReader<EdgeCitationLinksElement> (EdgeCitationLinksElement.ASPECT_NAME,EdgeCitationLinksElement.class));
        _readers.add(new GeneralAspectFragmentReader<EdgeSupportLinksElement> (EdgeSupportLinksElement.ASPECT_NAME,EdgeSupportLinksElement.class));
        _readers.add(new GeneralAspectFragmentReader<NodeCitationLinksElement> (NodeCitationLinksElement.ASPECT_NAME,NodeCitationLinksElement.class));
        _readers.add(new GeneralAspectFragmentReader<NodeSupportLinksElement> (NodeSupportLinksElement.ASPECT_NAME,NodeSupportLinksElement.class));
        _readers.add(new GeneralAspectFragmentReader<Provenance> (Provenance.ASPECT_NAME,Provenance.class));

        _readers.add( CyVisualPropertiesFragmentReader.createInstance());
        _readers.add( CartesianLayoutFragmentReader.createInstance());
        _readers.add( NetworkRelationsFragmentReader.createInstance());
        _readers.add( SubNetworkFragmentReader.createInstance());
        _readers.add( CyGroupsFragmentReader.createInstance());

        _readers.add( HiddenAttributesFragmentReader.createInstance());
        _readers.add( CyTableColumnFragmentReader.createInstance());
        _readers.add( CyViewsFragmentReader.createInstance());
    }
    
    @Override
    public NiceCXNetwork readNiceCXNetwork(InputStream in) throws NdexException {
        try {
            CxElementReader2 r = new CxElementReader2(in, _readers, true);
	        
            MetaDataCollection metadata = r.getPreMetaData();
			
            long nodeIdCounter = 0;
            long edgeIdCounter = 0;

            NiceCXNetwork niceCX = new NiceCXNetwork ();
            for ( AspectElement elmt : r ) {
                switch ( elmt.getAspectName() ) {
                    case NodesElement.ASPECT_NAME :       //Node
                        NodesElement n = (NodesElement) elmt;
                        niceCX.addNode(n);
                    if (n.getId() > nodeIdCounter )
                        nodeIdCounter = n.getId();
                            break;
                        case NdexNetworkStatus.ASPECT_NAME:   //ndexStatus we ignore this in CX
                            break; 
                        case EdgesElement.ASPECT_NAME:       // Edge
                            EdgesElement ee = (EdgesElement) elmt;
                            niceCX.addEdge(ee);
                            if( ee.getId() > edgeIdCounter)
                                edgeIdCounter = ee.getId();
                            break;
                        case NodeAttributesElement.ASPECT_NAME:  // node attributes
                            niceCX.addNodeAttribute((NodeAttributesElement) elmt );
                            break;
                        case NetworkAttributesElement.ASPECT_NAME: //network attributes
                            niceCX.addNetworkAttribute(( NetworkAttributesElement) elmt);
                            break;
                        case EdgeAttributesElement.ASPECT_NAME:
                            niceCX.addEdgeAttribute((EdgeAttributesElement)elmt);
                            break;
                        case CartesianLayoutElement.ASPECT_NAME:
                            CartesianLayoutElement e = (CartesianLayoutElement)elmt;
                            niceCX.addNodeAssociatedAspectElement(Long.valueOf(e.getNode()), e);
                            break;
                        case Provenance.ASPECT_NAME:
                            Provenance prov = (Provenance) elmt;
                            niceCX.setProvenance(prov);
                            break;
                        case NamespacesElement.ASPECT_NAME:
                            NamespacesElement ns = (NamespacesElement) elmt;
                            niceCX.setNamespaces(ns);
                            break;
                        case CitationElement.ASPECT_NAME:
                            CitationElement ce = (CitationElement) elmt;
                            niceCX.addCitation(ce);
                            break;
                        default:    // opaque aspect
                            niceCX.addOpaqueAspect(elmt);
                }
            }
            MetaDataCollection mergedMetaData = mergeMetaDataCollections(metadata, r.getPostMetaData());
            updateNodeCounts(mergedMetaData, nodeIdCounter);
            updateEdgeCounts(mergedMetaData, edgeIdCounter);
            metadata.remove(NdexNetworkStatus.ASPECT_NAME);
	    niceCX.setMetadata(mergedMetaData);
            return niceCX;
        }catch(IOException io){
            throw new NdexException("Error reading CX from stream", io);
        }
    }
    
    /**
     * If {@code postmetadata} is {@code null} then {@code preMetaData} is returned
     * 
     * If {@code postmetadata} is not {@code null} and {@code preMetaData} is null then {@code postmetadata} is returned
     * 
     * If neither are {@code null} then this method
     * iterates through all {@link org.ndexbio.cxio.metadata.MetaDataElement} elements
     * from the {@code postmetadata} 
     * updating {@code preMetaData} objects {@link org.ndexbio.cxio.metadata.MetaDataCollection#getElementCount(java.lang.String)} counts
     * in {@code preMetaData} returning the {@code premetaData} object.
     * @param preMetaData Initial {@link org.ndexbio.cxio.metadata.MetaDataCollection} before parsing
     * @param postMetaData {@link org.ndexbio.cxio.metadata.MetaDataCollection} after parsing
     * @return {@link org.ndexbio.cxio.metadata.MetaDataCollection} merged
     */
    protected MetaDataCollection mergeMetaDataCollections(MetaDataCollection preMetaData,
                   MetaDataCollection postMetaData){
        if (postMetaData == null){
            return preMetaData;
        }
        if (preMetaData == null){
            return postMetaData;
        }
        for (MetaDataElement e : postMetaData) {
            Long cnt = e.getIdCounter();
            if (cnt != null){
                preMetaData.setIdCounter(e.getName(), cnt);
            }
            cnt = e.getElementCount();
            if (cnt != null){
                preMetaData.setElementCount(e.getName(), cnt);
            }
        }
        return preMetaData;
    }
    
    /**
     * Updates {@code metaData} node count with value in {@code nodeIdCounter}
     * @param metaData {@link org.ndexbio.cxio.metadata.MetaDataCollection} to update
     * @param nodeIdCounter New node count to set in {@code metaData}
     */
    protected void updateNodeCounts(MetaDataCollection metaData, long nodeIdCounter){
        Long cxNodeIdCounter = metaData.getIdCounter(NodesElement.ASPECT_NAME);
	if (cxNodeIdCounter == null || cxNodeIdCounter.longValue() < nodeIdCounter)
            metaData.setIdCounter(NodesElement.ASPECT_NAME, Long.valueOf(nodeIdCounter));   
    }
    
    /**
     * Updates {@code metaData} node count with value in {@code edgeIdCounter}
     * @param metaData {@link org.ndexbio.cxio.metadata.MetaDataCollection} to update
     * @param edgeIdCounter New edge count to set in {@code metaData}
     */
    protected void updateEdgeCounts(MetaDataCollection metaData, long edgeIdCounter){
        Long cxEdgeIdCounter = metaData.getIdCounter(EdgesElement.ASPECT_NAME);
        if (cxEdgeIdCounter == null || cxEdgeIdCounter.longValue() < edgeIdCounter)
            metaData.setIdCounter(EdgesElement.ASPECT_NAME, Long.valueOf(edgeIdCounter));
    }
    
}
