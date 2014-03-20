package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Citation extends MetadataObject
{
    private List<String> _contributors;
    private String _identifier;
    private String _title;
    private String _type;
    private List<String> _supports;



    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Citation()
    {
        super();

        _contributors = new ArrayList<String>();
        _supports = new ArrayList<String>();
    }

    public List<String> getContributors()
    {
        return _contributors;
    }

    public void setContributors(List<String> contributors)
    {
        _contributors = contributors;
    }

    public String getIdentifier()
    {
        return _identifier;
    }
    
    public void setIdentifier(String identifier)
    {
        _identifier = identifier;
    }
    
    public List<String> getSupports()
    {
        return _supports;
    }
    
    public void setSupports(List<String> supports)
    {
        _supports = supports;
    }

    public String getTitle()
    {
        return _title;
    }

    public void setTitle(String title)
    {
        _title = title;
    }

    public String getType()
    {
        return _type;
    }

    public void setType(String type)
    {
        _type = type;
    }
}
