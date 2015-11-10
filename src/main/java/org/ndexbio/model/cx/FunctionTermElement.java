package org.ndexbio.model.cx;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cxio.aspects.datamodels.AbstractAspectElement;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FunctionTermElement extends AbstractAspectElement {

	final public static String ASPECT_NAME    = "functionTerms";
	
	final private static String nodeId_prop = "po";
	final private static String funcName_prop = "f";
	final private static String args_prop = "args";

	@JsonProperty( nodeId_prop)
	private Long nodeID;
	
	@JsonProperty(funcName_prop)
    private String functionName;
	
	@JsonProperty( args_prop)
    private List<Object> args;
	
	
	public Long getNodeID() {
		return nodeID;
	}

	public void setNodeID(Long nodeID) {
		this.nodeID = nodeID;
	}

	public FunctionTermElement() {
		args = new LinkedList<> ();
	}
	
	public FunctionTermElement(Long nodeId, String functionName, List<Object> args) {
		this.nodeID = nodeId;
		this.functionName = functionName;
		this.args=args;
		
	}
	
	
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> arguments) throws NdexException {
		this.args = new ArrayList<> (arguments.size());
		for (Object arg : arguments) {
			if (arg instanceof String ) {
				args.add(arg);
			} else if ( arg instanceof Map<?,?>){
				Map<String, Object> arg2 = (Map<String, Object>)arg;
				Map<String,Object> m = arg2;
				FunctionTermElement f = new FunctionTermElement();
				f.setNodeID((Long)m.get(nodeId_prop));
				f.setFunctionName((String)m.get(funcName_prop));
				f.setArgs((List<Object>)m.get(args_prop));
				args.add(f);
			} else 
				throw new NdexException("wrong type of argument in the function term.");
		}

	}



	@Override
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}


}
