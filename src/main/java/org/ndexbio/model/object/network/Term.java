package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.ndexbio.model.helpers.TermDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = TermDeserializer.class)
@Deprecated
public abstract class Term extends MetadataObject
{
    private String _termType;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Term()
    {
    }  
    
    public String getTermType()
    {
        return _termType;
    }

    public void setTermType(String termType)
    {
        _termType = termType;
    }
}
