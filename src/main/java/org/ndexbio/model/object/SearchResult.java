/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
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
