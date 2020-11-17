package org.ndexbio.cx2.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxEdgeBypass;
import org.ndexbio.cx2.aspect.element.core.CxMetadata;
import org.ndexbio.cx2.aspect.element.core.CxNetworkAttribute;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cx2.aspect.element.core.CxNodeBypass;
import org.ndexbio.cx2.aspect.element.core.CxVisualProperty;
import org.ndexbio.cx2.aspect.element.core.DeclarationEntry;
import org.ndexbio.cx2.aspect.element.cytoscape.VisualEditorProperties;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.ndexbio.cxio.aspects.datamodels.CartesianLayoutElement;
import org.ndexbio.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.metadata.MetaDataCollection;
import org.ndexbio.cxio.metadata.MetaDataElement;
import org.ndexbio.model.cx.NamespacesElement;
import org.ndexbio.model.cx.Provenance;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AspectAttributeStat {
	
	private Map<String, Map<String, AspectAttributeStatEntry>> table;
	
	// internal state controls for performance.
	private boolean hasNodeName;
	private boolean hasNodeRep;
	private boolean hasEdgeInteraction;
	
	// number of node and edge visual property bypasses in the cyVisualProperties aspect. 
	private int nodeBypassCount;
	private int edgeBypassCount;
	private long nodeCount;
	private long edgeCount;
	private boolean hasNamespacesAspect;
	
	public AspectAttributeStat() {
		
		table = new HashMap<> ();
		hasNodeName = false;
		hasNodeRep = false;
		hasEdgeInteraction = false;
		nodeBypassCount = 0;
		edgeBypassCount = 0;
		nodeCount = 0;
		edgeCount = 0;
		hasNamespacesAspect = false;
	}
	
	
	public void addNode (NodesElement n ) throws NdexException {
		nodeCount++;
		if ( ! hasNodeName) {
			if (n.getNodeName() !=null ) {
				addCX1ReservedAttr(CxNode.ASPECT_NAME, CxNode.NAME, "n",null);
				hasNodeName = true;
			}
		}
		if ( !hasNodeRep) {
			if ( n.getNodeRepresents() != null) {
				addCX1ReservedAttr(CxNode.ASPECT_NAME, CxNode.REPRESENTS, "r",null);
				hasNodeRep = true;
			}
		}
	}
	
	public void addEdge (EdgesElement edge) throws NdexException {
		edgeCount++;
		if ( !hasEdgeInteraction) {
			if ( edge.getInteraction() != null) {
				addCX1ReservedAttr(CxEdge.ASPECT_NAME, CxEdge.INTERACTION, "i", edge.getInteraction());
				hasEdgeInteraction = true;
			}
		}
	}


	private void addCX1ReservedAttr(String aspectName, String attrName, String alias, String value ) throws NdexException {
		Map<String, AspectAttributeStatEntry> attributes = table.get(aspectName);
		if ( attributes == null) {
			attributes = new HashMap<> ();
			table.put(aspectName, attributes);
		}
		AspectAttributeStatEntry e = attributes.get(attrName);
		if ( e == null)
			e = new AspectAttributeStatEntry();
		String error = e.addDatatype(ATTRIBUTE_DATA_TYPE.STRING);
		if ( error != null)
			throw new NdexException ("Data type error in attribute " + attrName + " of aspect "+ aspectName +
					 ": " + error);
		if ( alias != null)
			e.setAlias(alias);
		if (value != null)
			e.addValue(value);
		attributes.put (attrName,e);
	}

	public void addNodeAttribute (NodeAttributesElement attr) throws NdexException, JsonProcessingException {
		String attrName = attr.getName();
		
		Map<String, AspectAttributeStatEntry> nodeAttributes = table.get(CxNode.ASPECT_NAME);
		
		if ( nodeAttributes == null) {
			nodeAttributes = new HashMap<>();
			table.put(CxNode.ASPECT_NAME,nodeAttributes);
		}	

		// check if there are name conflicts
		if ( nodeAttributes.get(CxNode.NAME) != null && attrName.equals("n")) {
			nodeAttributes.get(CxNode.NAME).setAlias(null);
		} else if (nodeAttributes.get(CxNode.REPRESENTS) != null && attrName.equals("r")) {
			nodeAttributes.get(CxNode.REPRESENTS).setAlias(null);
		}

		AspectAttributeStatEntry e = nodeAttributes.get(attrName);
		if ( e == null ) {
			e = new AspectAttributeStatEntry ();
			nodeAttributes.put(attrName, e);
		}	
		
		ATTRIBUTE_DATA_TYPE t = attr.getDataType();
		String error = e.addDatatype(t);
		if ( error != null) {
			throw new NdexException (constructErrMsg(attr,error));
		}
		if ( t == ATTRIBUTE_DATA_TYPE.STRING || t == ATTRIBUTE_DATA_TYPE.BOOLEAN) {
			e.addValue(convertAttributeValue(attr));
			
		}
	}

	/**
	 * 
	 * @param attr
	 * @return A warning message if network attribute name is duplicated. Returns null otherwise.
	 * @throws NdexException
	 * @throws JsonProcessingException
	 */
	public String addNetworkAttribute (NetworkAttributesElement attr) throws NdexException, JsonProcessingException {
		String attrName = attr.getName();
		
		String warning = null;
		
		Map<String, AspectAttributeStatEntry> attributes = table.get(CxNetworkAttribute.ASPECT_NAME);
		
		if ( attributes == null) {
			attributes = new HashMap<>();
			table.put(CxNetworkAttribute.ASPECT_NAME,attributes);
		}	

		AspectAttributeStatEntry e = attributes.get(attrName);
		if ( e != null ) 
			warning = "Duplicated network attribute '" + attrName + "' found.";	
		e = new AspectAttributeStatEntry ();
		String error = e.addDatatype(attr.getDataType());
		if ( error != null) {
			throw new NdexException (constructErrMsg(attr, error));
		}
		attributes.put(attrName, e);
		return warning;
	}
	
	public void addEdgeAttribute (EdgeAttributesElement attr) throws NdexException, JsonProcessingException {
		String attrName = attr.getName();
		
		Map<String, AspectAttributeStatEntry> edgeAttributes = table.get(CxEdge.ASPECT_NAME);
		
		if ( edgeAttributes == null) {
			edgeAttributes = new HashMap<>();
			table.put(CxEdge.ASPECT_NAME,edgeAttributes);
		}	

		// check if there are name conflicts
		if ( edgeAttributes.get(CxEdge.INTERACTION) != null && attrName.equals("i")) {
			edgeAttributes.get(CxEdge.INTERACTION).setAlias(null);
		} 

		AspectAttributeStatEntry e = edgeAttributes.get(attrName);
		if ( e == null ) {
			e = new AspectAttributeStatEntry ();
			edgeAttributes.put(attrName, e);
		}	
		ATTRIBUTE_DATA_TYPE t = attr.getDataType();
		String error = e.addDatatype(t);
		if ( error != null) {
			throw new NdexException(constructErrMsg(attr,error) );
		}
		if ( t == ATTRIBUTE_DATA_TYPE.STRING || t== ATTRIBUTE_DATA_TYPE.BOOLEAN ) {
			e.addValue(convertAttributeValue(attr));
			
		}
	}
	
	private static String constructErrMsg(AbstractAttributesAspectElement e, String cause) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return  "Data error in " + e.getAspectName() + " object " + om.writeValueAsString(e) + 
				". Cause: " + cause;
	}
	
	public CxAttributeDeclaration createCxDeclaration() throws NdexException {
		CxAttributeDeclaration declaration = new CxAttributeDeclaration(); 
		for ( Map.Entry<String, Map<String, AspectAttributeStatEntry>> entry: table.entrySet()) {
			String aspectName = entry.getKey();
			Map<String,DeclarationEntry> attrDecls = new HashMap<>();
			for ( Map.Entry<String, AspectAttributeStatEntry> e2 : entry.getValue().entrySet()) {
				DeclarationEntry e = new DeclarationEntry ();
				AspectAttributeStatEntry stat = e2.getValue();
				e.setDataType(stat.getDataType());
				if (stat.getAlias()!=null)
					e.setAlias(stat.getAlias());
				
				// try to get default values for node/edge attributes
				if ( aspectName.equals(CxNode.ASPECT_NAME)) {
					Object d = stat.getDefaultValue(nodeCount);
					if ( d != null)
						e.setDefaultValue(d);
				} else if ( aspectName.equals(CxEdge.ASPECT_NAME)) {
					Object d = stat.getDefaultValue(edgeCount);
					if ( d != null)
						e.setDefaultValue(d);
				}
				attrDecls.put(e2.getKey(), e);
			}
			declaration.add(aspectName, attrDecls);
		}
		
		//add namespaces as a network attribute
		if ( hasNamespacesAspect) {
			Map<String,DeclarationEntry>  entries = declaration.getAttributesInAspect(CxNetworkAttribute.ASPECT_NAME);
			if( entries == null) {
				entries = new HashMap<>();
			}
			DeclarationEntry oldv = entries.put(NamespacesElement.ASPECT_NAME, 
					new DeclarationEntry(ATTRIBUTE_DATA_TYPE.STRING,null,null));
			if ( oldv != null) {
				throw new NdexException ("Invalid CX data: '@context' is definded a network attribute and an aspect.");
			}
		}
		
		return declaration;
		
	}
	
	public void addCyVisualPropertiesElement(CyVisualPropertiesElement e) {
		if (e.getProperties_of().equals("nodes"))
			nodeBypassCount++;
		else if ( e.getProperties_of().equals("edges"))
			edgeBypassCount++;
	}
	
	public boolean hasBothReservedNodeAttr() { return hasNodeName && hasNodeRep; }
	public boolean hasEdgeInteractionAttr() { return this.hasEdgeInteraction; }
	
	public int getNodeBypassCount() {return this.nodeBypassCount;}
	public int getEdgeBypassCount() {return this.edgeBypassCount;}
	
	
	/* warning: this function can only be called after attrStats is initialized */
	public List<CxMetadata> getCX2Metadata(MetaDataCollection  cx1Metadata, CxAttributeDeclaration attrDeclarations) {
		List<CxMetadata> result = new ArrayList<>(cx1Metadata.size());
				
		MetaDataElement networkAttribute = cx1Metadata.getMetaDataElement(NetworkAttributesElement.ASPECT_NAME);
		if ( networkAttribute != null ) {
			result.add(new CxMetadata (CxNetworkAttribute.ASPECT_NAME, 1L));
		}	

		MetaDataElement vpM = cx1Metadata.getMetaDataElement( CyVisualPropertiesElement.ASPECT_NAME);
		if ( vpM != null) {
			result.add(new CxMetadata(CxVisualProperty.ASPECT_NAME, 1L));
			
			//addCx2 extra aspects
			result.add(new CxMetadata(VisualEditorProperties.ASPECT_NAME, 1L));
			
			if (nodeBypassCount > 0) {
				result.add(new CxMetadata(CxNodeBypass.ASPECT_NAME, nodeBypassCount));
			}
			
			if (edgeBypassCount > 0) {
				result.add(new CxMetadata(CxEdgeBypass.ASPECT_NAME, edgeBypassCount));
			}
		}
		
		if (!attrDeclarations.getDeclarations().isEmpty()) {
			result.add(new CxMetadata(CxAttributeDeclaration.ASPECT_NAME,1L));
		}
		
		for ( MetaDataElement e: cx1Metadata) {
			String aspectName = e.getName();
			if ( !aspectName.equals(NetworkAttributesElement.ASPECT_NAME) && 
					!aspectName.equals(CyVisualPropertiesElement.ASPECT_NAME) &&
					!aspectName.equals(NodeAttributesElement.ASPECT_NAME) &&
					!aspectName.equals(EdgeAttributesElement.ASPECT_NAME) &&
					!aspectName.equals(CartesianLayoutElement.ASPECT_NAME) &&
					!aspectName.equals(Provenance.ASPECT_NAME) &&
					!aspectName.equals(NamespacesElement.ASPECT_NAME)) {
				result.add(new CxMetadata(e.getName(), e.getElementCount().longValue()));
			}
	
		}
		return result;

	}

	public static Object convertAttributeValue(AbstractAttributesAspectElement attr) throws NdexException {
		switch (attr.getDataType()) {
		case BOOLEAN: 
		case DOUBLE:
		case INTEGER:
		case LONG:
		case STRING:
			return convertSingleAttributeValue(attr.getDataType(), attr.getValue());
		case LIST_OF_BOOLEAN:
		case LIST_OF_DOUBLE:
		case LIST_OF_INTEGER:
		case LIST_OF_LONG:
		case LIST_OF_STRING:	
			List<String> ls = attr.getValues();
			ArrayList<Object> result = new ArrayList<>(ls.size());
			for ( String s : ls) {
				result.add(convertSingleAttributeValue(attr.getDataType().elementType(), s));
			}
			return result;
		default:
			throw new NdexException ("Unsupported attribute data type found: " + attr.getDataType());
		}
	}
	
	private static Object convertSingleAttributeValue(ATTRIBUTE_DATA_TYPE t, String value) throws NdexException {
		switch (t) {
		case BOOLEAN: 
			return Boolean.valueOf( value);
		case DOUBLE:
			return Double.valueOf(value);
		case INTEGER:
			return Integer.valueOf(value);
		case LONG:
			return Long.valueOf(value);
		case STRING:
			return value;
		default: 
			throw new NdexException ("Value " + value + " is not a single value type. It is " + t.toString());
		}
	}
	
   public void setHasNamespacesAspect() { this.hasNamespacesAspect = true;}

}
