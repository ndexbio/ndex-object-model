package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Citation extends PropertiedNetworkElement {
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

	public String getIdentifier() {
		return _identifier;
	}

	public void setIdentifier(String _identifier) {
		this._identifier = _identifier;
	}

	public String getIdType() {
		return _idType;
	}

	public void setIdType(String _idType) {
		this._idType = _idType;
	}

    
}
