package org.ndexbio.model.object.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Support extends NetworkElement
{

	private String _text;
    private Citation _citation;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Support()
    {
        super();
        _type = this.getClass().getSimpleName();
    }



    public String getText()
    {
        return _text;
    }

    public void setText(String text)
    {
        _text = text;
    }

	public Citation getCitation() {
		return _citation;
	}

	public void setCitation(Citation _citation) {
		this._citation = _citation;
	}
    
    
}
