package org.ndexbio.cx2.aspect.element.core;

import java.io.IOException;
import java.util.Map;

import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CxVisualProperty implements AspectElement {


	@JsonProperty("at")
	private VisualPropertyType vtype;
	
	@JsonProperty("po")
	private Long propertyOf;
	
	@JsonProperty("v")
	private Map<String, Object> propTable;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String ASPECT_NAME = "visualProperties";
	
	@Override
	public int compareTo(AspectElement o) {
		return 0;
	}

	@Override
	public String getAspectName() {
		return ASPECT_NAME;
	}

	@Override
	public void write(JsonWriter out) throws IOException {
		// TODO Auto-generated method stub

	}

}
