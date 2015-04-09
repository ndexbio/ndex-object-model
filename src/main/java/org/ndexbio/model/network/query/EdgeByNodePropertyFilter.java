package org.ndexbio.model.network.query;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class EdgeByNodePropertyFilter extends PropertyFilter {

	
	private SpecMatchMode _mode;
	public SpecMatchMode getMode() {
		return _mode;
	}
	public void setMode(SpecMatchMode mode) {
		this._mode = mode;
	}
	
}
