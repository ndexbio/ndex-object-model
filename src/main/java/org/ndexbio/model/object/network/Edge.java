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
    private List<Long> _citationIds;
    private List<Long> _supportIds;



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
    	this._citationIds = new ArrayList<Long>();
    	this._supportIds = new ArrayList<Long>();
    }


    public List<Long> getCitationIds()
    {
        return _citationIds;
    }

    public void setCitationIds(List<Long> citations)
    {
        _citationIds = citations;
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
    
    public List<Long> getSupportIds()
    {
        return _supportIds;
    }

    public void setSupportIds(List<Long> supports)
    {
        _supportIds = supports;
    }

}
