package org.ndexbio.model.object.network;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseTerm extends Term implements Comparable<BaseTerm> 
{
    private String _name;
    private long _namespaceId;
    
    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public BaseTerm()
    {
        super();
        _type = this.getClass().getSimpleName();
    }
    
    
    public String getName()
    {
        return _name;
    }

    public void setName(String termName)
    {
        _name = termName;
    }

    public long getNamespaceId()
    {
        return _namespaceId;
    }

    public void setNamespaceId(long namespace)
    {
        _namespaceId = namespace;
    }


	public int compareTo(BaseTerm o) {
		long c = this.getId() - o.getId();
		if ( c==0)
			return 0;
		return c>0? 1 : -1;
		
	}
	
	@Override
	public int hashCode() {
		return (int)getId();
	}
	
	@Override
	public boolean equals (Object b2) {
		if (b2 instanceof BaseTerm)
			return compareTo((BaseTerm)b2)==0;
		return false;
	}
}
