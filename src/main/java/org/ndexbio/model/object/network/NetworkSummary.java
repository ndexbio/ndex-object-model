package org.ndexbio.model.object.network;


import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexExternalObject;
import org.ndexbio.model.object.NdexProperty;
import org.ndexbio.model.object.PropertiedObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class NetworkSummary extends NdexExternalObject implements PropertiedObject {

    private String _description;
    private int _edgeCount;
    private boolean _isComplete;
    private boolean _isLocked;
    private VisibilityType _visibility;
    private String _name;
    private int _nodeCount;
    
//	private long _highestElementId;
	private String _version;
	
	private List<NdexProperty> _properties;
	private List<NdexProperty> _presentationProperties;

	
	public NetworkSummary () {
		super();
        _type = this.getClass().getSimpleName();

        _isComplete = false;
        _isLocked = false;
        setVisibility(VisibilityType.PRIVATE);
        _edgeCount = 0;
        _nodeCount = 0;
        
        _properties = new ArrayList<NdexProperty> (10);
    	_presentationProperties = new ArrayList<NdexProperty> (10);

	}

    public String getDescription()
    {
        return _description;
    }
    
    public void setDescription(String description)
    {
        _description = description;
    }
    
    public int getEdgeCount()
    {
        return _edgeCount;
    }

    public void setEdgeCount(int edgeCount)
    {
        _edgeCount = edgeCount;
    }
	
	
	public String getVersion() {
		return _version;
	}


	public void setVersion(String _version) {
		this._version = _version;
	}


	public VisibilityType getVisibility() {
		return _visibility;
	}


	public void setVisibility(VisibilityType _visibility) {
		this._visibility = _visibility;
	}

    public boolean getIsComplete()
    {
        return _isComplete;
    }
    
    public void setIsComplete(boolean isComplete)
    {
        _isComplete = isComplete;
    }

    public boolean getIsLocked()
    {
        return _isLocked;
    }
    
    public void setIsLocked(boolean isLocked)
    {
        _isLocked = isLocked;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public void setName(String name)
    {
        _name = name;
    }

    public int getNodeCount()
    {
        return _nodeCount;
    }

    public void setNodeCount(int nodeCount)
    {
        _nodeCount = nodeCount;
    }

	public List<NdexProperty> getProperties() {
		return _properties;
	}

	public List<NdexProperty> getPresentationProperties() {
		return _presentationProperties;
	}

	public void setProperties(List<NdexProperty> properties) {
		_properties = properties;
	}

	public void setPresentationProperties(List<NdexProperty> properties) {
		_presentationProperties = properties;
	}


    
}
