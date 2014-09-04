package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexPropertyValuePair;
import org.ndexbio.model.object.PropertiedObject;

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
    	this._aliasIds = new ArrayList<Long>();
    	this._relatedTermIds = new ArrayList<Long>();
    	this._supportIds = new ArrayList<Long>();
    	this._citationIds = new ArrayList<Long>();
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




	public List<Long> getCitations() {
		return _citationIds;
	}




	public void setCitations(List<Long> _citations) {
		this._citationIds = _citations;
	}




	public List<Long> getSupports() {
		return _supportIds;
	}




	public void setSupports(List<Long> _supports) {
		this._supportIds = _supports;
	}




	public String getRepresentsTermType() {
		return _representsTermType;
	}




	public void setRepresentsTermType(String _representsTermType) {
		this._representsTermType = _representsTermType;
	}

    
}
