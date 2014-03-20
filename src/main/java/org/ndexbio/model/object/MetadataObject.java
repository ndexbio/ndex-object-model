package org.ndexbio.model.object;

import java.util.HashMap;
import java.util.Map;

public class MetadataObject extends NdexObject
{
    private Map<String, String> _metadata;

    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public MetadataObject()
    {
        super();
        
        _metadata = new HashMap<String, String>();

    }
  
    
    
    public Map<String, String> getMetadata()
    {
        return _metadata;
    }
    
    public void setMetadata(Map<String, String> metadata)
    {
        _metadata = metadata;
    }

}
