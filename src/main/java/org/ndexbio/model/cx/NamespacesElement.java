package org.ndexbio.model.cx;

import java.util.HashMap;
import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NamespacesElement extends HashMap<String,String> implements AspectElement{

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
	public int compareTo(AspectElement o) {
	       if ((o != null) && (o.getAspectName() != null) && (getAspectName() != null)) {
	            return getAspectName().compareTo(o.getAspectName());
	        }
	        return 0;
	}

}
