/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionTerm extends Term implements Comparable <FunctionTerm> 
{
    private long _functionTermId;
    
    // element id list of other terms
    private List<Long> _parameterIds;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public FunctionTerm()
    {
        super();
//        _type = this.getClass().getSimpleName();
        this._parameterIds = new ArrayList<>(10);
    }

    public List<Long> getParameterIds()
    {
        return _parameterIds;
    }

    public void setParameterIds(List<Long> parameters)
    {
        _parameterIds = parameters;
    }


	public long getFunctionTermId() {
		return _functionTermId;
	}

	public void setFunctionTermId(long functionTermId) {
		this._functionTermId = functionTermId;
	}

	@Override
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
        
        List<Long> p2 = o.getParameterIds();
        int ci = _parameterIds.size() - p2.size();
        
        if (ci != 0) return ci;
        
        for ( int i = 0 ; i < _parameterIds.size(); i++ ) {
        	c = _parameterIds.get(i).longValue() - p2.get(i).longValue();
        	if ( c !=0) return c>0? 1 : -1; 
        }     
        
        return 0;
	}

	@Override
	public String getTermType() {
		return Term.functionTerm;
	}
}
