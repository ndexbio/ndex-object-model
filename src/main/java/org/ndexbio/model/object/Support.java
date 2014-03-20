package org.ndexbio.model.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Support extends MetadataObject
{
    private String _jdexId;
    private String _text;
    private String _citation;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Support()
    {
        super();
    }


    public String getJdexId()
    {
        return _jdexId;
    }

    public void setJdexId(String jdexId)
    {
        _jdexId = jdexId;
    }

    public String getText()
    {
        return _text;
    }

    public void setText(String text)
    {
        _text = text;
    }

	public String getCitation() {
		return _citation;
	}

	public void setCitation(String _citation) {
		this._citation = _citation;
	}
    
    
}
