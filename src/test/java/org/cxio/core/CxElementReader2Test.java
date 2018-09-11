package org.cxio.core;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;

import org.cxio.aspects.datamodels.EdgeAttributesElement;
import org.cxio.aspects.datamodels.EdgesElement;
import org.cxio.aspects.datamodels.NetworkAttributesElement;
import org.cxio.aspects.datamodels.NodeAttributesElement;
import org.cxio.aspects.datamodels.NodesElement;
import org.cxio.aspects.datamodels.SubNetworkElement;
import org.cxio.aspects.readers.EdgeAttributesFragmentReader;
import org.cxio.aspects.readers.EdgesFragmentReader;
import org.cxio.aspects.readers.GeneralAspectFragmentReader;
import org.cxio.aspects.readers.NetworkAttributesFragmentReader;
import org.cxio.aspects.readers.NodeAttributesFragmentReader;
import org.cxio.aspects.readers.NodesFragmentReader;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.core.interfaces.AspectFragmentReader;
import org.cxio.metadata.MetaDataCollection;
import org.cxio.metadata.MetaDataElement;
import org.junit.Test;
import org.ndexbio.model.cx.CitationElement;
import org.ndexbio.model.cx.EdgeCitationLinksElement;
import org.ndexbio.model.cx.EdgeSupportLinksElement;
import org.ndexbio.model.cx.FunctionTermElement;
import org.ndexbio.model.cx.NamespacesElement;
import org.ndexbio.model.cx.NdexNetworkStatus;
import org.ndexbio.model.cx.NodeCitationLinksElement;
import org.ndexbio.model.cx.NodeSupportLinksElement;
import org.ndexbio.model.cx.Provenance;
import org.ndexbio.model.cx.SupportElement;

public class CxElementReader2Test {

	@Test
	public void test() throws URISyntaxException, FileNotFoundException, IOException {

		HashSet<AspectFragmentReader> readers = new HashSet<>(20);
		
		  readers.add(EdgesFragmentReader.createInstance());
		  readers.add(EdgeAttributesFragmentReader.createInstance());
		  readers.add(NetworkAttributesFragmentReader.createInstance());
		  readers.add(NodesFragmentReader.createInstance());
		  readers.add(NodeAttributesFragmentReader.createInstance());
		  
		  readers.add(new GeneralAspectFragmentReader<> (NdexNetworkStatus.ASPECT_NAME,
				NdexNetworkStatus.class));
		  readers.add(new GeneralAspectFragmentReader<> (NamespacesElement.ASPECT_NAME,NamespacesElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (FunctionTermElement.ASPECT_NAME,FunctionTermElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (CitationElement.ASPECT_NAME,CitationElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (SupportElement.ASPECT_NAME,SupportElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (EdgeCitationLinksElement.ASPECT_NAME,EdgeCitationLinksElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (EdgeSupportLinksElement.ASPECT_NAME,EdgeSupportLinksElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (NodeCitationLinksElement.ASPECT_NAME,NodeCitationLinksElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (NodeSupportLinksElement.ASPECT_NAME,NodeSupportLinksElement.class));
		  
		  File f = new File(getClass().getClassLoader().getResource("network1_3n2e_no_numVerification.cx").toURI());
		  
		  int edgeCount = 0;
		  int nodeCount = 0;
		  int nodeAttrCnt = 0;
		  int edgeAttrCnt = 0;
		  int netAttrCnt = 0;
		  int opaqueCnt = 0;
		  try (FileInputStream in = new FileInputStream (f) ) {
			  CxElementReader2 cxreader = new CxElementReader2(in, readers, true);
			  MetaDataCollection metadata = cxreader.getPreMetaData();
			  
			  
				for ( AspectElement elmt : cxreader ) {
					switch ( elmt.getAspectName() ) {
						case NodesElement.ASPECT_NAME :       //Node
							System.out.println((NodesElement) elmt);
							nodeCount ++;
							break;
						case NdexNetworkStatus.ASPECT_NAME:   //ndexStatus we ignore this in CX
						//	netStatus = (NdexNetworkStatus) elmt;
						//	saveNetworkStatus(netStatus);
							break; 
						case EdgesElement.ASPECT_NAME:       // Edge
							EdgesElement ee = (EdgesElement) elmt;
							System.out.println(ee);
							edgeCount ++;
							break;
						case NodeAttributesElement.ASPECT_NAME:  // node attributes
							System.out.println((NodeAttributesElement) elmt );
							nodeAttrCnt ++;
							break;
						case EdgeAttributesElement.ASPECT_NAME:  // edge attributes
							System.out.println( elmt );
							edgeAttrCnt ++;
							break;
						case NetworkAttributesElement.ASPECT_NAME: //network attributes
							System.out.println(elmt);
							netAttrCnt++;
							break;
						case CitationElement.ASPECT_NAME: 
							System.out.println((CitationElement)elmt);
							break;
						case SupportElement.ASPECT_NAME:
							System.out.println((SupportElement)elmt);
							break;
						case EdgeCitationLinksElement.ASPECT_NAME:
							System.out.println((EdgeCitationLinksElement) elmt);
							break;
						case EdgeSupportLinksElement.ASPECT_NAME:
							System.out.println((EdgeSupportLinksElement) elmt);
							break;
						case NodeSupportLinksElement.ASPECT_NAME:
							System.out.println((NodeSupportLinksElement) elmt);
							break;
						case NodeCitationLinksElement.ASPECT_NAME:
							System.out.println((NodeCitationLinksElement) elmt);
							break;
						case FunctionTermElement.ASPECT_NAME:
							System.out.println((FunctionTermElement)elmt);
							break;
						default:    // opaque aspect
							System.out.println("opaque: " + elmt);
							opaqueCnt ++;
					}

				} 
				  //save the metadata
				  MetaDataCollection postmetadata = cxreader.getPostMetaData();
				  if ( postmetadata !=null) {
					  if( metadata == null) {
						  metadata = postmetadata;
					  } else {
						  for (MetaDataElement e : postmetadata) {
							  Long cnt = e.getIdCounter();
							  if ( cnt !=null) {
								 metadata.setIdCounter(e.getName(),cnt);
							  }
							  cnt = e.getElementCount() ;
							  if ( cnt !=null) {
									 metadata.setElementCount(e.getName(),cnt);
							  }
						  }
					  }
				  }
				  
				  assertEquals( metadata.size() , 8);

		  }
		  
		  assertEquals( edgeCount , 2);
		  assertEquals( nodeCount , 3);
		  assertEquals( nodeAttrCnt , 0);
		  assertEquals( edgeAttrCnt , 4);
		  assertEquals( netAttrCnt  , 1);
		  assertEquals( opaqueCnt , 7);
	}

	
	
	@Test
	public void test2() throws URISyntaxException, FileNotFoundException, IOException {

		HashSet<AspectFragmentReader> readers = new HashSet<>(20);
		
		  readers.add(EdgesFragmentReader.createInstance());
		  readers.add(EdgeAttributesFragmentReader.createInstance());
		  readers.add(NetworkAttributesFragmentReader.createInstance());
		  readers.add(NodesFragmentReader.createInstance());
		  readers.add(NodeAttributesFragmentReader.createInstance());
		  
		  readers.add(new GeneralAspectFragmentReader<> (NdexNetworkStatus.ASPECT_NAME,
				NdexNetworkStatus.class));
		  readers.add(new GeneralAspectFragmentReader<> (NamespacesElement.ASPECT_NAME,NamespacesElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (FunctionTermElement.ASPECT_NAME,FunctionTermElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (CitationElement.ASPECT_NAME,CitationElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (SupportElement.ASPECT_NAME,SupportElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (EdgeCitationLinksElement.ASPECT_NAME,EdgeCitationLinksElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (EdgeSupportLinksElement.ASPECT_NAME,EdgeSupportLinksElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (NodeCitationLinksElement.ASPECT_NAME,NodeCitationLinksElement.class));
		  readers.add(new GeneralAspectFragmentReader<> (NodeSupportLinksElement.ASPECT_NAME,NodeSupportLinksElement.class));
		  
		  File f = new File(getClass().getClassLoader().getResource("network1 - 3n2e collection.cx").toURI());
		  
		  int edgeCount = 0;
		  int nodeCount = 0;
		  int nodeAttrCnt = 0;
		  int edgeAttrCnt = 0;
		  int netAttrCnt = 0;
		  int opaqueCnt = 0;
		  try (FileInputStream in = new FileInputStream (f) ) {
			  CxElementReader2 cxreader = new CxElementReader2(in, readers, true);
			  MetaDataCollection metadata = cxreader.getPreMetaData();
			  
			  
				for ( AspectElement elmt : cxreader ) {
					switch ( elmt.getAspectName() ) {
						case NodesElement.ASPECT_NAME :       //Node
							System.out.println((NodesElement) elmt);
							nodeCount ++;
							break;
						case NdexNetworkStatus.ASPECT_NAME:   //ndexStatus we ignore this in CX
						//	netStatus = (NdexNetworkStatus) elmt;
						//	saveNetworkStatus(netStatus);
							break; 
						case EdgesElement.ASPECT_NAME:       // Edge
							EdgesElement ee = (EdgesElement) elmt;
							System.out.println(ee);
							edgeCount ++;
							break;
						case NodeAttributesElement.ASPECT_NAME:  // node attributes
							System.out.println((NodeAttributesElement) elmt );
							nodeAttrCnt ++;
							break;
						case EdgeAttributesElement.ASPECT_NAME:  // edge attributes
							System.out.println( elmt );
							edgeAttrCnt ++;
							break;
						case NetworkAttributesElement.ASPECT_NAME: //network attributes
							System.out.println(elmt);
							netAttrCnt++;
							break;
						case CitationElement.ASPECT_NAME: 
							System.out.println((CitationElement)elmt);
							break;
						case SupportElement.ASPECT_NAME:
							System.out.println((SupportElement)elmt);
							break;
						case EdgeCitationLinksElement.ASPECT_NAME:
							System.out.println((EdgeCitationLinksElement) elmt);
							break;
						case EdgeSupportLinksElement.ASPECT_NAME:
							System.out.println((EdgeSupportLinksElement) elmt);
							break;
						case NodeSupportLinksElement.ASPECT_NAME:
							System.out.println((NodeSupportLinksElement) elmt);
							break;
						case NodeCitationLinksElement.ASPECT_NAME:
							System.out.println((NodeCitationLinksElement) elmt);
							break;
						case FunctionTermElement.ASPECT_NAME:
							System.out.println((FunctionTermElement)elmt);
							break;
						default:    // opaque aspect
							System.out.println("opaque: " + elmt);
							opaqueCnt ++;
					}

				} 
				  //save the metadata
				  MetaDataCollection postmetadata = cxreader.getPostMetaData();
				  if ( postmetadata !=null) {
					  if( metadata == null) {
						  metadata = postmetadata;
					  } else {
						  for (MetaDataElement e : postmetadata) {
							  Long cnt = e.getIdCounter();
							  if ( cnt !=null) {
								 metadata.setIdCounter(e.getName(),cnt);
							  }
							  cnt = e.getElementCount() ;
							  if ( cnt !=null) {
									 metadata.setElementCount(e.getName(),cnt);
							  }
						  }
					  }
				  }
				  
				  assertEquals( metadata.size() , 13);

		  }
		  
		  assertEquals( edgeCount , 2);
		  assertEquals( nodeCount , 3);
		  assertEquals( nodeAttrCnt , 3);
		  assertEquals( edgeAttrCnt , 4);
		  assertEquals( netAttrCnt  , 2);
		  assertEquals( opaqueCnt , 23);
	}

}
