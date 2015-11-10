package org.ndexbio.model.cx;


import java.util.Collection;
import java.util.LinkedList;

import org.cxio.aspects.datamodels.AbstractAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SupportElement extends AbstractAspectElement{
	
	public static final String ASPECT_NAME = "supports";
	
	private static final String tField = "text";

	
	@JsonProperty ( tField )
	private String text;
	
	@JsonProperty( "citation")
	private Long citationId;
	
	@JsonProperty( "@id")
	private long id;
	
	@JsonProperty( "attributes")
	private Collection<CXSimpleAttribute> props;
	
	public SupportElement() {
		props = new LinkedList<> ();
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Long getCitationId() {
		return citationId;
	}


	public void setCitationId(Long citationId) {
		this.citationId = citationId;
	}


	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Collection<CXSimpleAttribute> getProps() {
		return props;
	}


	public void setProps(Collection<CXSimpleAttribute> props) {
		this.props = props;
	}

}
