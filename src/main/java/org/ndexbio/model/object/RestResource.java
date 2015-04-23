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
