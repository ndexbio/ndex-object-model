/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node extends PropertiedNetworkElement
{
    private String _name;
    private Long _representsId;
    private String _representsTermType;
    private List<Long> _aliasIds;
    private List<Long> _relatedTermIds;
    private List<Long> _citationIds;
    private List<Long> _supportIds;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Node()
    {
        super();
        _representsId = null;
        _type = this.getClass().getSimpleName();
        this.initializeCollections();
    }
    

    
  
    /*
     * initialize class Collection fields
     */
    private void initializeCollections() {
    	this._aliasIds = new ArrayList<>();
    	this._relatedTermIds = new ArrayList<>();
    	this._supportIds = new ArrayList<>();
    	this._citationIds = new ArrayList<>();
    }
    
    public List<Long> getAliases()
    {
        return _aliasIds;
    }

    public void setAliases(List<Long> aliases)
    {
        _aliasIds = aliases;
    }

    public String getName()
    {
        return _name;
    }
    
    public void setName(String name)
    {
        _name = name;
    }

    public List<Long> getRelatedTerms()
    {
        return _relatedTermIds;
    }

    public void setRelatedTerms(List<Long> relatedTerms)
    {
        _relatedTermIds = relatedTerms;
    }
    
    public Long getRepresents()
    {
        return _representsId;
    }
    
    public void setRepresents(Long representsId)
    {
        _representsId = representsId;
    }




	public List<Long> getCitationIds() {
		return _citationIds;
	}




	public void setCitationIds(List<Long> _citations) {
		this._citationIds = _citations;
	}




	public List<Long> getSupportIds() {
		return _supportIds;
	}




	public void setSupportIds(List<Long> _supports) {
		this._supportIds = _supports;
	}




	public String getRepresentsTermType() {
		return _representsTermType;
	}




	public void setRepresentsTermType(String representsTermType) {
		this._representsTermType = representsTermType;
	}

    
}
