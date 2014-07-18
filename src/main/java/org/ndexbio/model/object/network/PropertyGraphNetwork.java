package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphNetwork {
   private UUID _uuid;
   // key is prefix, value is URI
   private Map<String,String> _namespaces;
   private Map<Long, String>  _citations;
   private Map<Long, String>  _supports;
   private Map<Long,PropertyGraphNode> _nodes;
   private Collection <PropertyGraphEdge> _edges;
   
   public PropertyGraphNetwork() {
	   setNamespaces(new HashMap<String,String> ());
	   setCitations(new HashMap<Long,String> ());
	   setSupports(new HashMap<Long,String> ());
	   setNodes(new HashMap<Long,PropertyGraphNode> ());
	   setEdges(new ArrayList<PropertyGraphEdge>  ());
   }

public UUID getUuid() {
	return _uuid;
}

public void setUuid(UUID _uuid) {
	this._uuid = _uuid;
}

public Map<String,String> getNamespaces() {
	return _namespaces;
}

public void setNamespaces(Map<String,String> _namespaces) {
	this._namespaces = _namespaces;
}

public Map<Long, String> getCitations() {
	return _citations;
}

public void setCitations(Map<Long, String> _citations) {
	this._citations = _citations;
}

public Map<Long, String> getSupports() {
	return _supports;
}

public void setSupports(Map<Long, String> _supports) {
	this._supports = _supports;
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
   
	
}
