package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionTerm extends Term implements Comparable <FunctionTerm> 
{
    private long _functionTermId;
    
    // element id list of other terms
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


	public long getFunctionTermId() {
		return _functionTermId;
	}

	public void setFunctionTermId(long _functionTermId) {
		this._functionTermId = _functionTermId;
	}

	public int compareTo(FunctionTerm o) {
        if ( getId() > 0 ) {
        	long c = getId() - o.getId();
        	if ( c==0) return 0 ;
        	return c > 0? 1 : -1;
        }
		
        if (o.getId() > 0) return -1;
        
        // compare the contents
        long c = _functionTermId - o.getFunctionTermId();
        if ( c != 0 )
        	return c>0 ? 1 : -1;
        
        List<Long> p2 = o.getParameters();
        int ci = _parameters.size() - p2.size();
        
        if (ci != 0) return ci;
        
        for ( int i = 0 ; i < _parameters.size(); i++ ) {
        	c = _parameters.get(i) - p2.get(i);
        	if ( c !=0) return c>0? 1 : -1; 
        }     
        
        return 0;
	}
}
