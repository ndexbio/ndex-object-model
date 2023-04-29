package org.ndexbio.model.object.network;


/**
 * Controls how much metadata should be included in a returned NetworkMetadata object.
 * @author jingchen
 *
 */
public enum NetworkSummaryFormat {

	/**
	 * The NDEx update status of the network, which includes UUID, lastModificationTime and lastModifiedBy (to be added).
	 */
	UPDATE,
	
	/**
	 * Return the stats of the network manage by NDEx server. Which includes 
	 * UUID,
	 * lastModificationTime
	 * lastModifiedBy
	 */
	NDEXONLY,
	
	/**
	 * Network attributes on the network.
	 */
	PROPERTIES, 
	
	/**
	 * Return the full record.
	 */
   FULL
   
}
