/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
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
package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResource extends NdexObject
{
    private String _methodName;
    private List<String> _parameterTypes;
    private String _requestType;
    private String _route;
    private String _data;
    private String _returns;
    private String _apiDoc;
    private boolean _authentication;
    

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public RestResource()
    {
        super();
        this._parameterTypes = new ArrayList<>();
        this._authentication = true;
    }

	public String getMethodName() {
		return _methodName;
	}

	public void setMethodName(String methodName) {
		this._methodName = methodName;
	}

	public List<String> getParameterTypes() {
		return _parameterTypes;
	}

	public void addParameterType(String _parameterType) {
		this._parameterTypes.add(_parameterType);
	}

	public void setParameterTypes(List<String> parameterTypes) {
		this._parameterTypes = parameterTypes;
	}

	public String getRequestType() {
		return _requestType;
	}

	public void setRequestType(String requestType) {
		this._requestType = requestType;
	}

	public String getPath() {
		return _route;
	}

	public void setPath(String _path) {
		this._route = _path;
	}

	public String getConsumes() {
		return _data;
	}

	public void setConsumes(String _consumes) {
		this._data = _consumes;
	}

	public String getProduces() {
		return _returns;
	}
	
	public void setProduces(String _produces) {
		this._returns = _produces;
	}

	public String getApiDoc() {
		return _apiDoc;
	}

	public void setApiDoc(String apiDoc) {
		this._apiDoc = apiDoc;
	}

	public boolean getAuthentication() {
		return _authentication;
	}

	public void setAuthentication(boolean authentication) {
		this._authentication = authentication;
	}
	
	

}
