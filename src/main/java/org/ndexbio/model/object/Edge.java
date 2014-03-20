package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Edge extends MetadataObject
{
    private String _objectId;
    private String _predicateId;
    private String _subjectId;
    private List<String> _citations;
    private List<String> _supports;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Edge()
    {
        super();
        this.initCollections();
    }

    /*
     * initialize class Collection fields
     */
    private void initCollections() {
    	this._citations = new ArrayList<String>();
    	this._supports = new ArrayList<String>();
    }


    public List<String> getCitations()
    {
        return _citations;
    }

    public void setCitations(List<String> citations)
    {
        _citations = citations;
    }

    public String getO()
    {
        return _objectId;
    }

    public void setO(String objectId)
    {
        _objectId = objectId;
    }

    public String getP()
    {
        return _predicateId;
    }

    public void setP(String predicateId)
    {
        _predicateId = predicateId;
    }
    
    public String getS()
    {
        return _subjectId;
    }

    public void setS(String subjectId)
    {
        _subjectId = subjectId;
    }
    
    public List<String> getSupports()
    {
        return _supports;
    }

    public void setSupports(List<String> supports)
    {
        _supports = supports;
    }
}
