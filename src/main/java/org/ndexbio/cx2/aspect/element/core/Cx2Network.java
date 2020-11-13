package org.ndexbio.cx2.aspect.element.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.ndexbio.cx2.aspect.element.cytoscape.VisualEditorProperties;

public class Cx2Network {
	
	public static final List<String> cx2SpecialAspects = Arrays.asList(
			CxAttributeDeclaration.ASPECT_NAME,CxEdgeBypass.ASPECT_NAME,
			CxNodeBypass.ASPECT_NAME, VisualEditorProperties.ASPECT_NAME,
			CxNetworkAttribute.ASPECT_NAME);
	
	private Map<String,CxMetadata> cx2Metadata;
	
	public Map<String, CxMetadata> getCx2Metadata() {
		return cx2Metadata;
	}

	public CxNetworkAttribute getNetworkAttributes() {
		return networkAttributes;
	}

	public Map<Long, CxNode> getNodes() {
		return nodes;
	}

	public Map<Long, CxEdge> getEdges() {
		return edges;
	}

	public CxVisualProperty getVisualProperties() {
		return visualProperties;
	}

	public Map<Long, CxNodeBypass> getNodeBypasses() {
		return nodeBypasses;
	}

	public Map<Long, CxEdgeBypass> getEdgeBypasses() {
		return edgeBypasses;
	}

	public Map<String, Collection<CxOpaqueAspectElement>> getOpaqueAspects() {
		return opaqueAspects;
	}

	private CxAttributeDeclaration attributeDeclarations;
	
	private CxNetworkAttribute networkAttributes;
	
	private Map<Long, CxNode> nodes;
	
	private Map<Long, CxEdge> edges;
	
	private CxVisualProperty visualProperties;
	
	private Map<Long, CxNodeBypass> nodeBypasses;
	
	private Map<Long, CxEdgeBypass> edgeBypasses;
	
	private Map<String, Collection<CxOpaqueAspectElement>> opaqueAspects;
	
	public Cx2Network () {
		cx2Metadata = new HashMap<> ();
		attributeDeclarations = new CxAttributeDeclaration();
		networkAttributes = new CxNetworkAttribute();
		nodes = new TreeMap<>();
		edges = new TreeMap<> ();
		visualProperties = new CxVisualProperty();
		nodeBypasses = new TreeMap<>();
		edgeBypasses = new TreeMap<>();
		opaqueAspects = new HashMap<> () ;
		
	}

	public CxAttributeDeclaration getAttributeDeclarations() {
		return attributeDeclarations;
	}

}
