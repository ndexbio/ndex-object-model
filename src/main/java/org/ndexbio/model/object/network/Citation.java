package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.model.object.NdexProperty;
import org.ndexbio.model.object.PropertiedObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Citation extends PropertiedNetworkElement {
    private List<String> _contributors;
    private String _title;
  //  private List<Support> _supports;


    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public Citation()
    {
        super();
        _type = this.getClass().getSimpleName();
        _contributors = new ArrayList<String>();
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

    
}
