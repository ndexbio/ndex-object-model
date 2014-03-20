package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Network extends MetadataObject
{
    private Map<String, Citation> _citations;
    private String _description;
    private int _edgeCount;
    private Map<String, Edge> _edges;
    private boolean _isComplete;
    private boolean _isLocked;
    private boolean _isPublic;
    private List<Membership> _members;
    private String _name;
    private Map<String, Namespace> _namespaces;
    private int _nodeCount;
    private Map<String, Node> _nodes;
    private List<Request> _requests;
    private Map<String, Support> _supports;
    private Map<String, Term> _terms;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Network()
    {
        super();

        _isComplete = false;
        _isLocked = false;
        _isPublic = false;
        _edgeCount = 0;
        _nodeCount = 0;
        
        initCollections();
    }


    

    public Map<String, Citation> getCitations()
    {
        return _citations;
    }

    public void setCitations(Map<String, Citation> citations)
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

    public Map<String, Edge> getEdges()
    {
        return _edges;
    }

    public void setEdges(Map<String, Edge> edges)
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
    
    public boolean getIsPublic()
    {
        return _isPublic;
    }
    
    public void setIsPublic(boolean isPublic)
    {
        _isPublic = isPublic;
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

    public Map<String, Namespace> getNamespaces()
    {
        return _namespaces;
    }

    public void setNamespaces(Map<String, Namespace> namespaces)
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

    public Map<String, Node> getNodes()
    {
        return _nodes;
    }

    public void setNodes(Map<String, Node> nodes)
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

    public Map<String, Term> getTerms()
    {
        return _terms;
    }

    public void setTerms(Map<String, Term> terms)
    {
        _terms = terms;
    }

    

    /**************************************************************************
    * Initializes the collections. 
    **************************************************************************/
    private void initCollections()
    {
        _citations = new HashMap<String, Citation>();
        _edges = new HashMap<String, Edge>();
        _members = new ArrayList<Membership>();
        _namespaces = new HashMap<String, Namespace>();
        _nodes = new HashMap<String, Node>();
        _requests = new ArrayList<Request>();
        _supports = new HashMap<String, Support>();
        _terms = new HashMap<String, Term>();
    }
}
