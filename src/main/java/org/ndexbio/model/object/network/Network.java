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
//        _type = this.getClass().getSimpleName();

        _edges = new HashMap<>(50);

        initCollections();
    }

    public Network(int edgeCount)
    {
        super();
//        _type = this.getClass().getSimpleName();

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
