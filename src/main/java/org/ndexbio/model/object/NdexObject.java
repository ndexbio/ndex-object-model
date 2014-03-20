package org.ndexbio.model.object;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NdexObject
{
    private String _id;
    private Date _createdDate;

    
    
    /**************************************************************************
    * Default constructor - initializes the created date. 
    **************************************************************************/
    public NdexObject()
    {
        _createdDate = new Date();
    }
    
    public Date getCreatedDate()
    {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        _createdDate = createdDate;
    }

    public String getId()
    {
        return _id;
    }

    public void setId(String id)
    {
        _id = id;
    }

}