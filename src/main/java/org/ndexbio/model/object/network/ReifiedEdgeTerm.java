package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReifiedEdgeTerm extends Term
{
    private  long _edgeId;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public ReifiedEdgeTerm()
    {
        super();
        _type = this.getClass().getSimpleName();
    }

	public long getEdgeId() {
		return _edgeId;
	}

	public void setEdgeId(long termEdge) {
		this._edgeId = termEdge;
	}


}
