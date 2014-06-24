package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseTerm extends NetworkElement
{
    private String _name;
    private long _namespace;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public BaseTerm()
    {
        super();
        _type = this.getClass().getSimpleName();
    }
    
    
    public String getName()
    {
        return _name;
    }

    public void setName(String termName)
    {
        _name = termName;
    }

    public long getNamespace()
    {
        return _namespace;
    }

    public void setNamespace(long namespace)
    {
        _namespace = namespace;
    }
}
