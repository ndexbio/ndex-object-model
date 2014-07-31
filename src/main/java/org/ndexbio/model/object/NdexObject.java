package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NdexObject
{
 //   private String _id;
 //   private Date _createdDate;
    
    protected String _type;

    
    
    /**************************************************************************
    * Default constructor - initializes the created date. 
    **************************************************************************/
/*    public NdexObject()
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
    }  */
    
    public void setType (String type) { _type = type;};
    public String getType () { return _type;}

}