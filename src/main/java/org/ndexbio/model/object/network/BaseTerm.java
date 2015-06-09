/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
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


	@Override
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
