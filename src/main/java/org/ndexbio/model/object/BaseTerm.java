package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseTerm extends Term
{
    private String _name;
    private String _namespace;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public BaseTerm()
    {
        super();
        
        this.setTermType("Base");
    }
    
    
    public String getName()
    {
        return _name;
    }

    public void setName(String termName)
    {
        _name = termName;
    }

    public String getNamespace()
    {
        return _namespace;
    }

    public void setNamespace(String namespace)
    {
        _namespace = namespace;
    }
}
