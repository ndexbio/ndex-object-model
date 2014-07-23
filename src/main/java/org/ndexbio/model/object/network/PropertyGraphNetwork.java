package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ndexbio.model.object.NdexProperty;
import org.ndexbio.model.object.PropertiedObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphNetwork implements PropertiedObject{

   private Map<Long,PropertyGraphNode> _nodes;
   private Collection <PropertyGraphEdge> _edges;
   private List<NdexProperty> _properties;
   private List<NdexProperty> _presentationProperties;

   public static final String uuid      = "NDEX:UUID";
   public static final String namspaces = "NDEX:namespaces";
   public static final String citations = "NDEX:citations";
   public static final String supports  = "NDEX:supports";
   public static final String name      = "NDEX:name";
   public static final String version   = "NDEX:version";
   public static final String description = "NDEX:description";
   
   public PropertyGraphNetwork() {
	   setNodes(new HashMap<Long,PropertyGraphNode> ());
	   setEdges(new ArrayList<PropertyGraphEdge>  ());
	   _properties = new ArrayList<NdexProperty>();
	   _presentationProperties = new ArrayList <NdexProperty>();
   }


public Map<Long,PropertyGraphNode> getNodes() {
	return _nodes;
}

public void setNodes(Map<Long,PropertyGraphNode> _nodes) {
	this._nodes = _nodes;
}

public Collection <PropertyGraphEdge> getEdges() {
	return _edges;
}

public void setEdges(Collection <PropertyGraphEdge> _edges) {
	this._edges = _edges;
}

public List<NdexProperty> getProperties() {
	return this._properties;
}

public List<NdexProperty> getPresentationProperties() {
	return this._presentationProperties;
}

public void setProperties(List<NdexProperty> properties) {
	this._properties = properties;
	
}

public void setPresentationProperties(List<NdexProperty> properties) {
	this._presentationProperties = properties;
	
}
   
	
}
