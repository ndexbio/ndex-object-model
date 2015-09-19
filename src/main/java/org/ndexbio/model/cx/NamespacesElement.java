package org.ndexbio.model.cx;

import java.util.HashMap;
import java.util.Map;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NamespacesElement extends HashMap<String,String> implements AspectElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NAME = "@context";
		
	public NamespacesElement() {
		super();
	}

	
	@Override
	@JsonIgnore
	public String getAspectName() {
		return NAME;
	}

	@Override
	@JsonIgnore
	public long getSum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
