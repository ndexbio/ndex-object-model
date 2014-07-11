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
public class Network extends NetworkSummary implements PropertiedObject
{
    private List<Citation> _citations;
    private Map<Long, Edge> _edges;
    private List<Membership> _members;
    private Collection<Namespace> _namespaces;
    
    // list of Element ids of nodes
    private List<Long> _nodes;
    private List<Request> _requests;
    private Map<String, Support> _supports;
    private List<Long> _baseTermIds;
    private List<Long> _functionTerms;
    private List<Long> _reifiedEdgeTerms;


	private List<NdexProperty> _properties;
	private List<NdexProperty> _presentationProperties;

	

    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Network()
    {
        super();
        _type = this.getClass().getSimpleName();

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

     public Map<Long, Edge> getEdges()
    {
        return _edges;
    }

    public void setEdges(Map<Long, Edge> edges)
    {
        _edges = edges;
    }

   
    public List<Membership> getMembers()
    {
        return _members;
    }
    
    public void setMembers(List<Membership> members)
    {
        _members = members;
    }
    

    public Collection<Namespace> getNamespaces()
    {
        return _namespaces;
    }

    public void setNamespaces(Collection<Namespace> namespaces)
    {
        _namespaces = namespaces;
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
        
        _functionTerms = new ArrayList<Long>(10);
        _reifiedEdgeTerms = new ArrayList<Long> (10);
        
        _properties = new ArrayList<NdexProperty> (10);
    	_presentationProperties = new ArrayList<NdexProperty> (10);
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

/*
	public long getHighestElementId() {
		return _highestElementId;
	}



	public void setHighestElementId(long _highestElementId) {
		this._highestElementId = _highestElementId;
	}
*/


	public List<Long> getBaseTermIds() {
		return _baseTermIds;
	}




	public void setBaseTermIds(List<Long> _baseTerms) {
		this._baseTermIds = _baseTerms;
	}




	public List<Long> getFunctionTerms() {
		return _functionTerms;
	}




	public void setFunctionTerms(List<Long> _functionTerms) {
		this._functionTerms = _functionTerms;
	}




	public List<Long> getReifiedEdgeTerms() {
		return _reifiedEdgeTerms;
	}




	public void setReifiedEdgeTerms(List<Long> _reifiedEdgeTerms) {
		this._reifiedEdgeTerms = _reifiedEdgeTerms;
	}
}
