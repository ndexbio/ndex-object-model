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
package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ndexbio.model.exceptions.NdexException;
import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.PropertiedObject;
import org.ndexbio.model.object.SimplePropertyValuePair;
import org.ndexbio.model.tools.PropertyHelpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphNetwork implements PropertiedObject{

   private Map<Long,PropertyGraphNode> _nodes;
   private Map <Long, PropertyGraphEdge> _edges;
   private List<NdexPropertyValuePair> _properties;
   private List<SimplePropertyValuePair> _presentationProperties;

   public static final String uuid      = "NDEX:UUID";
   public static final String namspaces = "NDEX:namespaces";
   public static final String citations = "NDEX:citations";
   public static final String supports  = "NDEX:supports";
   public static final String name      = "dc:title";
   public static final String version   = "NDEX:version";
   public static final String description = "dc:description";
   
   public static final String function = "FUNCTION";
   public static final String reifiedEdgeTerm = "NDEX:ReifiedEdgeTerm";
   
   public PropertyGraphNetwork() {
	   setNodes(new HashMap<Long,PropertyGraphNode> ());
	   setEdges(new HashMap<Long, PropertyGraphEdge>  ());
	   _properties = new ArrayList<>();
	   _presentationProperties = new ArrayList <>();
   }


   public PropertyGraphNetwork (Network network) throws NdexException {
	   //copy network profiles and properties

	   _properties = new ArrayList<>(network.getProperties().size() + 15);

       _properties.add(new NdexPropertyValuePair(PropertyGraphNetwork.uuid,
    		                    network.getExternalId().toString()));
       
       _properties.add(new NdexPropertyValuePair(
       		PropertyGraphNetwork.name, network.getName()));
       
       String desc = network.getDescription();
       if ( desc != null && !desc.equals("")) 
       	 _properties.add(new NdexPropertyValuePair(PropertyGraphNetwork.description, desc));
       
       String v =network.getVersion();
       if ( v != null) 
       	 _properties.add(new NdexPropertyValuePair(PropertyGraphNetwork.version, v));
       
       for ( NdexPropertyValuePair p : network.getProperties()) {
			_properties.add( p);	
       }

       // copy nodes
       for ( Node n : network.getNodes().values()) {
    	   PropertyGraphNode gn = new PropertyGraphNode();
    	   gn.setId(n.getId());
    	   
    	   if ( n.getName() != null)
    		   gn.setName(n.getName());
    	   
    	   Long repId = n.getRepresents();
    	   if ( repId !=null) {
    		   String termStr = PropertyHelpers.getTermStringInNetwork(repId, network);
    		   gn.getProperties().add(new NdexPropertyValuePair(PropertyGraphNode.represents, termStr)); 
    		  
    	   }
    	   
    	   for ( Long alias : n.getAliases()) {
    		  BaseTerm bt = network.getBaseTerms().get(alias) ;
     		  String aStr = PropertyHelpers.getBaseTermString(bt, network);
     		  gn.getProperties().add(new NdexPropertyValuePair(PropertyGraphNode.represents, aStr));   
    	   }
    	   
    	   for ( Long relatedT : n.getRelatedTerms()) {
     		  BaseTerm bt = network.getBaseTerms().get(relatedT) ;
      		  String rStr = PropertyHelpers.getBaseTermString(bt, network);
      		  gn.getProperties().add(new NdexPropertyValuePair(PropertyGraphNode.represents, rStr));   
     	   }
    	       	   
    	   for ( NdexPropertyValuePair p: n.getProperties()) {
    		   gn.getProperties().add(p);
    	   }
    	   
    	   _nodes.put(gn.getId(), gn);
       }
       
      // copy edges
       for ( Edge e : network.getEdges().values()) {
   		  PropertyGraphEdge ge = new PropertyGraphEdge();
   		  ge.setId(e.getId());

   		  ge.setPredicate(PropertyHelpers.getBaseTermString(network.getBaseTerms().get(e.getPredicateId()), network));

   		  ge.setSubjectId(e.getSubjectId());
          ge.setObjectId(e.getObjectId());

          for ( NdexPropertyValuePair p: e.getProperties()) {
 		     ge.getProperties().add(p);
 	      }
          
          _edges.put(ge.getId(), ge);
       }
   }
   
public Map<Long,PropertyGraphNode> getNodes() {
	return _nodes;
}

public void setNodes(Map<Long,PropertyGraphNode> nodes) {
	this._nodes = nodes;
}

public Map <Long, PropertyGraphEdge> getEdges() {
	return _edges;
}

public void setEdges(Map <Long,PropertyGraphEdge> edges) {
	this._edges = edges;
}

@Override
public List<NdexPropertyValuePair> getProperties() {
	return this._properties;
}

@Override
public List<SimplePropertyValuePair> getPresentationProperties() {
	return this._presentationProperties;
}

@Override
public void setProperties(List<NdexPropertyValuePair> properties) {
	this._properties = properties;
	
}

@Override
public void setPresentationProperties(List<SimplePropertyValuePair> properties) {
	this._presentationProperties = properties;
	
}
   

  /**
   *  Get the network title.
   * @return
   */
  public String getName () {
	  for ( NdexPropertyValuePair p : _properties) {
		  if ( p.getPredicateString().equals(name))
			  return p.getValue();
	  }
	  return null;
  }
  
  /**
   * Set the network title.
   * @param networkName the new network title.
   */
  public void setName(String networkName) {
	  for ( NdexPropertyValuePair p : _properties) {
		  if ( p.getPredicateString().equals(name)) {
			  p.setValue(networkName);
			  return;
		  }   
	  }
	  NdexPropertyValuePair p = new NdexPropertyValuePair(name, networkName);
	  _properties.add(p);
  }
	
}
