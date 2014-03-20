package org.ndexbio.model.object;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

/*
 * Represents a set of service operations to interact with NDEx model objects.
 * Implementations may utilize the REST service classes directly or through
 * HTTP-based invocation of NDEx RESTful operations
 * 
 */

public interface NdexDataModelService {
	public Network getNetworkById(String networkId);
	
	public Iterable<Citation> getCitationsByNetworkId(String networkId);
	public Network getSubnetworkByCitationId(String networkId, String citationId);
	public Iterable<Edge> getEdgesBySupportId(String supportId);
	public Iterable<Namespace> getNamespacesByNetworkId(String networkId);
	public List<Network> findNetworks(String string);

	public List<Network> findNetworksByText(String searchString, String searchType, Integer blockSize, Integer skipBlocks) 
			throws JsonProcessingException, IOException;
	
	
	public List<Network> findNetworksByProperty(String property, String value, String operator, Integer maxNetworks) throws JsonProcessingException, IOException;	
	
	
	public List<BaseTerm> findBaseTermsInNetworkByNamespace(String namespacePrefix, String networkId) throws JsonProcessingException, IOException;
	

	public Network getEdges(String id, int skipBlocks, int edgesPerBlock) throws IOException;
	

	public Network createNetwork(Network network) throws JsonProcessingException, IOException, Exception;
	

	public Network addNetwork(String targetNetworkId, String equivalenceMethod,
			Network network) throws JsonProcessingException, IOException, Exception;
		
	

	public Network getNetworkByNonEdgeNodes(String networkId, int skipBlocks,
			int nodesPerBlock) throws IOException;
	

}
