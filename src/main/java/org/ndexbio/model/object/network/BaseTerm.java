package org.ndexbio.model.object.network;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseTerm extends NetworkElement implements Comparable<BaseTerm> 
{
    private String _name;
    private long _namespace;
    
    
    
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

    public long getNamespace()
    {
        return _namespace;
    }

    public void setNamespace(long namespace)
    {
        _namespace = namespace;
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
