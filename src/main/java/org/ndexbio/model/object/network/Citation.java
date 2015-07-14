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
public class Citation extends PropertiedNetworkElement implements Comparable<Citation> {
    private List<String> _contributors;
    private String _title;
    private String _identifier;
    private String _idType;
  //  private List<Support> _supports;


    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Citation()
    {
        super();
//        _type = this.getClass().getSimpleName();
        _contributors = new ArrayList<>();
 //       _supports = new ArrayList<Support>();
        
    }

    public List<String> getContributors()
    {
        return _contributors;
    }

    public void setContributors(List<String> contributors)
    {
        _contributors = contributors;
    }

    /*
    public List<Support> getSupports()
    {
        return _supports;
    }
    
    public void setSupports(List<Support> supports)
    {
        _supports = supports;
    }
*/
    public String getTitle()
    {
        return _title;
    }

    public void setTitle(String title)
    {
        _title = title;
    }

	public String getIdentifier() {
		return _identifier;
	}

	public void setIdentifier(String identifier) {
		this._identifier = identifier;
	}

	public String getIdType() {
		return _idType;
	}

	public void setIdType(String idType) {
		this._idType = idType;
	}

	@Override
	public int compareTo(Citation o) {
		long i = this.getId() - o.getId();
		if ( i > 0) return 1;
		if ( i < 0 ) return -1;
		return 0;
	}

    
}
