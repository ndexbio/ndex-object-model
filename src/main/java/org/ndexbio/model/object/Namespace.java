package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Namespace extends MetadataObject
{
    private String _jdexId;
    private String _prefix;
    private String _uri;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Namespace()
    {
        super();
    }
      
    public String getJdexId()
    {
        return _jdexId;
    }
    
    public void setJdexId(String jdexId)
    {
        _jdexId = jdexId;
    }
    
    public String getPrefix()
    {
        return _prefix;
    }
    
    public void setPrefix(String prefix)
    {
        _prefix = prefix;
    }
    
    public String getUri()
    {
        return _uri;
    }
    
    public void setUri(String uri)
    {
        _uri = uri;
    }
}
