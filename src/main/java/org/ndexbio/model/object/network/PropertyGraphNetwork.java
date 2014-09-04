package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
   public static final String reifiedEdgeTerm = "NDEX:ReifedEdgeTerm";
   
   public PropertyGraphNetwork() {
	   setNodes(new HashMap<Long,PropertyGraphNode> ());
	   setEdges(new HashMap<Long, PropertyGraphEdge>  ());
	   _properties = new ArrayList<NdexPropertyValuePair>();
	   _presentationProperties = new ArrayList <SimplePropertyValuePair>();
   }


public Map<Long,PropertyGraphNode> getNodes() {
	return _nodes;
}

public void setNodes(Map<Long,PropertyGraphNode> _nodes) {
	this._nodes = _nodes;
}

public Map <Long, PropertyGraphEdge> getEdges() {
	return _edges;
}

public void setEdges(Map <Long,PropertyGraphEdge> _edges) {
	this._edges = _edges;
}

public List<NdexPropertyValuePair> getProperties() {
	return this._properties;
}

public List<SimplePropertyValuePair> getPresentationProperties() {
	return this._presentationProperties;
}

public void setProperties(List<NdexPropertyValuePair> properties) {
	this._properties = properties;
	
}

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
