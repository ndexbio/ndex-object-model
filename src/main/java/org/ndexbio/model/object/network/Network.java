/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package org.ndexbio.model.object.network;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Network extends NetworkSummary 
{
    private Map<Long,Citation> _citations;
    private Map<Long, Edge> _edges;
    private Map<Long,Namespace> _namespaces;
    
    private Map<Long, Node> _nodes;
    private Map<Long, Support> _supports;
    private Map<Long, BaseTerm> _baseTerms;
    private Map<Long, FunctionTerm> _functionTerms;
    private Map<Long, ReifiedEdgeTerm> _reifiedEdgeTerms;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Network()
    {
        super();
        _type = this.getClass().getSimpleName();

        _edges = new HashMap<>(50);

        initCollections();
    }

    public Network(int edgeCount)
    {
        super();
        _type = this.getClass().getSimpleName();

        _edges = new HashMap<>(edgeCount);
        
        initCollections();
    }

    

    public Map<Long,Citation> getCitations()
    {
        return _citations;
    }

    public void setCitations(Map<Long,Citation> citations)
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

    public Map<Long,Namespace> getNamespaces()
    {
        return _namespaces;
    }

    public void setNamespaces( Map<Long,Namespace> namespaces)
    {
        _namespaces = namespaces;
    }

    public Map<Long,Node> getNodes()
    {
        return _nodes;
    }

    public void setNodes(Map<Long,Node> nodes)
    {
        _nodes = nodes;
    }
   
    public Map<Long, Support> getSupports()
    {
        return _supports;
    }

    public void setSupports(Map<Long, Support> supports)
    {
        _supports = supports;
    }

    

    /**************************************************************************
    * Initializes the collections. 
    **************************************************************************/
    private void initCollections()
    {
        _citations = new HashMap<>();
//        _members = new HashMap<Long,Membership>();
        _namespaces = new HashMap<>();
        _nodes = new TreeMap<>();
//        _requests = new HashMap<Long,Request>();
        _supports = new HashMap<>();
        _baseTerms = new HashMap<>(10);
        
        _functionTerms = new HashMap<>(10);
        _reifiedEdgeTerms = new HashMap<> (10);
        
    }


	public Map<Long,BaseTerm> getBaseTerms() {
		return _baseTerms;
	}


	public void setBaseTerms(Map<Long,BaseTerm> baseTerms) {
		this._baseTerms = baseTerms;
	}


	public Map<Long,FunctionTerm> getFunctionTerms() {
		return _functionTerms;
	}


	public void setFunctionTerms(Map<Long,FunctionTerm> functionTerms) {
		this._functionTerms = functionTerms;
	}


	public Map<Long,ReifiedEdgeTerm> getReifiedEdgeTerms() {
		return _reifiedEdgeTerms;
	}


	public void setReifiedEdgeTerms(Map<Long,ReifiedEdgeTerm> reifiedEdgeTerms) {
		this._reifiedEdgeTerms = reifiedEdgeTerms;
	}
}
