package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node extends MetadataObject
{
    private String _name;
    private String _represents;
    private List<String> _aliases;
    private List<String> _relatedTerms;
    private List<String> _citations;
    private List<String> _supports;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Node()
    {
        super();
        this.initializeCollections();
    }
    

    
  
    /*
     * initialize class Collection fields
     */
    private void initializeCollections() {
    	this._aliases = new ArrayList<String>();
    	this._relatedTerms = new ArrayList<String>();
    	this._supports = new ArrayList<String>();
    	this._citations = new ArrayList<String>();
    }
    
    public List<String> getAliases()
    {
        return _aliases;
    }

    public void setAliases(List<String> aliases)
    {
        _aliases = aliases;
    }

    public String getName()
    {
        return _name;
    }
    
    public void setName(String name)
    {
        _name = name;
    }

    public List<String> getRelatedTerms()
    {
        return _relatedTerms;
    }

    public void setRelatedTerms(List<String> relatedTerms)
    {
        _relatedTerms = relatedTerms;
    }
    
    public String getRepresents()
    {
        return _represents;
    }
    
    public void setRepresents(String representsId)
    {
        _represents = representsId;
    }




	public List<String> getCitations() {
		return _citations;
	}




	public void setCitations(List<String> _citations) {
		this._citations = _citations;
	}




	public List<String> getSupports() {
		return _supports;
	}




	public void setSupports(List<String> _supports) {
		this._supports = _supports;
	}
    
    
}
