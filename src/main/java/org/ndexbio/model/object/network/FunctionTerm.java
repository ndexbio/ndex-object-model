package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionTerm extends NetworkElement
{
    private BaseTerm _termFunction;
    private List<Long> _parameters;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public FunctionTerm()
    {
        super();
        _type = this.getClass().getSimpleName();
        this._parameters = new ArrayList<Long>(10);
    }

    public List<Long> getParameters()
    {
        return _parameters;
    }

    public void setParameters(List<Long> parameters)
    {
        _parameters = parameters;
    }

    public BaseTerm getTermFunction()
    {
        return _termFunction;
    }

    public void setTermFunction(BaseTerm termFunction)
    {
        _termFunction = termFunction;
    }
}
