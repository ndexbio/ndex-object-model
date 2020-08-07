package org.ndexbio.cx2.converter;

import java.util.HashMap;
import java.util.Map;

import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxNetworkAttribute;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cx2.aspect.element.core.DeclarationEntry;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.model.exceptions.NdexException;

public class AspectAttributeStat {
	
	private Map<String, Map<String, AspectAttributeStatEntry>> table;
	
	// internal state controls for performance.
	private boolean hasNodeName;
	private boolean hasNodeRep;
	private boolean hasEdgeInteraction;
	
	public AspectAttributeStat() {
		
		table = new HashMap<> ();
		hasNodeName = false;
		hasNodeRep = false;
		hasEdgeInteraction = false;
	}
	
	
	public void addNode (NodesElement n ) throws NdexException {
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
		e.addDatatype(ATTRIBUTE_DATA_TYPE.STRING);
		if ( alias != null)
			e.setAlias(alias);
		if (value != null)
			e.addValue(value);
		attributes.put (attrName,e);
	}

	public void addNodeAttribute (NodeAttributesElement attr) throws NdexException {
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
		e.addDatatype(t);
		if ( t == ATTRIBUTE_DATA_TYPE.STRING || t == ATTRIBUTE_DATA_TYPE.BOOLEAN) {
			e.addValue(CXToCX2Converter.convertAttributeValue(attr));
			
		}
	}
	
	public void addNetworkAttribute (NetworkAttributesElement attr) throws NdexException {
		String attrName = attr.getName();
		
		Map<String, AspectAttributeStatEntry> nodeAttributes = table.get(CxNetworkAttribute.ASPECT_NAME);
		
		if ( nodeAttributes == null) {
			nodeAttributes = new HashMap<>();
			table.put(CxNetworkAttribute.ASPECT_NAME,nodeAttributes);
		}	

		AspectAttributeStatEntry e = nodeAttributes.get(attrName);
		if ( e != null ) 
			throw new NdexException("Duplicated network attribute '" + attrName + "' found." );	
		e = new AspectAttributeStatEntry ();
		e.addDatatype(attr.getDataType());
		nodeAttributes.put(attrName, e);
	}
	
	public void addEdgeAttribute (EdgeAttributesElement attr) throws NdexException {
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
		e.addDatatype(t);
		if ( t == ATTRIBUTE_DATA_TYPE.STRING || t== ATTRIBUTE_DATA_TYPE.BOOLEAN ) {
			e.addValue(CXToCX2Converter.convertAttributeValue(attr));
			
		}
	}
	
	public CxAttributeDeclaration createCxDeclaration() {
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
				if ( stat.getDefaultValue()!=null)
					e.setDefaultValue(stat.getDefaultValue());
				attrDecls.put(e2.getKey(), e);
			}
			declaration.add(aspectName, attrDecls);
		}
		
		return declaration;
		
	}
	
	public boolean hasBothReservedNodeAttr() { return hasNodeName && hasNodeRep; }
	public boolean hasEdgeInteractionAttr() { return this.hasEdgeInteraction; }

}
