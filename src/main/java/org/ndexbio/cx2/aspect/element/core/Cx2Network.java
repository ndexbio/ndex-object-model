package org.ndexbio.cx2.aspect.element.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.ndexbio.cx2.aspect.element.cytoscape.VisualEditorProperties;
import org.ndexbio.cx2.io.CXReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Cx2Network {
	
	public static final List<String> cx2SpecialAspects = Arrays.asList(
			CxAttributeDeclaration.ASPECT_NAME,CxEdgeBypass.ASPECT_NAME,
			CxNodeBypass.ASPECT_NAME, VisualEditorProperties.ASPECT_NAME,
			CxNetworkAttribute.ASPECT_NAME);
	
	private Map<String,CxMetadata> cx2Metadata;
	
	// should be null when there is no error.
	private String errorMessage; 
	
	private VisualEditorProperties editorProperties; 
	
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
		errorMessage = null;
		
	}

	public Cx2Network (InputStream in) throws JsonParseException, IOException {
		CXReader reader = new CXReader (in);
		
		ObjectMapper om = new ObjectMapper();
		
		for (CxAspectElement<?> elmt : reader) {
				switch (elmt.getAspectName()) {
				case CxNode.ASPECT_NAME: // Node
					CxNode n = (CxNode) elmt;
					getNodes().put(n.getId(),n);
					break;
				case CxEdge.ASPECT_NAME: // Edge
					CxEdge e = (CxEdge) elmt;
					getEdges().put(e.getId(),e);
					break;
				case CxEdgeBypass.ASPECT_NAME:
					CxEdgeBypass eb = (CxEdgeBypass)elmt;
					getEdgeBypasses().put(eb.getId(), eb);
					break;
				case CxNodeBypass.ASPECT_NAME:
					CxNodeBypass nb = (CxNodeBypass)elmt;
					getNodeBypasses().put(nb.getId(),nb);
					break;
				case CxNetworkAttribute.ASPECT_NAME:
					networkAttributes = (CxNetworkAttribute)elmt;
					break;
				case CxVisualProperty.ASPECT_NAME:
					visualProperties = (CxVisualProperty) elmt;
					break;					
				case CxAttributeDeclaration.ASPECT_NAME:
					CxAttributeDeclaration dec = (CxAttributeDeclaration)elmt;
					this.attributeDeclarations = dec;
					break;
				case VisualEditorProperties.ASPECT_NAME:
					this.editorProperties = (VisualEditorProperties)elmt; 
					break;
				default:
					addOpaqueAspect((CxOpaqueAspectElement)elmt);
					break;
				}
		}
		
		cx2Metadata = reader.getMetadata();

	}
	
	
	
	public CxAttributeDeclaration getAttributeDeclarations() {
		return attributeDeclarations;
	}

	public String getErrorMessage() { return this.errorMessage;}
	
	
	private void addOpaqueAspect(CxOpaqueAspectElement e) {
		Collection<CxOpaqueAspectElement> aspectElmts = opaqueAspects.get(e.getAspectName());
		if ( aspectElmts == null) {
			aspectElmts = new LinkedList<> ();
			opaqueAspects.put(e.getAspectName(), aspectElmts);
		}
		aspectElmts.add(e);
		
	}


}
