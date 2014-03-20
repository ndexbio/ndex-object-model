package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Account
{
    private String _emailAddress;
    private String _firstName;
    private String _lastName;
    private List<Membership> _groupMemberships;
    private List<Membership> _networkMemberships;
    private List<Request> _requests;
    private List<Task> _tasks;
    private String _username;
    private List<Network> _workSurface;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public User()
    {
        super();

        initCollections();
    }
    
 
    
    
    public String getEmailAddress()
    {
        return _emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        _emailAddress = emailAddress;
    }

    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName(String firstName)
    {
        _firstName = firstName;
    }

    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName(String lastName)
    {
        _lastName = lastName;
    }

    public List<Membership> getGroups()
    {
        return _groupMemberships;
    }

    public void setGroups(List<Membership> groupMemberships)
    {
        _groupMemberships = groupMemberships;
    }

    public List<Membership> getNetworks()
    {
        return _networkMemberships;
    }

    public void setNetworks(List<Membership> networkMemberships)
    {
        _networkMemberships = networkMemberships;
    }
    
    public List<Request> getRequests()
    {
        return _requests;
    }
    
    public void setRequests(List<Request> requests)
    {
        _requests = requests;
    }
    
    public List<Task> getTasks()
    {
        return _tasks;
    }
    
    public void setTasks(List<Task> tasks)
    {
        _tasks = tasks;
    }

    public String getUsername()
    {
        return _username;
    }

    public void setUsername(String username)
    {
        _username = username;
    }

    public List<Network> getWorkSurface()
    {
        return _workSurface;
    }
    
    public void setWorkSurface(List<Network> workSurface)
    {
        _workSurface = workSurface;
    }

    

    /**************************************************************************
    * Initializes the collections. 
    **************************************************************************/
    private void initCollections()
    {
        _groupMemberships = new ArrayList<Membership>();
        _networkMemberships = new ArrayList<Membership>();
        _requests = new ArrayList<Request>();
        _tasks = new ArrayList<Task>();
        _workSurface = new ArrayList<Network>();
    }
}
