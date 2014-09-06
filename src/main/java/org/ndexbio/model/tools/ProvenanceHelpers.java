package org.ndexbio.model.tools;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.ndexbio.model.object.ProvenanceEntity;
import org.ndexbio.model.object.ProvenanceEvent;
import org.ndexbio.model.object.network.NetworkSummary;

public class ProvenanceHelpers {
	
	// Create a new provenance history in the case where the
	// latest event has only one input. (such as a copy operation)
	public static ProvenanceEntity createProvenanceHistory(
			NetworkSummary newNetwork,
			String hostURI,
			String eventType, 
			Timestamp eventDate,
			ProvenanceEntity input
			){
		ProvenanceEntity prov = createRootProvenanceEntity(newNetwork, hostURI, eventType, eventDate);
		if (input != null){
			ProvenanceEvent event = prov.getCreationEvent();
			event.addInput(copyProvenanceEntity(input));			
		}
		return prov;
	}

	// Create a new provenance history in the case where the
	// latest event has multiple inputs (such as merging information 
    // from two networks to create a new network)
	public static ProvenanceEntity createProvenanceHistory(
			NetworkSummary newNetwork,
			String hostURI,
			String eventType, 
			Timestamp eventDate,
			List<ProvenanceEntity> inputs
			){
		ProvenanceEntity prov = createRootProvenanceEntity(newNetwork, hostURI, eventType, eventDate);
		if (inputs != null){
			ProvenanceEvent event = prov.getCreationEvent();
			for (ProvenanceEntity input : inputs){
				if (null != input){
					event.addInput(copyProvenanceEntity(input));
				}
			}
		}
		return prov;
	}
	
	private static ProvenanceEntity createRootProvenanceEntity(
			NetworkSummary networkSummary, String hostURI, String eventType, Timestamp eventTime) {
		ProvenanceEntity prov = new ProvenanceEntity(networkSummary, hostURI);
		ProvenanceEvent event = new ProvenanceEvent(eventType, eventTime);
		prov.setCreationEvent(event);
		return prov;
	}
	
	// Recursive Copy of provenance history, starting at the 
	// root ProvenanceEntity
	private static ProvenanceEntity copyProvenanceEntity(ProvenanceEntity prov) {
		ProvenanceEntity copiedProv = new ProvenanceEntity();
		if (null != prov.getUri()){
			copiedProv.setUri(new String(prov.getUri()));
		}
		if (null != prov.getProperties()){
			copiedProv.setProperties(PropertyHelpers.copyProperties(prov.getProperties()));
		}
		if (null != prov.getCreationEvent()){
			copiedProv.setCreationEvent(copyProvenanceEvent(prov.getCreationEvent()));
		}
		return copiedProv;
	}
	
	private static ProvenanceEvent copyProvenanceEvent(ProvenanceEvent event){
		ProvenanceEvent copiedEvent = new ProvenanceEvent();
		if (null != event.getEventType()) {
			copiedEvent.setEventType(new String(event.getEventType()));
		}
		if (null != event.getProperties()){
			copiedEvent.setProperties(PropertyHelpers.copyProperties(event.getProperties()));
		}

		if (null != event.getEndedAtTime()) {
			copiedEvent.setEndedAtTime((Timestamp)event.getEndedAtTime().clone());
		}
		
		if (null != event.getStartedAtTime()) {
			copiedEvent.setStartedAtTime((Timestamp)event.getStartedAtTime().clone());
		}
		
		if (null != event.getInputs()){
			for (ProvenanceEntity input : event.getInputs()){
				if (null != input){
					copiedEvent.addInput(copyProvenanceEntity(input));
				}
			}
		}
		return copiedEvent;
	}

}
