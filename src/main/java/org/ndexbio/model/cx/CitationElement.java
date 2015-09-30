package org.ndexbio.model.cx;

import java.util.Collection;

import org.cxio.core.interfaces.AspectElement;
import org.cxio.util.CxConstants;
import org.ndexbio.model.object.NdexPropertyValuePair;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)


public class CitationElement implements AspectElement {
    
	final public static String NAME           = "citations";
	
	@JsonProperty(CxConstants.ID)
	private String id ;
	
	@JsonProperty("dc:title")	
	private String title;
	
	@JsonProperty("dc:contributor")	
	private Collection<String> contributor;

	@JsonProperty("dc:identifier")	

	private String identifier;
	
	@JsonProperty("dc:type")	
	private String citationType;

	@JsonProperty("dc:description")	

	private String description;
	
	@JsonProperty("attributes")	

	private Collection<CXSimpleAttribute> props;
	
	public CitationElement() {
	}

	@Override
	@JsonIgnore
	public  String getAspectName() {
		return NAME;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection<String> getContributor() {
		return contributor;
	}

	public void setContributor(Collection<String> contributor) {
		this.contributor = contributor;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCitationType() {
		return citationType;
	}

	public void setCitationType(String citationType) {
		this.citationType = citationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
/*
	public Collection<String> getEdges() {
		return edges;
	}

	public void setEdges(Collection<String> edges) {
		this.edges = edges;
	}

	public Collection<String> getNodes() {
		return nodes;
	}

	public void setNodes(Collection<String> nodes) {
		this.nodes = nodes;
	}  */
/*
	public Collection<CXSupport> getSupports() {
		return supports;
	}

	public void setSupports(Collection<CXSupport> supports) {
		this.supports = supports;
	} */


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<CXSimpleAttribute> getProps() {
		return props;
	}

	public void setProps(Collection<CXSimpleAttribute> props) {
		this.props = props;
	}

}
