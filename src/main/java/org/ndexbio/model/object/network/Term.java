package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonDeserialize(using = TermDeserializer.class)

public abstract class Term extends NetworkElement
{
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Term () {
    	super();
    }
	
    final public String getTermType()
    {
        return this.getType();
    } 

}
