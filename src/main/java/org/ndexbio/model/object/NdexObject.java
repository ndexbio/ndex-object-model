package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NdexObject
{
    protected String _type;

    public void setType (String type) { _type = type;}
    public String getType () { return _type;}

}