package org.ndexbio.model.cx;

import java.util.List;

import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class functionTermsElement implements AspectElement {

	@JsonProperty( "po")
	private String nodeID;
	
	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	@JsonProperty( "f")
    private String functionName;
	
	
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@JsonProperty( "args")
    private List<Object> args;
	
	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}

	public functionTermsElement() {
	}

	@Override
	public String getAspectName() {
		return null;
	}

	@Override
	public long getSum() {
		return 0;
	}

}
