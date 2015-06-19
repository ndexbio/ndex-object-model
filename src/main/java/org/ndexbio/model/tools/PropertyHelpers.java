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

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.SimplePropertyValuePair;
import org.ndexbio.model.object.network.BaseTerm;
import org.ndexbio.model.object.network.Citation;
import org.ndexbio.model.object.network.Edge;
import org.ndexbio.model.object.network.Namespace;
import org.ndexbio.model.object.network.Network;
import org.ndexbio.model.object.network.Node;

public class PropertyHelpers {

	public static List<SimplePropertyValuePair> copyProperties(List<SimplePropertyValuePair> properties) {
		List<SimplePropertyValuePair> copiedProperties = new ArrayList<>();
		for (SimplePropertyValuePair prop : properties){
			if (null != prop){
				copiedProperties.add(copyProperty(prop));
			}
		}		
		return copiedProperties;
	}
	
	public static SimplePropertyValuePair copyProperty(SimplePropertyValuePair property){
		if (null == property) return null;
		SimplePropertyValuePair copiedProperty = new SimplePropertyValuePair();
		
		
		//private String _predicateString;
		if (null != property.getName()){
			copiedProperty.setName(new String(property.getName()));
		}
		
		//private String _value;
		if (null != property.getValue()){
			copiedProperty.setValue(new String(property.getValue()));
		}
		
		return copiedProperty;
		
	}
	
	public static void setProperty(
			String propertyString, 
			String valueString, 
			List<SimplePropertyValuePair> properties){
		
		// if the property is found in a pair in the list, set the value
		for (SimplePropertyValuePair pair : properties){
			if (propertyString.equals(pair.getName())){
				pair.setValue(valueString);
				return;
			}
		}
		
		// if the property is not found, add it
		addProperty(propertyString, valueString, properties);	
	}
	
	public static void addProperty(
			String propertyString, 
			String valueString, 
			List<SimplePropertyValuePair> properties){
		
		properties.add(new SimplePropertyValuePair(propertyString, valueString));		
	}
	
	public static void addNdexProperty(String propertyString, String valueString,
			List<NdexPropertyValuePair> citationProperties) {
		citationProperties.add(new NdexPropertyValuePair(propertyString, valueString));
		
	}

	public static List<String> getNetworkPropertyValueStrings(Network network, String propertyName) {
		return getPropertyValueStrings(network, network.getProperties(), propertyName);
	}
	
	public static String getNetworkPropertyValueString(Network network, String propertyName) {
		return getFirst(getNetworkPropertyValueStrings(network, propertyName));
	}
	
	// TODO: make this error if there is more than one, call it getOnly ??
	private static String getFirst(
			List<String> strings) {
		if (strings != null && strings.size() > 0){
			return strings.get(0);
		}
		return null;
	}

	public static List<String> getCitationPropertyValueStrings(Network network, Citation citation, String propertyName){
		return getPropertyValueStrings(network, citation.getProperties(), propertyName);
	}
	
	public static String getCitationPropertyValueString(Network network, Citation citation, String propertyName) {
		return getFirst(getCitationPropertyValueStrings(network, citation, propertyName));
	}
	
	public static List<String> getNodePropertyValueStrings(Network network, Node node, String propertyName){
		return getPropertyValueStrings(network, node.getProperties(), propertyName);
	}
	
	public static String getNodePropertyValueString(Network network, Node node, String propertyName) {
		return getFirst(getNodePropertyValueStrings(network, node, propertyName));
	}
	
	public static List<String> getEdgePropertyValueStrings(Network network, Edge edge, String propertyName){
		return getPropertyValueStrings(network, edge.getProperties(), propertyName);
	}
	
	public static String getEdgePropertyValueString(Network network, Edge edge, String propertyName) {
		return getFirst(getEdgePropertyValueStrings(network, edge, propertyName));
	}
	
	public static List<String> getPropertyValueStrings(Network network, List<NdexPropertyValuePair> properties, String propertyName) {
		List<String> valueStrings = new ArrayList<>();
		for (NdexPropertyValuePair pvp : properties){
			String propertyString = getPropertyString(pvp, network);
			if (propertyString.equals(propertyName)){
				String valueString = getPropertyValueString(pvp, network);
				valueStrings.add(valueString);
			}
		}
		return valueStrings;
	}

	private static String getPropertyValueString(NdexPropertyValuePair pvp, Network network) {
		String propertyValueString = pvp.getValue();
		if (null == propertyValueString){
			Long valueId = pvp.getValueId();
			BaseTerm valueTerm = network.getBaseTerms().get(valueId);
			if (null != valueTerm){
				propertyValueString = getBaseTermString(valueTerm, network);
			}
		}
		return propertyValueString;
	}

	private static String getPropertyString(NdexPropertyValuePair pvp, Network network) {
		String propertyString = pvp.getPredicateString();
		if (null == propertyString){
			Long propertyId = pvp.getPredicateId();
			BaseTerm predicateTerm = network.getBaseTerms().get(propertyId);
			propertyString = getBaseTermString(predicateTerm, network);		
		}
		return propertyString;
	}
	
	private static String getBaseTermString(BaseTerm bt, Network network) {
		Long namespaceId = bt.getNamespaceId();
		if (null == namespaceId){
			return bt.getName();
		}
		Namespace ns = network.getNamespaces().get(namespaceId);
		if (null != ns){
			if (null != ns.getPrefix()){
				return ns.getPrefix() + ":" + bt.getName();
			}
			if (null != ns.getUri()){
				return ns.getUri() + bt.getName();
			}
		}
		return null;
	}



}
