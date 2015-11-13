package org.ndexbio.model.cx;


import org.cxio.aspects.datamodels.AbstractAspectElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BELNamespaceElement extends AbstractAspectElement {

	final public static String ASPECT_NAME           = "BELNamespaceFiles";
	
	private String prefix;
	private String content; 

	public BELNamespaceElement() {
	}
	
	public BELNamespaceElement(String prefix, String fileContent) {
		this.prefix = prefix;
		this.content = fileContent;
	}
	

	@Override
	public String getAspectName() {
		return ASPECT_NAME;
	}



	public String getPrefix() {
		return prefix;
	}



	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}


}
