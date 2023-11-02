package org.ndexbio.cx2.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxEdgeBypass;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cx2.aspect.element.core.CxNodeBypass;
import org.ndexbio.cx2.aspect.element.core.CxVisualProperty;
import org.ndexbio.cx2.aspect.element.core.DeclarationEntry;
import org.ndexbio.cx2.aspect.element.core.MappingDefinition;
import org.ndexbio.cx2.aspect.element.core.VPMappingType;
import org.ndexbio.cx2.aspect.element.core.VisualPropertyMapping;
import org.ndexbio.cx2.aspect.element.core.VisualPropertyTable;
import org.ndexbio.cx2.aspect.element.cytoscape.VisualEditorProperties;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.ndexbio.cxio.aspects.datamodels.Mapping;
import org.ndexbio.cxio.core.writers.NiceCXCX2Writer;
import org.ndexbio.model.exceptions.NdexException;

public class CX2VPHolder {
	
	
//	private List<String> warnings;
	
	private static final int  maximumNumberWarningMessages = 50; 
	
	private CxVisualProperty style; 
	
	private List<CxNodeBypass> nodeBypasses; 
	private List<CxEdgeBypass> edgeBypasses;   

	public CX2VPHolder () {
		setStyle(new CxVisualProperty());
		
		setNodeBypasses(new ArrayList<>());
		setEdgeBypasses(new ArrayList<>());  
//		warnings = new ArrayList<>(maximumNumberWarningMessages);
	}

	public CxVisualProperty getStyle() {
		return style;
	}

	public void setStyle(CxVisualProperty style) {
		this.style = style;
	}

	public List<CxNodeBypass> getNodeBypasses() {
		return nodeBypasses;
	}

	public void setNodeBypasses(List<CxNodeBypass> nodeBypasses) {
		this.nodeBypasses = nodeBypasses;
	}

	public List<CxEdgeBypass> getEdgeBypasses() {
		return edgeBypasses;
	}

	public void setEdgeBypasses(List<CxEdgeBypass> edgeBypasses) {
		this.edgeBypasses = edgeBypasses;
	}
	
	
	
    /**
     * Adds {@code warningStr} to internal {@code warnings} variable 
     * unless there are more then 20 entries in list in which case
     * method just returns
     * @param warningStr warning message to add to warnings list
     */
	private static void addWarning(List<String> warningHolder, String warningStr) {
		if (warningHolder.size() >= maximumNumberWarningMessages)
			return;
		
		warningHolder.add(NiceCXCX2Writer.messagePrefix + warningStr);		
	}

	private static void addWarning(List<String> warningHolder, final ConverterUtilitiesResult cRes) {
	    if (cRes == null) {
            return;
        }
        List<String> warnList = cRes.getWarnings();
        if (warnList == null) {
            return;
        }
        for (String warning : warnList) {
            addWarning(warningHolder, NiceCXCX2Writer.messagePrefix + warning);
        }
	}
	
	
	private static void processEditorProperties(Map<String,String> cx1Properties, VisualEditorProperties visualDependencies) {
		for (Map.Entry<String,String> entry: cx1Properties.entrySet()) {
			String propName = entry.getKey();
			if ( propName.equals("NETWORK_CENTER_X_LOCATION") ||
					propName.equals("NETWORK_CENTER_Y_LOCATION") || 
					propName.equals("NETWORK_SCALE_FACTOR")) {
				visualDependencies.getProperties().put(propName, 
						Float.valueOf(entry.getValue()));
			} 
		}
	}

	public void addVisuaProperty(CyVisualPropertiesElement elmt,
			   VisualEditorProperties visualDependencies, List<String> warningHolder, CxAttributeDeclaration attrDeclarations) throws NdexException, IOException {
	    	
		String po = elmt.getProperties_of();
		    
		CXToCX2VisualPropertyConverter vpConverter = CXToCX2VisualPropertyConverter.getInstance();
	    if ( po.equals("network")) {
				processEditorProperties(elmt.getProperties(), visualDependencies);

	    		style.getDefaultProps().setNetworkProperties(vpConverter.convertNetworkVPs(elmt.getProperties()));
	    } else if( po.equals("nodes:default")) {

	       		// get dependencies
	    		String nodeSizeLockedStr = elmt.getDependencies().get("nodeSizeLocked");
	    	    		
	    		boolean nodeSizeLocked = ( nodeSizeLockedStr != null && nodeSizeLockedStr.equals("true"));   	
	    		
	    		visualDependencies.getProperties().put("nodeSizeLocked", Boolean.valueOf(nodeSizeLockedStr) );
	    		visualDependencies.getProperties().put("nodeCustomGraphicsSizeSync", 
	    				Boolean.valueOf(elmt.getDependencies().get("nodeCustomGraphicsSizeSync")) );

	    		Map<String,String> cx1Properties = elmt.getProperties();
	    		if ( nodeSizeLocked) {
	    			String size = cx1Properties.get("NODE_SIZE");
	    			if ( size != null) {
	    				//TODO: need to check if we need to give a warning about upgrading cyNDEx2.
	    				cx1Properties.put("NODE_WIDTH", size);
	    				cx1Properties.put("NODE_HEIGHT", size);
	    			}
	    		}
	    		style.getDefaultProps().setNodeProperties(vpConverter.convertEdgeOrNodeVPs(cx1Properties));
	    		
	    		// add mapping
	    		SortedMap<String,Mapping> nodeMappings = elmt.getMappings();
	    		
	    		if ( nodeSizeLocked ) {
	       			Mapping sizeMapping = nodeMappings.remove("NODE_SIZE");
	       			if ( sizeMapping != null ) {
	       				nodeMappings.put("NODE_WIDTH", sizeMapping);
	       				nodeMappings.put("NODE_HEIGHT", sizeMapping);
	       			}
	    		}
	    		
	    		if ( nodeMappings != null && !nodeMappings.isEmpty()) {
	    			processMappingEntry(nodeMappings, style.getNodeMappings(),warningHolder, attrDeclarations);
	    		}
	    		
	    } else if ( po.equals("edges:default")) {
	      		//Map<String, Object> defaultVp = getDefaultVP (style);
	    		      		
	    		SortedMap<String,String> cx1Properties = elmt.getProperties();
	    		
	    		visualDependencies.getProperties().putAll(elmt.getDependencies());
	    		
	    		// add dependencies
	    		String arrowColorMatchesEdgeStr = elmt.getDependencies().get("arrowColorMatchesEdge");

	    		visualDependencies.getProperties().put("arrowColorMatchesEdge", Boolean.valueOf(arrowColorMatchesEdgeStr) );
	    		
	    		boolean arrowColorMatchesEdge = (arrowColorMatchesEdgeStr != null && arrowColorMatchesEdgeStr.equals("true"));

	    		if ( arrowColorMatchesEdge ) {
	    			String ep = cx1Properties.get("EDGE_UNSELECTED_PAINT");
	    			cx1Properties.put("EDGE_SOURCE_ARROW_UNSELECTED_PAINT", ep);
	    			cx1Properties.put("EDGE_STROKE_UNSELECTED_PAINT", ep);
	    			cx1Properties.put("EDGE_TARGET_ARROW_UNSELECTED_PAINT", ep);
	    		}

	    		style.getDefaultProps().setEdgeProperties(vpConverter.convertEdgeOrNodeVPs(cx1Properties));
	    		
	    		// add mapping
	    		SortedMap<String,Mapping> edgeMappings = elmt.getMappings();
	    		
	    		if ( arrowColorMatchesEdge) {
	    			Mapping m = edgeMappings.remove("EDGE_UNSELECTED_PAINT");
	    			if ( m !=null) {
	    				edgeMappings.put("EDGE_SOURCE_ARROW_UNSELECTED_PAINT", m);
	    				edgeMappings.put("EDGE_STROKE_UNSELECTED_PAINT", m);
	    				edgeMappings.put("EDGE_TARGET_ARROW_UNSELECTED_PAINT", m);
	    			}
	    		}	
	    		
	    		if ( edgeMappings != null && !edgeMappings.isEmpty()) {
	    			processMappingEntry(edgeMappings, style.getEdgeMappings(),warningHolder, attrDeclarations);
	    		}
	    		
	    	} else if ( po.equals("nodes")) {  //node bypasses
	    		
	    		SortedMap<String,String> vps = elmt.getProperties();
	    		
	    		Boolean nodeSizeLocked = (Boolean)visualDependencies.getProperties().get("nodeSizeLocked");
	    		if( nodeSizeLocked.booleanValue()) {
	    			String nsize = vps.remove("NODE_SIZE");
	    			if ( nsize != null) {
	    				vps.put("NODE_WIDTH", nsize);
	    				vps.put("NODE_HEIGHT",nsize);
	    			}
	    		}
	    		
	    		CxNodeBypass nodebp = new CxNodeBypass(elmt.getApplies_to().longValue(), 
	    				vpConverter.convertEdgeOrNodeVPs(elmt.getProperties()));
	    		
	    		if ( !nodebp.getVisualProperties().getVisualProperties().isEmpty())
	    			nodeBypasses.add(nodebp);
	    	} else if ( po.equals("edges")) {  // edge bypasses
	    		VisualPropertyTable v = vpConverter.convertEdgeOrNodeVPs(elmt.getProperties());
	    		if ( !v.getVisualProperties().isEmpty()) {
	    			CxEdgeBypass edgebp = new CxEdgeBypass();
	    			edgebp.setId(elmt.getApplies_to().longValue());
	    			edgebp.setVisualProperties(v);
	    			edgeBypasses.add(edgebp);
	    		}
	    	} else {
	    		throw new NdexException ("'" + po + "' is not a supported Cytoscape visual property group. Please upgrade your cyNDEx-2 app to the latest version in Cytoscape and try again.");
	    	}
	    }

	
	/**
	 * 
	 * @param cx1Mappings
	 * @param v2Mappings
	 * @return A list of String that contains warnings from the conversion.
	 * @throws NdexException
	 * @throws IOException
	 */
	private static void processMappingEntry(SortedMap<String, Mapping> cx1Mappings,
			Map<String, VisualPropertyMapping> v2Mappings, List<String> warningHolder, CxAttributeDeclaration attrDeclarations) throws NdexException, IOException {
		
		CXToCX2VisualPropertyConverter vpConverter = CXToCX2VisualPropertyConverter.getInstance();
		
		for (Map.Entry<String, Mapping> entry : cx1Mappings.entrySet()) {
			String vpName = entry.getKey();
			String newVPName = vpConverter.getNewEdgeOrNodeProperty(vpName);
			if (newVPName == null)
				continue;

			VisualPropertyMapping mappingObj = new VisualPropertyMapping();
			String mappingType = entry.getValue().getType();
			try {
				mappingObj.setType(VPMappingType.valueOf(mappingType));
			} catch ( IllegalArgumentException e) {
				throw new NdexException ("Invalid mapping type '" + mappingType + "' found on visual property '" +vpName +"'.");
			}
			MappingDefinition defObj = new MappingDefinition();
			mappingObj.setMappingDef(defObj);
			String defString = entry.getValue().getDefinition();
			try {
				if (mappingType.equals("PASSTHROUGH")) {
					String[] nameNType = ConverterUtilities.getPassThroughMappingAttribute(defString);
					defObj.setAttributeName(nameNType[0]);
					defObj.setAttributeType(ATTRIBUTE_DATA_TYPE.fromCxLabel(nameNType[1]));
				} else if (mappingType.equals("DISCRETE")) {
					List<Map<String, Object>> m = new ArrayList<>();
					try {
						MappingValueStringParser sp = new MappingValueStringParser(defString);

						String col = sp.get("COL");
						String t = sp.get("T");
						int counter = 0;
						while (true) {
							final String k = sp.get("K=" + counter);
							if (k == null) {
								break;
							}
							final String v = sp.get("V=" + counter);

							if (v == null) {
								throw new NdexException(
										"error: discrete mapping string is corruptted for " + defString);
							}

							Map<String, Object> mapEntry = new HashMap<>(2);
							ConverterUtilitiesResult cRes = ConverterUtilities.cvtStringValueToObj(t, k);
							
				            addWarning(warningHolder, cRes);
							
							mapEntry.put("v", cRes.getResult());
							mapEntry.put("vp", vpConverter.getNewEdgeOrNodePropertyValue(vpName, v));
							m.add(mapEntry);
							counter++;
						}

						defObj.setAttributeName(col);
						defObj.setAttributeType(ATTRIBUTE_DATA_TYPE.fromCxLabel(t));
						defObj.setMapppingList(m);
					} catch (IOException e) {
						addWarning( warningHolder,
								"Corrupted data found in DISCRETE mapping on " + vpName +
								". Please upgrade your cyNDEx-2 and Cytoscape to the latest version and reload this network. Cause: " + e.getMessage() );
						System.err.println(e.getMessage());
						continue;
					}

				} else { // continuous mapping
					List<Map<String, Object>> m = new ArrayList<>();
					MappingValueStringParser sp = new MappingValueStringParser(defString);
					String col = sp.get("COL");
					
					String t = sp.get("T");

					Object min = null;
					Boolean includeMin = null;
					// Object max = null;
					Object minVP = null;
					// Object maxVP = null;

					int counter = 0;
					Map<String, Object> currentMapping = new HashMap<>();

					while (true) {
						final String L = sp.get("L=" + counter);
						if (L == null) {
							break;
						}
						Object LO = vpConverter.getNewEdgeOrNodePropertyValue(vpName, L);

						final String E = sp.get("E=" + counter);
						if (E == null) {
							break;
						}
						// Object EO = vpConverter.getNewEdgeOrNodePropertyValue(vpName,E);

						final String G = sp.get("G=" + counter);
						if (G == null) {
							break;
						}
						Object GO = vpConverter.getNewEdgeOrNodePropertyValue(vpName, G);

						final String OV = sp.get("OV=" + counter);
						if (OV == null) {
							throw new NdexException(
									"error: continuous mapping string is corruptted for " + defString);
						}
						ConverterUtilitiesResult cRes = ConverterUtilities.cvtStringValueToObj("double", OV);
						addWarning(warningHolder, cRes);
						
						Object OVO = cRes.getResult();

						if (counter == 0) { // min side
							currentMapping.put("includeMin", Boolean.FALSE);
							currentMapping.put("includeMax", Boolean.valueOf(E.equals(L)));
							currentMapping.put("maxVPValue", LO);
							currentMapping.put("max", OVO);
							m.add(currentMapping);

						} else {
							currentMapping.put("includeMin", includeMin);
							currentMapping.put("includeMax", Boolean.valueOf(E.equals(L)));
							currentMapping.put("minVPValue", minVP);
							currentMapping.put("min", min);
							currentMapping.put("maxVPValue", LO);
							currentMapping.put("max", OVO);
							m.add(currentMapping);
						}

						// store the max values as min for the next segment
						includeMin = Boolean.valueOf(E.equals(G));

						min = OVO;
						minVP = GO;

						currentMapping = new HashMap<>();
						counter++;
					}

					// add the last entry
					currentMapping.put("includeMin", includeMin);
					currentMapping.put("includeMax", Boolean.FALSE);
					currentMapping.put("minVPValue", minVP);
					currentMapping.put("min", min);
					m.add(currentMapping);

					// add the list
					defObj.setAttributeName(col);
					defObj.setAttributeType(ATTRIBUTE_DATA_TYPE.fromCxLabel(t));
					defObj.setMapppingList(m);
				}
			} catch (NdexException e) {
				throw new NdexException(
						"Can't converter " + mappingType + " mapping on " + vpName + ". Cause: " + e.getMessage());
			}
			if ( mappingObj.getType() == VPMappingType.PASSTHROUGH || 
					mappingObj.getMappingDef().getMapppingList().size()>0) {
			    MappingDefinition def = mappingObj.getMappingDef();
			    
			    String aspectName = newVPName.startsWith("NODE_")? CxNode.ASPECT_NAME: CxEdge.ASPECT_NAME;
			    
			    ATTRIBUTE_DATA_TYPE t0 = getDelcaredAttributeType(attrDeclarations, aspectName , def.getAttributeName());
			    if ( t0 == null)
			    	addWarning(warningHolder, newVPName + " " + mappingObj.getType() + " mapping references nonexistent attribute '" + def.getAttributeName() 
			    	   + "' in " + aspectName + ".");
			    else if ( t0 != def.getAttributeType()) {
			    	if ((!t0.isSingleValueType()) && t0.elementType() == def.getAttributeType() ) { // handle list as a special case.
			    			def.setAttributeType(t0);
			    	} else 
			    		throw new NdexException ("Data type error on '" + def.getAttributeName() + "' attribute: declared as " + def.getAttributeType() +
			    			" in visual style mapping (" + newVPName + ") but found as " + t0 + " in the attribute value."); 
			    }
				v2Mappings.put(newVPName, mappingObj);
			}   
		}
	}
	
	private static ATTRIBUTE_DATA_TYPE getDelcaredAttributeType ( CxAttributeDeclaration attrDeclarations, String aspectName, String attrName) {
		Map<String,DeclarationEntry> n = attrDeclarations.getAttributesInAspect(aspectName);
		if ( n != null ) {
			DeclarationEntry e = n.get(attrName);
			if (e != null)
				return e.getDataType();
		}		
		return null;
	}
	

}
