/**
 * Copyright (c) 2013, 2015
 *  	The Regents of the University of California, The Cytoscape Consortium
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
package org.ndexbio.model.object;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//TODO: Remove this class, it's unnecessary
//TODO: Refactor KnockoutJS bindings to not use this class
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult<T> extends NdexObject
{
    private int _skip;
    private int _pageSize;
    private Collection<T> _results;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public SearchResult()
    {
        super();
    }



    public int getPageSize()
    {
        return _pageSize;
    }

    public void setPageSize(int pageSize)
    {
        _pageSize = pageSize;
    }

    public int getSkip()
    {
        return _skip;
    }

    public void setSkip(int skip)
    {
        _skip = skip;
    }

    public Collection<T> getResults()
    {
        return _results;
    }

    public void setResults(Collection<T> results)
    {
        _results = results;
    }
}
