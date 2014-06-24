package org.ndexbio.model.object.network;

import java.util.HashMap;
import java.util.Map;

import org.ndexbio.model.object.NdexObject;

@Deprecated
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
