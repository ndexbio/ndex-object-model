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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Edge extends PropertiedNetworkElement 
{
    private long _objectId;
    private long _predicateId;
    private long _subjectId;
    private List<Long> _citationIds;
    private List<Long> _supportIds;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Edge()
    {
        super();
        _type = this.getClass().getSimpleName();
        this.initCollections();
    }

    /*
     * initialize class Collection fields
     */
    private void initCollections() {
    	this._citationIds = new ArrayList<>();
    	this._supportIds = new ArrayList<>();
    }


    public List<Long> getCitationIds()
    {
        return _citationIds;
    }

    public void setCitationIds(List<Long> citations)
    {
        _citationIds = citations;
    }

    public long getObjectId()
    {
        return _objectId;
    }

    public void setObjectId(long objectId)
    {
        _objectId = objectId;
    }

    public long getPredicateId()
    {
        return _predicateId;
    }

    public void setPredicateId(long predicateId)
    {
        _predicateId = predicateId;
    }
    
    public long getSubjectId()
    {
        return _subjectId;
    }

    public void setSubjectId(long subjectId)
    {
        _subjectId = subjectId;
    }
    
    public List<Long> getSupportIds()
    {
        return _supportIds;
    }

    public void setSupportIds(List<Long> supports)
    {
        _supportIds = supports;
    }

}
