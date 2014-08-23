package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Namespace extends PropertiedNetworkElement
{
    private String _prefix;
    private String _uri;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Namespace()
    {
        super();
        _type = this.getClass().getSimpleName();
    }
      
    
    public String getPrefix()
    {
        return _prefix;
    }
    
    public void setPrefix(String prefix) //throws Exception
    {
//    	if ( prefix == null ) throw new Exception("Prefix for Namespace can't be null.");
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
