package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphNode {
   private long _id;
   private String _name;
   private List<String> _represents;
   private List<String> _aliases;
   private List<String> _relatedTerms;
   
   public PropertyGraphNode () {
	   setRepresents(new ArrayList<String>());
	   setAliases(new ArrayList <String> ());
	   setRelatedTerms(new ArrayList<String>());
   }

public long getId() {
	return _id;
}

public void setId(long _id) {
	this._id = _id;
}

public String getName() {
	return _name;
}

public void setName(String _name) {
	this._name = _name;
}

public List<String> getRepresents() {
	return _represents;
}

public void setRepresents(List<String> _represents) {
	this._represents = _represents;
}

public List<String> getAliases() {
	return _aliases;
}

public void setAliases(List<String> _aliases) {
	this._aliases = _aliases;
}

public List<String> getRelatedTerms() {
	return _relatedTerms;
}

public void setRelatedTerms(List<String> _relatedTerms) {
	this._relatedTerms = _relatedTerms;
}
   
   
}
