package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Edge extends PropertiedNetworkElement 
{
    private long _objectId;
    private long _predicateId;
    private long _subjectId;
    private List<Long> _citations;
    private List<Long> _supports;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Edge()
    {
        super();
        _type = this.getClass().getSimpleName();
        this.initCollections();
    }

    /*
     * initialize class Collection fields
     */
    private void initCollections() {
    	this._citations = new ArrayList<Long>();
    	this._supports = new ArrayList<Long>();
    }


    public List<Long> getCitations()
    {
        return _citations;
    }

    public void setCitations(List<Long> citations)
    {
        _citations = citations;
    }

    public long getObjectId()
    {
        return _objectId;
    }

    public void setObjectId(long objectId)
    {
        _objectId = objectId;
    }

    public long getPredicateId()
    {
        return _predicateId;
    }

    public void setPredicateId(long predicateId)
    {
        _predicateId = predicateId;
    }
    
    public long getSubjectId()
    {
        return _subjectId;
    }

    public void setSubjectId(long subjectId)
    {
        _subjectId = subjectId;
    }
    
    public List<Long> getSupports()
    {
        return _supports;
    }

    public void setSupports(List<Long> supports)
    {
        _supports = supports;
    }

}
