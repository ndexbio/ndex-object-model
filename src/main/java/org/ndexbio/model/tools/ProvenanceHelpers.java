/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.tools;

import java.sql.Timestamp;
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
