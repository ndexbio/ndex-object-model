package org.ndexbio.model.object.network;


/**
 * Controls how much metadata should be included in a returned NetworkMetadata object.
 * @author jingchen
 *
 */
public enum NetworkSummaryFormat {

	/**
	 * The NDEx update status of the network, which includes UUID, lastModificationTime and updatedBy.
	 */
	UPDATE,
	
	/**
	 * Return the network name, description and all the NDEx stats of 
	 */
	COMPACT,
	
	/**
	 * Additional properties that are not included in the COMPACT format. 
	 */
	PROPERTIES, 
	/**
	 * Return the full record.
	 */
   FULL
   
}
