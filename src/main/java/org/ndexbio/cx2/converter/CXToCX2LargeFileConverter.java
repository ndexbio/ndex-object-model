package org.ndexbio.cx2.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

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
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.ndexbio.cxio.aspects.datamodels.CartesianLayoutElement;
import org.ndexbio.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.aspects.datamodels.Mapping;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodeAttributesElement;
import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.aspects.readers.CartesianLayoutFragmentReader;
import org.ndexbio.cxio.aspects.readers.CyVisualPropertiesFragmentReader;
import org.ndexbio.cxio.aspects.readers.EdgeAttributesFragmentReader;
import org.ndexbio.cxio.aspects.readers.EdgesFragmentReader;
import org.ndexbio.cxio.aspects.readers.GeneralAspectFragmentReader;
import org.ndexbio.cxio.aspects.readers.NetworkAttributesFragmentReader;
import org.ndexbio.cxio.aspects.readers.NodeAttributesFragmentReader;
import org.ndexbio.cxio.aspects.readers.NodesFragmentReader;
import org.ndexbio.cxio.core.CxElementReader2;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.core.interfaces.AspectFragmentReader;
import org.ndexbio.cxio.metadata.MetaDataCollection;
import org.ndexbio.cxio.metadata.MetaDataElement;
import org.ndexbio.cxio.misc.OpaqueElement;
import org.ndexbio.model.cx.CitationElement;
import org.ndexbio.model.cx.NdexNetworkStatus;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintStream;


/**
 * This class is not thread safe.
 * @author jingchen
 *
 */
public class CXToCX2LargeFileConverter {
	
	private Set<AspectFragmentReader> _readers;
	File cxFile;
	String newFileName;

	private CxAttributeDeclaration attrDeclarations;
	
	//long nodeIdCounter;
	long edgeIdCounter;

	Map<String, Object> netAttributes;

	MetaDataCollection metadata;
	
	
	Map<String, List<OpaqueElement>> opaqueAspectTable;
	
	Long currentEdgeId;  // tmp variable for printing edge attributes
	
	byte[] elmtDivider;

	CXToCX2VisualPropertyConverter vpConverter;
	
	private long edgeCounter;  // counter for how many edges have been written so far.  
	
	private Map<String, Object> visualDependencies; 
	
	private static final Boolean hasFragments = Boolean.FALSE;
	
	private final static String CXVersion = "2.0"; 
	
	/**
	 * 
	 * @param filePath
	 * @param configJsonFilePath
	 * @param newFileName        new file name without the cx suffix
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public CXToCX2LargeFileConverter(String filePath, String configJsonFilePath, String newFileName)
			throws JsonParseException, JsonMappingException, IOException {
		cxFile = new File(filePath);
		ObjectMapper mapper = new ObjectMapper();
		
		if (configJsonFilePath != null) {
			attrDeclarations = mapper.readValue(new File(configJsonFilePath), 
					CxAttributeDeclaration.class);
		} else
			attrDeclarations = null;
		
		createReaders();
		this.newFileName = newFileName;

		//nodeIdCounter = 0;
		edgeIdCounter = 0;
		netAttributes = new HashMap<>();
		opaqueAspectTable = new HashMap<>();
		
		vpConverter = CXToCX2VisualPropertyConverter.getInstance();
		
		visualDependencies = new HashMap<> ();
		
		//elmtDivider = ",".getBytes();
		elmtDivider = ",\n".getBytes();
		System.out.println("Converting " + filePath + " to " +  newFileName);
	}

        
        /**
         * Prints any warnings encountered in {@code cRes} to {@code PrintStream}
         * passed in
         * 
         */
        private void outputAnyConverterUtilitiesWarnings(PrintStream outStream,
                ConverterUtilitiesResult cRes){
           if (cRes == null || cRes.hasWarnings() == false){
               return;
           } 
           for (String s : cRes.getWarnings()){
               if (s == null){
                   continue;
               }
               outStream.println(s);
           }
        }
	private void createReaders() {
		_readers = new HashSet<>(23);

		_readers.add(EdgesFragmentReader.createInstance());
		_readers.add(EdgeAttributesFragmentReader.createInstance());
		_readers.add(NetworkAttributesFragmentReader.createInstance());
		_readers.add(NodesFragmentReader.createInstance());
		_readers.add(NodeAttributesFragmentReader.createInstance());

		_readers.add(new GeneralAspectFragmentReader<>(NdexNetworkStatus.ASPECT_NAME, NdexNetworkStatus.class));
        _readers.add( CyVisualPropertiesFragmentReader.createInstance());
		_readers.add(CartesianLayoutFragmentReader.createInstance());

	}

	public void convert() throws NdexException, IOException {

		JsonFactory factory = new JsonFactory();
		factory.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
		ObjectMapper mapper = new ObjectMapper(factory);
		currentEdgeId = null;
		edgeCounter = 0;
		Map<String, Object> descriptor = new HashMap<>();
		
		if ( attrDeclarations == null) {
			AspectAttributeStat attrStats = analyzeAttributes();
			attrDeclarations = attrStats.createCxDeclaration();
		}
		
		try ( FileOutputStream out = new FileOutputStream(newFileName) ) {
			
			//write start
			out.write("[".getBytes());
			out.flush();
					
			descriptor.put("CXVersion", CXVersion);
			descriptor.put("hasFragments", hasFragments);
			mapper.writeValue(out, descriptor);
			
			out.flush();
			out.write(",\n".getBytes());
			out.flush();

			//read the nodes first so that metadata can also be populated.
			Map<Long, Map<String, Object>> nodes = readNodes();

			// write metadata
			//TODO: cleanup the old deprecated aspects in metadata
			mapper.writeValue(out, metadata);
			out.write(",\n".getBytes());
			out.flush();

			//write attributeDeclarations
			if (!attrDeclarations.getDeclarations().isEmpty()) {
				out.write(("{\"" + CxAttributeDeclaration.ASPECT_NAME + "\":[ ").getBytes());
				mapper.writeValue (out, attrDeclarations);
				out.write("]},\n".getBytes());
				out.flush();
			}
			
			//write network attributes 
			
			out.write("{\"networkAttributes\":[".getBytes());
			mapper.writeValue (out, this.netAttributes);
			
			out.write("]},\n".getBytes());
			out.flush();
			
			// writing nodes
			out.write(("{\"" + CxNode.ASPECT_NAME + "\":[").getBytes());
			
			long nodeCnt = 0 ;
			
			for(Map<String,Object> entry : nodes.values()) {
				if( nodeCnt != 0 )
				    out.write(elmtDivider);
				mapper.writeValue(out, entry);
				nodeCnt ++;	
			}
			
			out.write("]},\n".getBytes());
			out.flush();
		
			System.out.println("Finished writing nodes.");
			
			nodes = null;  // kick in gc.
			
			System.out.println("Finished writing node attributes.");

			//write edges;
			
			Map<Long, EdgesElement> edges = readEdges();
			
			System.out.println("Finished reading edges.");

			//write edge and attributes
			out.write("{\"edges\":[".getBytes());

			writeEdgeAttributes(edges, out, mapper);

						
			out.write("]},\n".getBytes());
			out.flush();
			
			System.out.println("Finished writing edges;"); 

			// write visualProperites
			
			Map<String,Object> vp = readVisualProperties();
			
			if ( vp.get(CxVisualProperty.ASPECT_NAME) != null) {
				out.write("{\"visualProperties\":[".getBytes());

				mapper.writeValue (out, vp.get(CxVisualProperty.ASPECT_NAME));

				out.write("]},\n".getBytes());
				out.flush();
			}
			
			if ( vp.get(CxNodeBypass.ASPECT_NAME)!=null) {
				out.write(("{\"" + CxNodeBypass.ASPECT_NAME + "\":[").getBytes());
				int cnt = 0;
				List<CxNodeBypass> bypasses = (List<CxNodeBypass>)vp.get(CxNodeBypass.ASPECT_NAME);
				for ( CxNodeBypass e : bypasses) {
				    if( cnt != 0 )
					    out.write(elmtDivider);
				    cnt ++;
					mapper.writeValue (out, e);
				}
				out.write("]},\n".getBytes());
				out.flush();
			}
			
			if ( vp.get(CxEdgeBypass.ASPECT_NAME)!=null) {
				out.write(("{\"" + CxEdgeBypass.ASPECT_NAME + "\":[").getBytes());
				int cnt = 0;
				List<CxEdgeBypass> bypasses = (List<CxEdgeBypass>)vp.get(CxEdgeBypass.ASPECT_NAME);
				for ( CxEdgeBypass e : bypasses) {
				    if( cnt != 0 )
					    out.write(elmtDivider);
				    cnt++;
					mapper.writeValue (out, e);
				}
				out.write("]},\n".getBytes());
				out.flush();
			}
			
			// write visualDependencies
			if ( !this.visualDependencies.isEmpty()) {
				out.write("{\"visualEditorProperties\":[ {\"properties\": ".getBytes());

				mapper.writeValue (out, visualDependencies);

				out.write("}]},\n".getBytes());
				out.flush();
			}
			
			//write possible opaque aspects
			readOpaqueAspects();
			
			for (Map.Entry<String, List<OpaqueElement>> entry : opaqueAspectTable.entrySet()) {
				out.write(("{\"" + entry.getKey()+ "\":[").getBytes());

				int i = 0 ;	
				for ( OpaqueElement e: entry.getValue()) {
					mapper.writeValue(out, e.getData());
					i++;
					if ( i < entry.getValue().size())
						out.write(',');
				}
				out.write("]},\n".getBytes());
				out.flush();
			}
			
			//write the end
			out.write("{\"status\":[{\"success\":true}]}]\n".getBytes());
			out.flush();
		}   
		
	}
	
	private AspectAttributeStat analyzeAttributes() throws NdexException, IOException {
		
		AspectAttributeStat attributeStats = new AspectAttributeStat();
		
		try (FileInputStream in = new FileInputStream(cxFile)) {
			try {
				CxElementReader2 r = new CxElementReader2(in, _readers, true);

				metadata = r.getPreMetaData();

				for (AspectElement elmt : r) {
					switch (elmt.getAspectName()) {
					case NodesElement.ASPECT_NAME: // Node
						NodesElement n = (NodesElement) elmt;
						attributeStats.addNode(n);
						break;
					case EdgesElement.ASPECT_NAME: { // Edge
						EdgesElement e = (EdgesElement) elmt;
						attributeStats.addEdge(e);
						break;
					}
					case NodeAttributesElement.ASPECT_NAME: // node attributes
						NodeAttributesElement attr = (NodeAttributesElement) elmt;
						if ( attr.getName().equals("name") || attr.getName().equals("represents"))
							throw new NdexException ("Node attribute " + attr.getName() + " is not allowed in CX spec.");
						attributeStats.addNodeAttribute(attr);
						break;
					case NetworkAttributesElement.ASPECT_NAME: // network attributes
						NetworkAttributesElement netAttr = (NetworkAttributesElement)elmt;
						attributeStats.addNetworkAttribute(netAttr);
						break;
					case EdgeAttributesElement.ASPECT_NAME:
						EdgeAttributesElement e = (EdgeAttributesElement) elmt;
						if ( e.getName().equals("interaction"))
							throw new NdexException ( "Edge attribute interaction is not allowed.");
						attributeStats.addEdgeAttribute(e);
						break;
					default:
						break;
					}
				}
				return attributeStats;
			} catch (IOException io) {
				throw new NdexException("Error reading CX from stream", io);
			}
		}
	}
	

	private Map<Long, Map<String, Object>> readNodes() throws NdexException, IOException {

		try (FileInputStream in = new FileInputStream(cxFile)) {

			Map<Long, Map<String, Object>> result = new TreeMap<>();

			try {
				CxElementReader2 r = new CxElementReader2(in, _readers, true);

				metadata = r.getPreMetaData();

				for (AspectElement elmt : r) {
					switch (elmt.getAspectName()) {
					case NodesElement.ASPECT_NAME: // Node
						NodesElement n = (NodesElement) elmt;
					//	if (n.getId() > nodeIdCounter)
					//		nodeIdCounter = n.getId();
						addNode(n, result);
						break;
					case NdexNetworkStatus.ASPECT_NAME: // ndexStatus we ignore this in CX
						break;
					case EdgesElement.ASPECT_NAME: // Edge
						break;
					case NodeAttributesElement.ASPECT_NAME: // node attributes
						NodeAttributesElement attr = (NodeAttributesElement) elmt;
						if ( attr.getName().equals("name") || attr.getName().equals("represents"))
							throw new NdexException ("Node attribute " + attr.getName() + " is not allowed in CX spec.");
						addNodeAttributes(attr, result);
						break;
					case NetworkAttributesElement.ASPECT_NAME: // network attributes
						//TODO: handles collections
						NetworkAttributesElement netAttr = (NetworkAttributesElement)elmt;
						Object attrValue = AspectAttributeStat.convertAttributeValue(netAttr);
						Object oldV = netAttributes.put(netAttr.getName(), attrValue);
						if ( oldV !=null)
							throw new NdexException("Duplicated network attribute name found: " + netAttr.getName());
						break;
					case EdgeAttributesElement.ASPECT_NAME:
						break;
					case CartesianLayoutElement.ASPECT_NAME:
						// TODO: need to enhance to handle subnet in the future.
						CartesianLayoutElement e = (CartesianLayoutElement) elmt;
						addCoordinates(e, result);
						break;
					case CitationElement.ASPECT_NAME:
					default:
						break;
					}
				}
				metadata = mergeMetaDataCollections(metadata, r.getPostMetaData());
				
				cleanupMetadata();
				/*
				 * updateNodeCounts(mergedMetaData, nodeIdCounter);
				 * updateEdgeCounts(mergedMetaData, edgeIdCounter);
				 * metadata.remove(NdexNetworkStatus.ASPECT_NAME);
				 * niceCX.setMetadata(mergedMetaData);
				 */
				return result;
			} catch (IOException io) {
				throw new NdexException("Error reading CX from stream", io);
			}
		}
	}
	
	private void cleanupMetadata() {
		metadata.remove("nodeAttributes");
		metadata.remove("edgeAttributes");
		metadata.remove("cartesianLayout");
		MetaDataElement networkAttribute = metadata.getMetaDataElement("networkAttributes");
		if ( networkAttribute != null ) {
			networkAttribute.setElementCount(1L);
			networkAttribute.setVersion(null);
		}	
		MetaDataElement nodeM = metadata.getMetaDataElement(CxNode.ASPECT_NAME);
		if ( nodeM != null) {
			nodeM.setIdCounter(null);
			nodeM.setVersion(null);
			nodeM.setConsistencyGroup(null);
		}
		MetaDataElement edgeM = metadata.getMetaDataElement(CxEdge.ASPECT_NAME);
		if ( edgeM != null) {
			edgeM.setIdCounter(null);
			edgeM.setVersion(null);
			edgeM.setConsistencyGroup(null);
		}
		MetaDataElement vpM = metadata.getMetaDataElement("cyVisualProperties");
		if ( vpM != null) {
			vpM.setIdCounter(null);
			vpM.setVersion(null);
			vpM.setConsistencyGroup(null);
			vpM.setElementCount(vpM.getElementCount() -2 );
			vpM.setName("visualProperties");
		}

	}
	
	/*private static ATTRIBUTE_DATA_TYPE getElementType(ATTRIBUTE_DATA_TYPE t ) throws NdexException {
		switch (t) {
		case LIST_OF_BOOLEAN:
			return ATTRIBUTE_DATA_TYPE.BOOLEAN;
		case LIST_OF_DOUBLE:
			return ATTRIBUTE_DATA_TYPE.DOUBLE;
		case LIST_OF_INTEGER:
			return ATTRIBUTE_DATA_TYPE.INTEGER;
		case LIST_OF_LONG:
			return ATTRIBUTE_DATA_TYPE.LONG;
		case LIST_OF_STRING:
			return ATTRIBUTE_DATA_TYPE.STRING;
		default: 
			throw new NdexException (t + " is not a list type.");
		}
	} */
	

	private static MetaDataCollection mergeMetaDataCollections(MetaDataCollection preMetaData,
			MetaDataCollection postMetaData) {
		if (postMetaData == null) {
			return preMetaData;
		}
		if (preMetaData == null) {
			return postMetaData;
		}
		for (MetaDataElement e : postMetaData) {
			Long cnt = e.getIdCounter();
			if (cnt != null) {
				preMetaData.setIdCounter(e.getName(), cnt);
			}
			cnt = e.getElementCount();
			if (cnt != null) {
				preMetaData.setElementCount(e.getName(), cnt);
			}
		}
		return preMetaData;
	}

	
	
	private void addNode(NodesElement elmt, Map<Long, Map<String, Object>> result) throws NdexException {
		Map<String, Object> newNode = result.get(elmt.getId());

		if (newNode == null) {
			newNode = new HashMap<>();
			result.put(elmt.getId(), newNode);
		}

		newNode.put("id", elmt.getId());
			
		if ( elmt.getNodeName() != null) {
			NodeAttributesElement attr = new NodeAttributesElement(elmt.getId(), "name", elmt.getNodeName(), ATTRIBUTE_DATA_TYPE.STRING);	
			addNodeAttributes(attr, result);
		}   
		if (elmt.getNodeRepresents() != null) {
			NodeAttributesElement attr = new NodeAttributesElement(elmt.getId(), "represents", elmt.getNodeName(), ATTRIBUTE_DATA_TYPE.STRING);	
			addNodeAttributes(attr, result);
		}    
	}
	
/*	private static void addAttributeAlias(Map<String, Map<String,Object>> defTable, String attributeName, String shortAlias) {
		Map<String, Object> attrDef = defTable.get(attributeName);
		if ( attrDef == null ) 
			attrDef = new HashMap<>();
		attrDef.put("a", shortAlias);
		
	} */

	private  void addNodeAttributes(NodeAttributesElement elmt, Map<Long, Map<String, Object>> result)
			throws NdexException {

		Map<String, Object> newNode = result.get(elmt.getPropertyOf());

		if (newNode == null) {
			newNode = new HashMap<>();
			result.put(elmt.getPropertyOf(), newNode);
		}

		Map<String, Object> nodeAttr = (Map<String, Object>) newNode.get("v");
		if (nodeAttr == null) {
			nodeAttr = new HashMap<>();
			newNode.put("v", nodeAttr);
		}

		Object v = AspectAttributeStat.convertAttributeValue(elmt);
		String attrName = elmt.getName();
		Map<String, DeclarationEntry> nodeAttributeDef = attrDeclarations.getDeclarations().get(CxNode.ASPECT_NAME);
		if ( nodeAttributeDef != null && 
				nodeAttributeDef.containsKey( attrName )) {
			DeclarationEntry decl = nodeAttributeDef.get( attrName );
			if ( decl.getDefaultValue() !=null) {
				Object defaultV = decl.getDefaultValue();
				if ( v.equals(defaultV))
					return;
			}
			
			if (decl.getAlias() !=null) {
				attrName = decl.getAlias();
			}
		}
		Object oldV = nodeAttr.put(attrName, v);
		if (oldV != null)
			throw new NdexException("Duplicate node attribute on node id: " + elmt.getPropertyOf() + ". Attribute name:"
					+ elmt.getName() + " has value " + oldV + " and " + elmt.getValue());

	}

	private static void addCoordinates(CartesianLayoutElement elmt, Map<Long, Map<String, Object>> result)
			throws NdexException {

		Map<String, Object> newNode = result.get(elmt.getNode());

		if (newNode == null) {
			newNode = new HashMap<>();
			result.put(elmt.getNode(), newNode);
		}

		newNode.put("x", elmt.getX());
		newNode.put("y", elmt.getY());
		if (elmt.getZ() != null) {
			newNode.put("z", elmt.getZ());
		}

		if (elmt.getView() != null) {
			throw new NdexException("Coordinate in Collection is not supported yet.");
		}
	}

	
	private Map<String,Object> readVisualProperties() throws FileNotFoundException, IOException, NdexException {
		Map<String,Object> rs= new HashMap<>();
		
		CxVisualProperty style = new CxVisualProperty();
		
		List<CxNodeBypass> nodeBypasses = new ArrayList<>();
		List<CxEdgeBypass> edgeBypasses = new ArrayList<>();  
		
		long cnt = 0;
		
		try (FileInputStream in = new FileInputStream(cxFile)) {
			
			CxElementReader2 r = new CxElementReader2(in, _readers, true);

			r.getPreMetaData();

			for (AspectElement elmt : r) {
				switch (elmt.getAspectName()) {
				case CyVisualPropertiesElement.ASPECT_NAME:
					CyVisualPropertiesElement cyVP = (CyVisualPropertiesElement)elmt;
					addVisuaProperty(cyVP, nodeBypasses,edgeBypasses, style);
					break;
				case NodesElement.ASPECT_NAME: // Node
					break;
				case NdexNetworkStatus.ASPECT_NAME: // ndexStatus we ignore this in CX
				case EdgesElement.ASPECT_NAME: // Edge 
					break;
				case NodeAttributesElement.ASPECT_NAME: // node attributes
					break;
				case NetworkAttributesElement.ASPECT_NAME: // network attributes
					break;
				case EdgeAttributesElement.ASPECT_NAME:
					break;
				case CartesianLayoutElement.ASPECT_NAME:
					CartesianLayoutElement e = (CartesianLayoutElement) elmt;
					break;
				case CitationElement.ASPECT_NAME:
				default:
					break;
				}
			}

		} 


		if (!style.isEmpty()) {
			rs.put(CxVisualProperty.ASPECT_NAME,style);
		}	
		
		if (!nodeBypasses.isEmpty()) {
			rs.put(CxNodeBypass.ASPECT_NAME, nodeBypasses);
		}
		
		if ( !edgeBypasses.isEmpty()) {
			rs.put(CxEdgeBypass.ASPECT_NAME, edgeBypasses);
		}
		
		return rs;

	}

    private void addVisuaProperty(CyVisualPropertiesElement elmt, List<CxNodeBypass> nodeBypasses,
    		List<CxEdgeBypass> edgeBypasses, CxVisualProperty style) throws NdexException, IOException {
    	String po = elmt.getProperties_of();
    	if ( po.equals("network")) {

    		style.getDefaultProps().setNetworkProperties(vpConverter.convertNetworkVPs(elmt.getProperties()));
    	} else if( po.equals("nodes:default")) {

       		// get dependencies
    		String nodeSizeLockedStr = elmt.getDependencies().get("nodeSizeLocked");
    	    		
    		boolean nodeSizeLocked = ( nodeSizeLockedStr != null && nodeSizeLockedStr.equals("true"));   	
    		
    		this.visualDependencies.put("nodeSizeLocked", Boolean.valueOf(nodeSizeLockedStr) );
    		this.visualDependencies.put("nodeCustomGraphicsSizeSync", 
    				Boolean.valueOf(elmt.getDependencies().get("nodeCustomGraphicsSizeSync")) );

    		Map<String,String> cx1Properties = elmt.getProperties();
    		if ( nodeSizeLocked) {
    			String size = cx1Properties.get("NODE_SIZE");
    			cx1Properties.put("NODE_WIDTH", size);
    			cx1Properties.put("NODE_HEIGHT", size);
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
    			processMappingEntry(nodeMappings, style.getNodeMappings());
    		}
    		
    	} else if ( po.equals("edges:default")) {
      		//Map<String, Object> defaultVp = getDefaultVP (style);
    		      		
    		SortedMap<String,String> cx1Properties = elmt.getProperties();
    		
    		this.visualDependencies.putAll(elmt.getDependencies());
    		
    		// add dependencies
    		String arrowColorMatchesEdgeStr = elmt.getDependencies().get("arrowColorMatchesEdge");

    		this.visualDependencies.put("arrowColorMatchesEdge", Boolean.valueOf(arrowColorMatchesEdgeStr) );
    		
    		boolean arrowColorMatchesEdge = (arrowColorMatchesEdgeStr != null && arrowColorMatchesEdgeStr.equals("true"));

    		if ( arrowColorMatchesEdge ) {
    			String ep = cx1Properties.get("EDGE_PAINT");
    			cx1Properties.put("EDGE_SOURCE_ARROW_UNSELECTED_PAINT", ep);
    			cx1Properties.put("EDGE_STROKE_UNSELECTED_PAINT", ep);
    			cx1Properties.put("EDGE_TARGET_ARROW_UNSELECTED_PAINT", ep);
    		}

    		style.getDefaultProps().setEdgeProperties(vpConverter.convertEdgeOrNodeVPs(cx1Properties));
    		
    		// add mapping
    		SortedMap<String,Mapping> edgeMappings = elmt.getMappings();
    		
    		//TODO: process the lock flag
    		if ( arrowColorMatchesEdge) {
    			Mapping m = edgeMappings.remove("EDGE_PAINT");
    			if ( m !=null) {
    				edgeMappings.put("EDGE_SOURCE_ARROW_UNSELECTED_PAINT", m);
    				edgeMappings.put("EDGE_STROKE_UNSELECTED_PAINT", m);
    				edgeMappings.put("EDGE_TARGET_ARROW_UNSELECTED_PAINT", m);
    			}
    		}	
    		
    		if ( edgeMappings != null && !edgeMappings.isEmpty()) {
    			processMappingEntry(edgeMappings, style.getEdgeMappings());
    		}
    		
    	} else if ( po.equals("nodes")) {  //node bypasses
    		
    		SortedMap<String,String> vps = elmt.getProperties();
    		
    		Boolean nodeSizeLocked = (Boolean)this.visualDependencies.get("nodeSizeLocked");
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
    	} else {  // edge bypasses
    		VisualPropertyTable v = vpConverter.convertEdgeOrNodeVPs(elmt.getProperties());
    		if ( !v.getVisualProperties().isEmpty()) {
    			CxEdgeBypass edgebp = new CxEdgeBypass();
    			edgebp.setId(elmt.getApplies_to().longValue());
    			edgebp.setVisualProperties(v);
    			edgeBypasses.add(edgebp);
    		}
    	}
    }

    /**
     * Get the default style object from a cx2 style object
     * @param cx2Style
     * @return
     */
  /*  private static Map<String, Object> getDefaultVP(DefaultVisualProperties cx2Style) {
    	Map<String, Object> defaultVp = (Map<String, Object>)cx2Style.get("default");
		if ( defaultVp == null) {
			defaultVp = new HashMap<> ();
			cx2Style.put("default", defaultVp);
		}
		return defaultVp;
    } */
    
	private void processMappingEntry(SortedMap<String, Mapping> nodeMappings, Map<String, VisualPropertyMapping> v2NodeMappings)
			throws NdexException, IOException {
		for ( Map.Entry<String, Mapping> entry : nodeMappings.entrySet() ) {
			String vpName = entry.getKey();
			String newVPName =  vpConverter.getNewEdgeOrNodeProperty(vpName);
			if ( newVPName == null)
				continue;
			
			VisualPropertyMapping mappingObj = new VisualPropertyMapping();
			String mappingType = entry.getValue().getType();
			mappingObj.setType(VPMappingType.valueOf(mappingType));
			MappingDefinition defObj = new MappingDefinition();
			mappingObj.setMappingDef(defObj);
			String defString = entry.getValue().getDefinition();
			if ( mappingType.equals("PASSTHROUGH")) {
				String mappingAttrName = ConverterUtilities.getPassThroughMappingAttribute(defString); 
				defObj.setAttributeName(mappingAttrName);
			} else if (mappingType.equals("DISCRETE")) {
				List<Map<String,Object>> m = new ArrayList<> ();
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
		            	throw new NdexException("error: discrete mapping string is corruptted for " + defString);
		            }
		            
		            Map<String,Object> mapEntry = new HashMap<>(2);
                             ConverterUtilitiesResult cRes = ConverterUtilities.cvtStringValueToObj(t, k);
                             
                             outputAnyConverterUtilitiesWarnings(System.err, cRes);
                             
		            mapEntry.put("v", cRes.getResult());
		            mapEntry.put("vp", vpConverter.getNewEdgeOrNodePropertyValue(vpName,v));
		        	m.add(mapEntry);
		            counter++;
		        }
		        
				defObj.setAttributeName(col);
				defObj.setMapppingList(m);

			} else {  //continuous mapping
				List<Map<String,Object>> m = new ArrayList<> ();
				MappingValueStringParser sp = new MappingValueStringParser(defString);	
				String col = sp.get("COL");
				String t = sp.get("T");
				
				Object min = null;
				Boolean includeMin = null;
				//Object max = null;
				Object minVP = null;
				//Object maxVP = null;
				
			    int counter = 0;
			    Map<String,Object> currentMapping = new HashMap<>();
			    
		        while (true) {
		        	final String L = sp.get("L=" + counter);
		            if (L == null) {
		                break;
		            }
		            Object LO = vpConverter.getNewEdgeOrNodePropertyValue(vpName,L);
		            
		            final String E = sp.get("E=" + counter);
		            if ( E == null) {
		                break;
		            }
		            Object EO = vpConverter.getNewEdgeOrNodePropertyValue(vpName,E);
		            
		            final String G = sp.get("G=" + counter);
		            if (G == null) {
		                break;
		            }
		            Object GO = vpConverter.getNewEdgeOrNodePropertyValue(vpName,G);

		            final String OV = sp.get("OV=" + counter);
		            if (OV == null) {
		            	throw new NdexException("error: continuous mapping string is corruptted for " + defString);
		            } 
                             ConverterUtilitiesResult cRes = ConverterUtilities.cvtStringValueToObj(t, OV);
		            outputAnyConverterUtilitiesWarnings(System.err, cRes);
                            
		            Object OVO = cRes.getResult();
		            
		            if ( counter == 0 ) {  // min side
		            	currentMapping.put("includeMin", Boolean.FALSE);
		            	currentMapping.put("includeMax", E.equals(L));
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
		        currentMapping.put("includeMin",includeMin);
            	currentMapping.put("includeMax", Boolean.FALSE);
            	currentMapping.put("minVPValue", minVP);
            	currentMapping.put("min", min);
            	m.add(currentMapping);
		        
		        // add the list
		    	defObj.setAttributeName(col);
				defObj.setMapppingList(m);
			}
			v2NodeMappings.put(newVPName, mappingObj);
		}
	}
	
		
    
	private Map<Long, EdgesElement> readEdges() throws IOException {
		
		Map<Long,EdgesElement> rs = new TreeMap<>();
		
		long cnt = 0;
		
		try (FileInputStream in = new FileInputStream(cxFile)) {
			
			CxElementReader2 r = new CxElementReader2(in, _readers, true);

			r.getPreMetaData();

			for (AspectElement elmt : r) {
				switch (elmt.getAspectName()) {
				case NodesElement.ASPECT_NAME: // Node
					break;
				case NdexNetworkStatus.ASPECT_NAME: // ndexStatus we ignore this in CX
					break;
				case EdgesElement.ASPECT_NAME: { // Edge 
					EdgesElement edge = (EdgesElement)elmt;
					rs.put(edge.getId(), edge);
					cnt++;
					
					if ( cnt % 1000000 == 0 )
						System.out.println (cnt + " edges read.");
			/*		Map<String,Object> newEdge = new HashMap<>();
					newEdge.put("id",edge.getId());
					newEdge.put("s",edge.getSource());
					newEdge.put("t",edge.getTarget());
					if (edge.getInteraction() != null && (configObj.get("defaultInteraciton") ==null || 
							(! edge.getInteraction().equals(configObj.get("defaultInteraciton") )))) {
						newEdge.put("i", edge.getInteraction());
					}
					
					mapper.writeValue(out,newEdge);
					out.write(elmtDivider); */
					}
					break;
				case NodeAttributesElement.ASPECT_NAME: // node attributes
					break;
				case NetworkAttributesElement.ASPECT_NAME: // network attributes
					break;
				case EdgeAttributesElement.ASPECT_NAME:
					break;
				case CartesianLayoutElement.ASPECT_NAME:
					//CartesianLayoutElement e = (CartesianLayoutElement) elmt;

					break;
				case CitationElement.ASPECT_NAME:
				default:
					break;
				}
			}

		} 

	//	out.write("]},\n".getBytes());
	//	out.flush();
		return rs;

	}
	
	
	private void writeEdgeAttributes(Map<Long, EdgesElement> edges, FileOutputStream out, ObjectMapper mapper) throws NdexException, IOException {
		
		Set<Long> processedEdges = new TreeSet<>();
		
		try (FileInputStream in = new FileInputStream(cxFile)) {

		    Map<String, Object> tmpResult = new TreeMap<>();

			try {
				CxElementReader2 r = new CxElementReader2(in, _readers, true);

				for (AspectElement elmt : r) {
					switch (elmt.getAspectName()) {
					case NodesElement.ASPECT_NAME: // Node
					case NdexNetworkStatus.ASPECT_NAME: // ndexStatus we ignore this in CX
					case EdgesElement.ASPECT_NAME: // Edge
					case NodeAttributesElement.ASPECT_NAME: // node attributes
					case NetworkAttributesElement.ASPECT_NAME: // network attributes
						break;
					case EdgeAttributesElement.ASPECT_NAME:
						addEdgeAttributes(edges, out, mapper, (EdgeAttributesElement)elmt, 
								tmpResult, processedEdges);
						break;
					case CartesianLayoutElement.ASPECT_NAME:
						break;
					case CitationElement.ASPECT_NAME:
					default:
						break;
					}
				}
				
				// print the last attribute.
				if (!tmpResult.isEmpty()) {
					Map<String, Object> worker = new HashMap<>();
					
					worker.put("id", currentEdgeId);
					EdgesElement edge = edges.remove (currentEdgeId);
					worker.put("s", edge.getSource());
					worker.put("t", edge.getTarget());
					if (edge.getInteraction() != null) {
						tmpResult.put("i", edge.getInteraction());	
					}
					
					worker.put("v",tmpResult);
					if ( edgeCounter != 0 )
						out.write(elmtDivider);
					mapper.writeValue(out, worker);
				    edgeCounter ++;
				    out.flush();
				}    
				
				// print out the remaining edges in the table. These are the edges that have no attributes
				for (EdgesElement edge : edges.values()) {
					CxEdge e = new CxEdge(edge.getId(),edge.getSource(),edge.getTarget());
					
					if (edge.getInteraction() != null) {
						e.getAttributes().put("i", edge.getInteraction());	
					}
					
					if ( edgeCounter != 0 )
						out.write(elmtDivider);
					mapper.writeValue(out, e);
				    edgeCounter ++;
				}
				
				return ;
			} catch (IOException io) {
				throw new NdexException("Error reading CX from stream", io);
			}
		}
	}

	
	private void addEdgeAttributes(Map<Long,EdgesElement> edgeTable, FileOutputStream out, ObjectMapper mapper, 
			EdgeAttributesElement elmt,  Map<String, Object> resultHolder, Set<Long> processedId)
			throws NdexException, JsonGenerationException, JsonMappingException, IOException {

		Object v = AspectAttributeStat.convertAttributeValue(elmt);
		String attrName = elmt.getName();
		Map<String, DeclarationEntry> edgeAttributeDef = attrDeclarations.getDeclarations().get(CxEdge.ASPECT_NAME);
		if (edgeAttributeDef.containsKey(attrName )) {
			DeclarationEntry decl = edgeAttributeDef.get(elmt.getName());
			if ( decl.getDefaultValue() !=null) {
				Object defaultV = decl.getDefaultValue();
				if ( v.equals(defaultV))
					return;
			}
			if (decl.getAlias() !=null) {
				attrName = decl.getAlias();
			}
		}

		if ( this.currentEdgeId == null || !elmt.getPropertyOf().equals(currentEdgeId)) {
			// attribute on new edge found.
			if ( currentEdgeId !=null)  { // print out the previous record.
				processedId.add(currentEdgeId);
				Map<String, Object> worker = new HashMap<>();
				worker.put("id", currentEdgeId);
				EdgesElement edge = edgeTable.remove (currentEdgeId);
				worker.put("s", edge.getSource());
				worker.put("t", edge.getTarget());
				if (edge.getInteraction() != null) {
					String interactionName = "interaction";
					if (edgeAttributeDef.containsKey(interactionName)) {
						DeclarationEntry decl = edgeAttributeDef.get(interactionName);

						if (decl.getAlias() !=null) {
							interactionName = decl.getAlias();
						}
						if ( decl.getDefaultValue() !=null) {
							Object defaultV = decl.getDefaultValue();
							if ( !edge.getInteraction().equals(defaultV))
								resultHolder.put(interactionName, edge.getInteraction());		
						} else {
							resultHolder.put(interactionName, edge.getInteraction());	
							
						}
					}
				}
				worker.put("v",resultHolder);
				if ( edgeCounter != 0 )
					out.write(elmtDivider);
				mapper.writeValue(out, worker);
			    out.flush();
			    edgeCounter ++;
			    edgeTable.remove(currentEdgeId);

			    if ( edgeCounter % 100000 == 0) 
			    	System.out.println(edgeCounter + " edges have been written.");
			}    
			if ( processedId.contains(elmt.getPropertyOf()))
				throw new NdexException ("Scattered edge attribute found for edge id " + elmt.getPropertyOf() );
			resultHolder.clear();
			currentEdgeId = elmt.getPropertyOf();
		} 
			
		resultHolder.put(attrName, v);			
	}

	private void readOpaqueAspects() throws NdexException, IOException {

		try (FileInputStream in = new FileInputStream(cxFile)) {

			try {
				CxElementReader2 r = new CxElementReader2(in, _readers, true);

				for (AspectElement elmt : r) {
					switch (elmt.getAspectName()) {
					case NodesElement.ASPECT_NAME: // Node
					case EdgesElement.ASPECT_NAME: // Edge
					case NodeAttributesElement.ASPECT_NAME: // node attributes
					case NetworkAttributesElement.ASPECT_NAME: // network attributes
					case EdgeAttributesElement.ASPECT_NAME:
					case CartesianLayoutElement.ASPECT_NAME:
					case CyVisualPropertiesElement.ASPECT_NAME:
					case NdexNetworkStatus.ASPECT_NAME: // these are deprecated aspects
						break;
					default:
						OpaqueElement e = (OpaqueElement) elmt;
						List<OpaqueElement> aspectElementList = opaqueAspectTable.get(e.getAspectName());
						if (aspectElementList == null) {
							aspectElementList = new ArrayList<>();
							opaqueAspectTable.put(e.getAspectName(), aspectElementList);
						}
						aspectElementList.add(e);
						break;
					}
				}
			} catch (IOException io) {
				throw new NdexException("Error reading CX from stream", io);
			}
		}
	}

	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException, NdexException {
		if ( args.length <2 || args.length > 3 ) {
			System.out.println("Usage: java -cp <jar> org.ndexbio.cx2.converter.CXToCX2Converter <CXFilePath> <CX2FilePath> [attribute_defination_file]\n" + 
		              "attribute_definition_file is optional. It has customized attribute declarations and will be used to overwrite the default settings." );
			System.exit(1);
		}
		
		CXToCX2LargeFileConverter converter;
		if ( args.length == 2) {
			converter = new CXToCX2LargeFileConverter(args[0], null, args[1]);
		} else
			converter = new CXToCX2LargeFileConverter(args[0], args[1], args[2]);
		
		converter.convert();

	}
}
