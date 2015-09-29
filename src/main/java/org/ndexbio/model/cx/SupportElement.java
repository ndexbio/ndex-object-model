package org.ndexbio.model.cx;


import java.util.Collection;

import org.cxio.core.interfaces.AspectElement;
import org.ndexbio.model.object.NdexPropertyValuePair;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SupportElement implements AspectElement{
	
	public static final String NAME = "supports";
	
	private static final String tField = "t";

	
	@JsonProperty ( tField )
	private String text;
	
	@JsonProperty( "citation")
	private String citationId;
	
	@JsonProperty( "@id")
	private String id;
	
	private Collection<NdexPropertyValuePair> props;
	
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


	public Collection<NdexPropertyValuePair> getProps() {
		return props;
	}


	public void setProps(Collection<NdexPropertyValuePair> props) {
		this.props = props;
	}

}
