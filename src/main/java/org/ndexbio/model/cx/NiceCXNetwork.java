package org.ndexbio.model.cx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.metadata.MetaDataCollection;

/**
 * Note: the node and edge attribute tables are indexed on node and edge id respectively. 
 * However, the po field is still in the compact format ( a list) in the attributeElement object. So don't use that field in this model.
 * @author chenjing
 *
 */

public class NiceCXNetwork {

	private MetaDataCollection metadata;
	
	private NamespacesElement namespaces;
	private Map<Long, NodesElement> nodes;
	private Map<Long, EdgesElement> edges;
	private Map<Long, CitationElement> citations;
	private Map<Long, SupportElement> supports;
	
	private Map<Long, Collection<NodeAttributesElement>> nodeAttributes;
	private Map<Long, Collection<EdgeAttributesElement>> edgeAttributes;
	
	private Collection <NetworkAttributesElement> networkAttributes;
	
	//function term, node/edge citation and supports might use these 2 tables.
	private Map<String,Map<Long,Collection<AspectElement>>> nodeAssociatedAspects;
	private Map<String,Map<Long, Collection<AspectElement>>> edgeAssociatedAspects;
	
	private Map<String, Collection<AspectElement>> opaqueAspects;
	
	private Provenance provenance;
	
	public NiceCXNetwork() {
		setMetadata(new MetaDataCollection());
		namespaces = new NamespacesElement();
		nodes = new HashMap<>();
		edges = new HashMap<>();
		citations = new HashMap<>();
		supports = new HashMap<>();
		
		nodeAttributes = new HashMap<> ();
		edgeAttributes = new HashMap<> ();
		
		networkAttributes = new ArrayList<> ();
		
		nodeAssociatedAspects = new HashMap<>();
		edgeAssociatedAspects = new HashMap<>();
		
		opaqueAspects = new HashMap<>();
		
	}
	
	public void addNode(NodesElement node) {
		nodes.put(Long.valueOf(node.getId()), node);
	}
	
	public void addEdge(EdgesElement edge) {
		edges.put(edge.getId(), edge);
	}
	
	public void addNetworkAttribute ( NetworkAttributesElement networkAttribute) {
		networkAttributes.add(networkAttribute);
	}
	
	public void addNodeAttribute( NodeAttributesElement nodeAttribute) {	
			addNodeAttribute(nodeAttribute.getPropertyOf(),nodeAttribute);
	}
	
	public void addNodeAttribute( Long i, NodeAttributesElement nodeAttribute) {
		
		Collection<NodeAttributesElement> nodeAttrs = nodeAttributes.get(i);
		if ( nodeAttrs == null) {
				nodeAttrs = new LinkedList<>();
				nodeAttributes.put(i,nodeAttrs);
		}
		nodeAttrs.add(nodeAttribute);
	}
	
	public void addEdgeAttribute(EdgeAttributesElement edgeAttribute) {
			addEdgeAttribute(edgeAttribute.getPropertyOf(),edgeAttribute);
		
	}

	
	public void addEdgeAttribute(Long i , EdgeAttributesElement edgeAttribute) {
			Collection<EdgeAttributesElement> edgeAttrs = edgeAttributes.get(i);
			if ( edgeAttrs == null) {
				edgeAttrs = new LinkedList<>();
				edgeAttributes.put(i, edgeAttrs);
			}
			edgeAttrs.add(edgeAttribute);
	}
	
	public void addSupport(SupportElement e) {
		supports.put(Long.valueOf(e.getId()), e);
	}
	
	public void addCitation(CitationElement e) {
		citations.put(Long.valueOf(e.getId()),e);
	}
	
	public void removeCitation(long i) {
		citations.remove(Long.valueOf(i));
	}
	
	public Map<Long, CitationElement> getCitations() {
		return citations;
	}
	
	public void addOpaqueAspect(AspectElement e) {
		Collection<AspectElement> aspectElmts = opaqueAspects.get(e.getAspectName());
		if ( aspectElmts == null) {
			aspectElmts = new LinkedList<> ();
			opaqueAspects.put(e.getAspectName(), aspectElmts);
		}
		aspectElmts.add(e);
		
	}
	
	public void addNodeAssociatedAspectElement(Long nodeId, AspectElement elmt) {
		addAssciatatedAspectElement(nodeAssociatedAspects, nodeId, elmt);
	}

	public void addEdgeAssociatedAspectElement(Long edgeId, AspectElement elmt) {
		addAssciatatedAspectElement(edgeAssociatedAspects, edgeId, elmt);	
	}
	
	private static void addAssciatatedAspectElement(Map<String,Map<Long,Collection<AspectElement>>> table, Long id, AspectElement elmt) {
		Map<Long,Collection<AspectElement>> aspectElements = table.get(elmt.getAspectName());
		if ( aspectElements == null) {
			aspectElements = new TreeMap<> ();
			table.put(elmt.getAspectName(), aspectElements);
		}
		Collection<AspectElement> elmts = aspectElements.get(id);
		
		if (elmts == null) {
			elmts = new ArrayList<>();
			aspectElements.put(id, elmts);
		}
		elmts.add(elmt);
	}

	
	public MetaDataCollection getMetadata() {
		return metadata;
	}

	public void setMetadata(MetaDataCollection metadata) {
		this.metadata = metadata;
	}
	
	public void addNameSpace(String prefix, String uri) {
		namespaces.put(prefix, uri);
	}
	
	public void setNamespaces(NamespacesElement ns ) {
		this.namespaces = ns;
	}
	
	public NamespacesElement getNamespaces() {
		return this.namespaces;
	}
	
	public Map<Long, EdgesElement> getEdges () {
		return this.edges;
	}
	
	public Map<Long,NodesElement> getNodes() {
		return this.nodes;
	}
	
	public Map<String,Collection<AspectElement>> getOpaqueAspectTable() {
		return this.opaqueAspects;
	}
	
	public Collection <NetworkAttributesElement> getNetworkAttributes() {
		return this.networkAttributes;
	}
	
	public Map<Long, Collection<NodeAttributesElement>> getNodeAttributes() {
		return this.nodeAttributes;
	}
	
	public Map<Long, Collection<EdgeAttributesElement>> getEdgeAttributes() {
		return this.edgeAttributes;
	}
	
	public Map<String,Map<Long,Collection<AspectElement>>> getNodeAssociatedAspects() {
		return this.nodeAssociatedAspects;
	}
	
	public Map<String,Map<Long,Collection<AspectElement>>> getEdgeAssociatedAspects() {
		return this.edgeAssociatedAspects;
	}
	
	public Map<Long,Collection<AspectElement>> getNodeAssociatedAspect(String aspectName) {
		return this.nodeAssociatedAspects.get(aspectName);
	}
	
	public Map<Long,Collection<AspectElement>> getEdgeAssociatedAspect(String aspectName) {
		return this.edgeAssociatedAspects.get(aspectName);
	}

	public Provenance getProvenance() {
		return provenance;
	}

	public void setProvenance(Provenance provenance) {
		this.provenance = provenance;
	}
	
	public String getNetworkName () {
		for ( NetworkAttributesElement e  :networkAttributes) {
			if (e.getName().equals("name"))
				return e.getValue();
		}
		return null;
	}
}
