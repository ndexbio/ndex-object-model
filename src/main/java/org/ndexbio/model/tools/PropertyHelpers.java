/**
 * Copyright (c) 2013, 2016, The Regents of the University of California, The Cytoscape Consortium
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

/*	public static List<String> getNetworkPropertyValueStrings(Network network, String propertyName) {
		return getPropertyValueStrings(network, network.getProperties(), propertyName);
	}
	
	public static String getNetworkPropertyValueString(Network network, String propertyName) {
		return getFirst(getNetworkPropertyValueStrings(network, propertyName));
	} */
	
/*	
 * //  make this error if there is more than one, call it getOnly ??
	private static String getFirst(
			List<String> strings) {
		if (strings != null && strings.size() > 0){
			return strings.get(0);
		}
		return null;
	} */
/*
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
	}*/


	
/*	BaseTerm class to be removed.
 * public static String getBaseTermString(BaseTerm bt, Network network) {
	
		if (bt.getNamespaceId() <= 0){
			return bt.getName();
		}
		
		Namespace ns = network.getNamespaces().get(bt.getNamespaceId());
		if (null != ns){
			if (null != ns.getPrefix()){
				return ns.getPrefix() + ":" + bt.getName();
			}
			if (null != ns.getUri()){
				return ns.getUri() + bt.getName();
			}
		}
		return null;
	} */

   /*
    public static String getTermStringInNetwork(Long termId, Network network) throws NdexException {
	
		  BaseTerm bt = network.getBaseTerms().get(termId) ;
		  if ( bt != null) {
		    return PropertyHelpers.getBaseTermString(bt, network);
		  } 
		  
	      FunctionTerm ft = network.getFunctionTerms().get(termId);
	      if ( ft != null) {
	    	  String baseTermStr = getBaseTermString(network.getBaseTerms().get(ft.getFunctionTermId()),network);
	    	  
	          String finalStr = baseTermStr + "(";
			  boolean isFirstArg = true;
              for ( Long paraId : ft.getParameterIds()) {
        			String argStr = getTermStringInNetwork(paraId, network);
        	
        			if ( isFirstArg) {
        				isFirstArg = false;
        			} else {
        				finalStr += ",";
        			}
        			finalStr += argStr;
        	  }
      		  finalStr += ")";
              return finalStr;
          }
	    	  
		  ReifiedEdgeTerm rt = network.getReifiedEdgeTerms().get(termId);
		  if ( rt != null) {
			  return  PropertyGraphNetwork.reifiedEdgeTerm + "("+rt.getEdgeId()+")";
		  }
		  
		  throw new NdexException ("Unsupported term type found for term Id:" + termId);
    
    } */


}
