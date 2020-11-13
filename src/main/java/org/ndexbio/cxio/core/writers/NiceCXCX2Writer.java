package org.ndexbio.cxio.core.writers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.ndexbio.cx2.aspect.element.core.Cx2Network;
import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxEdgeBypass;
import org.ndexbio.cx2.aspect.element.core.CxMetadata;
import org.ndexbio.cx2.aspect.element.core.CxNetworkAttribute;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cx2.aspect.element.core.CxNodeBypass;
import org.ndexbio.cx2.aspect.element.core.CxOpaqueAspectElement;
import org.ndexbio.cx2.aspect.element.core.CxVisualProperty;
import org.ndexbio.cx2.aspect.element.cytoscape.VisualEditorProperties;
import org.ndexbio.cx2.converter.AspectAttributeStat;
import org.ndexbio.cx2.converter.CX2VPHolder;
import org.ndexbio.cx2.converter.CXToCX2Converter;
import org.ndexbio.cx2.io.CXWriter;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.CartesianLayoutElement;
import org.ndexbio.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.misc.OpaqueElement;
import org.ndexbio.model.cx.CitationElement;
import org.ndexbio.model.cx.NamespacesElement;
import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.model.cx.Provenance;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NiceCXCX2Writer {
	
	private CXWriter wtr;
	
	private List<String> warnings;
	
	public NiceCXCX2Writer (OutputStream output) {
		this.wtr = new CXWriter (output,false);
		warnings = new ArrayList<>();
	}
	
	private void addWarning(String warningStr) {
		if (warnings.size() < 50 ) 
			warnings.add(warningStr);
	}
	
	
	private AspectAttributeStat analyzeAttributes(NiceCXNetwork niceCX) throws NdexException, IOException {
		
		AspectAttributeStat attributeStats = new AspectAttributeStat();
		
		boolean foundEdgeInteractionAttr = false;
		boolean foundNodeNameAttr = false;
		boolean foundNodeRepresentAttr = false;
		
		// check nodes aspect
		for ( NodesElement node : niceCX.getNodes().values()) {
			attributeStats.addNode(node);
			if ( attributeStats.hasBothReservedNodeAttr())
				break;
		}
		
		//check edges aspect
		for ( EdgesElement edge : niceCX.getEdges().values()) {
			attributeStats.addEdge(edge);
			if ( attributeStats.hasEdgeInteractionAttr())
				break;
		}
		
		//check network attribute
		for (NetworkAttributesElement netAttr : niceCX.getNetworkAttributes() ) {
			
			String warning = attributeStats.addNetworkAttribute(netAttr);
			if ( warning != null)
				addWarning(warning);
		}
		
		//check node attributes
		for ( Collection<NodeAttributesElement> nodeAttrs : niceCX.getNodeAttributes().values()) {
			for ( NodeAttributesElement attr : nodeAttrs) {
				if (attr.getName().equals(CxNode.NAME) && (!foundNodeNameAttr) ){
					String errMsg = "Attribute '" + attr.getName() + "' on node "
							+ attr.getPropertyOf() + " is not allowed in CX specification. Please upgrade your cyNDEx-2 and Cytoscape to the latest version and reload this network.";				
					addWarning ( errMsg);
					foundNodeNameAttr = true;
				} else if ( attr.getName().equals(CxNode.REPRESENTS) && !foundNodeRepresentAttr) {
					String errMsg = "Attribute '" + attr.getName() + "' on node "
							+ attr.getPropertyOf() + " is not allowed in CX specification. Please upgrade your cyNDEx-2 and Cytoscape to the latest version and reload this network.";				
					addWarning ( errMsg);
					foundNodeRepresentAttr = true;
				}
				attributeStats.addNodeAttribute(attr);
			}
		}
		
		//check edge attributes
		for ( Collection<EdgeAttributesElement> edgeAttrs : niceCX.getEdgeAttributes().values()) {
			for ( EdgeAttributesElement e : edgeAttrs) {
				if (  (e.getName().equals(CxEdge.INTERACTION) && (!foundEdgeInteractionAttr))) {
					String errMsg = "Attribute '" + e.getName() + "' on edge "
							+ e.getPropertyOf() + "' is not allowed in CX specification. Please upgrade your cyNDEx-2 and Cytoscape to the latest version and reload this network.";	
					
					addWarning (errMsg);
					foundEdgeInteractionAttr = true;
				}	
				attributeStats.addEdgeAttribute(e);
			}
		}
		
		//check node and edge bypass count
		for ( AspectElement a : niceCX.getOpaqueAspectTable().get(CyVisualPropertiesElement.ASPECT_NAME)) {
				attributeStats.addCyVisualPropertiesElement((CyVisualPropertiesElement)a);
		}
		return attributeStats;
	}
	

	public List<CxMetadata>  writeAsCX2(NiceCXNetwork niceCX) throws FileNotFoundException, IOException, NdexException {
			
		AspectAttributeStat	attrStats = analyzeAttributes(niceCX);
		
		CxAttributeDeclaration attrDeclarations = attrStats.createCxDeclaration();
		
		VisualEditorProperties visualDependencies = new VisualEditorProperties();
		
		List<CxMetadata> cx2Metadata = attrStats.getCX2Metadata(niceCX.getMetadata(), attrDeclarations);
			
		boolean hasAttributes = !attrDeclarations.getDeclarations().isEmpty();
		
		ObjectMapper om = new ObjectMapper();
			
		//prepare network attributes
		CxNetworkAttribute cx2NetAttr = new CxNetworkAttribute();
		for (NetworkAttributesElement netAttr : niceCX.getNetworkAttributes() ) {
			try {
				Object attrValue = CXToCX2Converter.convertAttributeValue(netAttr);
				Object oldV = cx2NetAttr.getAttributes().put(netAttr.getName(), attrValue);

				if (oldV !=null && !attrValue.equals(oldV)) {
					String msg = "Inconsistent network attribute value found on attribute '" + netAttr.getName()
						      + "'. It has value (" + oldV.toString() + ") and (" + netAttr.getValueAsJsonString()+")" ;	
					throw new NdexException(msg);
				}
			} catch(NumberFormatException nfe){
				String errMsg = "For network attribute '"
								+ netAttr.getName() + "' unable to convert value  to '" 
								+ netAttr.getDataType() + "' : " + nfe.getMessage();
				throw new NdexException(errMsg);
			}
		}
		
		// merge the namespace aspect if it exists
		NamespacesElement namespace = niceCX.getNamespaces();
		if ( !namespace.isEmpty()) {
			if ( cx2NetAttr.getAttributes().containsKey(NamespacesElement.ASPECT_NAME)) 
				throw new NdexException ("Redundent @context is found in CX. It is defined as an network attribute and a seperate aspect.");
			cx2NetAttr.add(NamespacesElement.ASPECT_NAME, om.writeValueAsString(namespace));
		}
		
		//write metadata first.
		wtr.writeMetadata(cx2Metadata);
			
		// write attributes declarations
		if (hasAttributes) {
			List<CxAttributeDeclaration> attrDecls = new ArrayList<>(1);
			attrDecls.add(attrDeclarations);
			wtr.writeFullAspectFragment(attrDecls);
		}
		
		//write network attribute.
		if ( !cx2NetAttr.getAttributes().isEmpty()) {
			List<CxNetworkAttribute> netAttrs = new ArrayList<>(1);
			netAttrs.add(cx2NetAttr);
			wtr.writeFullAspectFragment(netAttrs);
		}
			 
		//write nodes
		if( !niceCX.getNodes().isEmpty()) {
			Map<Long, Collection<AspectElement> > coordinatesTable = niceCX.getNodeAssociatedAspect(CartesianLayoutElement.ASPECT_NAME);

			wtr.startAspectFragment(CxNode.ASPECT_NAME);
			for ( NodesElement cx1node : niceCX.getNodes().values()) {
				Long nodeId = Long.valueOf(cx1node.getId());
				CxNode newNode = new CxNode(nodeId);
										
				if ( cx1node.getNodeName() != null) {
					NodeAttributesElement attr = new NodeAttributesElement(nodeId, CxNode.NAME, cx1node.getNodeName(), ATTRIBUTE_DATA_TYPE.STRING);	
					String warning = newNode.addCX1NodeAttribute(attr, attrDeclarations);
					if ( warning != null)
						addWarning(warning);
				}   
				if (cx1node.getNodeRepresents() != null) {
					NodeAttributesElement attr = new NodeAttributesElement(nodeId, CxNode.REPRESENTS, cx1node.getNodeRepresents(), ATTRIBUTE_DATA_TYPE.STRING);	
					String warning = newNode.addCX1NodeAttribute(attr, attrDeclarations);
					if ( warning != null)
						addWarning(warning);
				}    

				// add attributes to CX2Node
				if ( niceCX.getNodeAttributes().get(nodeId) != null) {
					for (NodeAttributesElement cx1nodeAttr : niceCX.getNodeAttributes().get(nodeId)) {
						try {
							String warning = newNode.addCX1NodeAttribute(cx1nodeAttr, attrDeclarations);
							if ( warning != null) {
								addWarning(warning);
							}
						} catch (NumberFormatException e2) {
							String errMsg = "For node attribute id: "
									+ cx1nodeAttr.getPropertyOf()
									+ " with name '" + cx1nodeAttr.getName()
									+ "' received fatal parsing error: " + e2.getMessage();
							throw new NdexException (errMsg);
						}
					}
				}
				
				//add coordinates to CX2Node
				if ( coordinatesTable != null) {
					Collection<AspectElement> coords = coordinatesTable.get(nodeId);
					if (coords.size()!= 1)
						throw new NdexException ("Node " + nodeId + " has " + coords.size() + " CartesianLayoutElement." );
					for ( AspectElement e : coords) {
						CartesianLayoutElement coord = (CartesianLayoutElement)e;
						newNode.setX(coord.getX());
						newNode.setY(coord.getY());
						newNode.setZ(coord.getZ());
					}	
				}

				wtr.writeElementInFragment(newNode);
			}
			wtr.endAspectFragment();
		}
			
		//write edges
		if ( !niceCX.getEdges().isEmpty()) {
			wtr.startAspectFragment(CxEdge.ASPECT_NAME);
			for(EdgesElement cx1Edge : niceCX.getEdges().values()) { 
				CxEdge cx2Edge = new CxEdge(cx1Edge.getId(),cx1Edge.getSource(),cx1Edge.getTarget());
				if ( cx1Edge.getInteraction() != null) {
					EdgeAttributesElement attr = new EdgeAttributesElement(cx1Edge.getId(), CxEdge.INTERACTION, cx1Edge.getInteraction(), ATTRIBUTE_DATA_TYPE.STRING);	
					try {
						String warning = cx2Edge.addCX1EdgeAttribute(attr, attrDeclarations);
						if (warning != null)
							addWarning(warning);
					} catch (NumberFormatException e2) {
						throw new NdexException (e2.getMessage());
					}
				}
				
				//add edgeAttribute
				if ( niceCX.getEdgeAttributes().get(cx1Edge.getId()) != null) {
					for ( EdgeAttributesElement cx1EdgeAttr : niceCX.getEdgeAttributes().get(cx1Edge.getId())) {
						try {
							String warning = cx2Edge.addCX1EdgeAttribute(cx1EdgeAttr,attrDeclarations);
							if ( warning != null )
								addWarning(warning);
						} catch (NumberFormatException e2) {
							// special case to ignore nulls
							
						/*	if ( cx1EdgeAttr.isSingleValue() && cx1EdgeAttr.getValue().toLowerCase().equals("null")) {
								addWarning("Edge attribute '" + cx1EdgeAttr.getName() + "' on edge " +
										cx1EdgeAttr.getPropertyOf() + " is " + cx1EdgeAttr.getValue() + ". It will be ignored." );
								continue;
							} */	
							String errMsg = "For edge attribute id: "
									+ cx1EdgeAttr.getPropertyOf()
									+ " with name '" + cx1EdgeAttr.getName()
									+ "' received fatal parsing error: " + e2.getMessage();
							throw new NdexException (errMsg);
						}
					} 
				}
				wtr.writeElementInFragment(cx2Edge);
			}		
							
			wtr.endAspectFragment();		
		}
			
			
		// write visualProperites
		CX2VPHolder vp = readVisualProperties(niceCX, visualDependencies,warnings);
						
		if ( !vp.getStyle().isEmpty()) {
				wtr.startAspectFragment(CxVisualProperty.ASPECT_NAME);
				wtr.writeElementInFragment(vp.getStyle());
				wtr.endAspectFragment();
		}
						
		if ( !vp.getNodeBypasses().isEmpty()) {
			wtr.startAspectFragment(CxNodeBypass.ASPECT_NAME);
			for ( CxNodeBypass e : vp.getNodeBypasses()) {
				wtr.writeElementInFragment(e);
			}
			wtr.endAspectFragment();
		}
			
		if ( !vp.getEdgeBypasses().isEmpty()) {
			wtr.startAspectFragment(CxEdgeBypass.ASPECT_NAME);
			for ( CxEdgeBypass e : vp.getEdgeBypasses()) {
				wtr.writeElementInFragment(e);
			}
				
			wtr.endAspectFragment();
		}
			
			// write visualDependencies
		if ( !visualDependencies.getProperties().isEmpty()) {
				wtr.startAspectFragment(VisualEditorProperties.ASPECT_NAME);
				wtr.writeElementInFragment(visualDependencies);
				wtr.endAspectFragment();
		}			
			
		//write possible opaque aspects
		for ( CxMetadata m : cx2Metadata) {
			String aspectName = m.getName();
			if (! Cx2Network.cx2SpecialAspects.contains(aspectName) && 
					   !aspectName.equals(CxNode.ASPECT_NAME) && !aspectName.equals(CxEdge.ASPECT_NAME)
					   && !aspectName.equals(CxVisualProperty.ASPECT_NAME)) {
			    if ( aspectName.equals(Provenance.ASPECT_NAME ) ) {
						wtr.startAspectFragment(aspectName);
						CxOpaqueAspectElement e = om.convertValue(niceCX.getProvenance(), CxOpaqueAspectElement.class);
						e.setAspectName(aspectName);
						wtr.writeElementInFragment(e);
						wtr.endAspectFragment();
			    } else if ( aspectName.equals(CitationElement.ASPECT_NAME) ) {
			    		wtr.startAspectFragment(aspectName);
			    		for ( CitationElement citation : niceCX.getCitations().values()) {
			    			CxOpaqueAspectElement e = om.convertValue(citation, CxOpaqueAspectElement.class);
			    			e.setAspectName(aspectName);
			    			wtr.writeElementInFragment(e);
			    		}
						wtr.endAspectFragment();
			    	
			    } else if ( niceCX.getOpaqueAspectTable().containsKey(aspectName)) {

						wtr.startAspectFragment(aspectName);
						for (AspectElement oe : niceCX.getOpaqueAspectTable().get(aspectName) ) {
							OpaqueElement elmt = (OpaqueElement)oe;
							CxOpaqueAspectElement e = om.convertValue(elmt.getData(), CxOpaqueAspectElement.class);
							e.setAspectName(aspectName);
							wtr.writeElementInFragment(e);
						}
						wtr.endAspectFragment();
				} else 
						throw new NdexException ("Aspect " + aspectName + " not found in the opaque aspect table.");
					
			}
		}
			wtr.finish();
		
		
		return cx2Metadata;
		
	}
	
	private static CX2VPHolder readVisualProperties(NiceCXNetwork niceCX, VisualEditorProperties visualDependencies,
			List<String> warningHolder) throws JsonProcessingException, IOException, NdexException {
		CX2VPHolder holder = new CX2VPHolder ();
		
		if ( niceCX.getOpaqueAspectTable().get(CyVisualPropertiesElement.ASPECT_NAME) != null) {
			for ( AspectElement elmt : niceCX.getOpaqueAspectTable().get(CyVisualPropertiesElement.ASPECT_NAME)) {
				holder.addVisuaProperty((CyVisualPropertiesElement)elmt, visualDependencies, warningHolder);
			}
		}
		return holder;
	}

}
