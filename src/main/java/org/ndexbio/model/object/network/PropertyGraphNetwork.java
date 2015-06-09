/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.PropertiedObject;
import org.ndexbio.model.object.SimplePropertyValuePair;

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
