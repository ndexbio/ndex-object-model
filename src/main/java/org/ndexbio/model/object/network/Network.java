package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.ndexbio.model.object.Membership;
import org.ndexbio.model.object.NdexExternalObject;
import org.ndexbio.model.object.NdexProperty;
import org.ndexbio.model.object.PropertiedObject;
import org.ndexbio.model.object.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

//TODO: some members such as elements are not defined in the class yet.
public class Network extends NdexExternalObject implements PropertiedObject
{
    private List<Citation> _citations;
    private String _description;
    private int _edgeCount;
    private Map<Long, Edge> _edges;
    private boolean _isComplete;
    private boolean _isLocked;
    private VisibilityType _visibility;
    private List<Membership> _members;
    private String _name;
    private Collection<Namespace> _namespaces;
    private int _nodeCount;
    private List<Long> _nodes;
    private List<Request> _requests;
    private Map<String, Support> _supports;
    private List<Long> _baseTermIds;


	private List<NdexProperty> _properties;
	private List<NdexProperty> _presentationProperties;

	
	private long _highestElementId;
	private String _version;

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Network()
    {
        super();
        _type = this.getClass().getSimpleName();

        _isComplete = false;
        _isLocked = false;
        setVisibility(VisibilityType.PRIVATE);
        _edgeCount = 0;
        _nodeCount = 0;
        
        initCollections();
    }


    

    public List<Citation> getCitations()
    {
        return _citations;
    }

    public void setCitations(List<Citation> citations)
    {
        _citations = citations;
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

    public Map<Long, Edge> getEdges()
    {
        return _edges;
    }

    public void setEdges(Map<Long, Edge> edges)
    {
        _edges = edges;
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
    
   
    public List<Membership> getMembers()
    {
        return _members;
    }
    
    public void setMembers(List<Membership> members)
    {
        _members = members;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public void setName(String name)
    {
        _name = name;
    }

    public Collection<Namespace> getNamespaces()
    {
        return _namespaces;
    }

    public void setNamespaces(Collection<Namespace> namespaces)
    {
        _namespaces = namespaces;
    }

    public int getNodeCount()
    {
        return _nodeCount;
    }

    public void setNodeCount(int nodeCount)
    {
        _nodeCount = nodeCount;
    }

    public List<Long> getNodes()
    {
        return _nodes;
    }

    public void setNodes(List<Long> nodes)
    {
        _nodes = nodes;
    }
    
    public List<Request> getRequests()
    {
        return _requests;
    }
    
    public void setRequests(List<Request> requests)
    {
        _requests = requests;
    }

    public Map<String, Support> getSupports()
    {
        return _supports;
    }

    public void setSupports(Map<String, Support> supports)
    {
        _supports = supports;
    }

    

    /**************************************************************************
    * Initializes the collections. 
    **************************************************************************/
    private void initCollections()
    {
        _citations = new ArrayList<Citation>();
        _edges = new HashMap<Long, Edge>();
        _members = new ArrayList<Membership>();
        _namespaces = new HashSet<Namespace>();
        _nodes = new ArrayList<Long>(100);
        _requests = new ArrayList<Request>();
        _supports = new HashMap<String, Support>();
        _baseTermIds = new ArrayList<Long>(10);
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


	public VisibilityType getVisibility() {
		return _visibility;
	}


	public void setVisibility(VisibilityType _visibility) {
		this._visibility = _visibility;
	}


	public long getHighestElementId() {
		return _highestElementId;
	}



	public void setHighestElementId(long _highestElementId) {
		this._highestElementId = _highestElementId;
	}


	public String getVersion() {
		return _version;
	}


	public void setVersion(String _version) {
		this._version = _version;
	}


	public List<Long> getBaseTermIds() {
		return _baseTermIds;
	}




	public void setBaseTermIds(List<Long> _baseTerms) {
		this._baseTermIds = _baseTerms;
	}
}
