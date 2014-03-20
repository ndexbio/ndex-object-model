package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResource extends NdexObject
{
    private String _methodName;
    private Collection<String> _parameterTypes;
    private String _requestType;
    private String _path;
    private String _consumes;
    private String _produces;
    private String _apiDoc;
    private String _authentication;
    

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public RestResource()
    {
        super();
        this._parameterTypes = new ArrayList<String>();
    }

	public String getMethodName() {
		return _methodName;
	}

	public void setMethodName(String _methodName) {
		this._methodName = _methodName;
	}

	public Collection<String> getParameterTypes() {
		return _parameterTypes;
	}

	public void addParameterType(String _parameterType) {
		this._parameterTypes.add(_parameterType);
	}

	public void setParameterTypes(Collection<String> _parameterTypes) {
		this._parameterTypes = _parameterTypes;
	}

	public String getRequestType() {
		return _requestType;
	}

	public void setRequestType(String _requestType) {
		this._requestType = _requestType;
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String _path) {
		this._path = _path;
	}

	public String getConsumes() {
		return _consumes;
	}

	public void setConsumes(String _consumes) {
		this._consumes = _consumes;
	}

	public String getProduces() {
		return _produces;
	}
	
	public void setProduces(String _produces) {
		this._produces = _produces;
	}

	public String getApiDoc() {
		return _apiDoc;
	}

	public void setApiDoc(String _apiDoc) {
		this._apiDoc = _apiDoc;
	}

	public String getAuthentication() {
		return _authentication;
	}

	public void setAuthentication(String _authentication) {
		this._authentication = _authentication;
	}
	
	

}
