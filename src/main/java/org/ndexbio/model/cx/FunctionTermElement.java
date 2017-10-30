/**
 * Copyright (c) 2013, 2016, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.cx;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FunctionTermElement extends NdexAspectElement {

	private static final long serialVersionUID = 1731775848803427957L;

	final public static String ASPECT_NAME    = "functionTerms";
	
	final private static String nodeId_prop = "po";
	final private static String funcName_prop = "f";
	final private static String args_prop = "args";

	@JsonProperty( nodeId_prop)
	private Long nodeID;
	
	@JsonProperty(funcName_prop)
    private String functionName;
	
	@JsonProperty( args_prop)
    private List<Object> args; // argument is either a String or a FunctionTermElement.
	
	
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

	@SuppressWarnings("unchecked")
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
