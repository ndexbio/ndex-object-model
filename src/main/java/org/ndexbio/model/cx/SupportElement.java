package org.ndexbio.model.cx;


import java.util.Collection;

import org.cxio.aspects.datamodels.AbstractAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SupportElement extends AbstractAspectElement{
	
	public static final String NAME = "supports";
	
	private static final String tField = "text";

	
	@JsonProperty ( tField )
	private String text;
	
	@JsonProperty( "citation")
	private String citationId;
	
	@JsonProperty( "@id")
	private String id;
	
	@JsonProperty( "attributes")
	private Collection<CXSimpleAttribute> props;
	
	public SupportElement() {
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getCitationId() {
		return citationId;
	}


	public void setCitationId(String citationId) {
		this.citationId = citationId;
	}


	@Override
	@JsonIgnore
	public String getAspectName() {
		return NAME;
	}


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
