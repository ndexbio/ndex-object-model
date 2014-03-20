package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReifiedEdgeTerm extends Term
{
    private String _termEdge;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public ReifiedEdgeTerm()
    {
        super();
        this.setTermType("ReifiedEdge");
    }

	public String getTermEdge() {
		return _termEdge;
	}

	public void setTermEdge(String termEdge) {
		this._termEdge = termEdge;
	}


}
