package org.cxio.aspects.datamodels;

import java.io.IOException;

import org.cxio.util.CxConstants;
import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is used to represent a node in a network.
 *
 * @author cmzmasek
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public final class NodesElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1305940218684987322L;
	public final static String ID              = CxConstants.ID;
    public final static String NODE_NAME       = "n";
    public final static String NODE_REPRESENTS = "r";
    public final static String ASPECT_NAME     = "nodes";
    
	@JsonProperty(CxConstants.ID)
    private  long         _id;

	@JsonProperty(NODE_NAME)
    private String        _node_name;
	
	@JsonProperty(NODE_REPRESENTS)
    private  String       _node_represents;

	
	public NodesElement () {
		_id = -1;
	}
	
    public NodesElement(final long id) {
        _id = id;
        _node_name = null;
        _node_represents = null;
    }

    public NodesElement(final String id) {
        _id = Long.parseLong(id);
        _node_name = null;
        _node_represents = null;
    }

    public NodesElement(final long id, final String node_name) {
        _id = id;
        _node_name = node_name;
        _node_represents = null;
    }

    public NodesElement(final String id, final String node_name) {
        _id = Long.parseLong(id);
        _node_name = node_name;
        _node_represents = null;
    }

    public NodesElement(final long id, final String node_name, final String node_represents) {
        _id = id;
        _node_name = node_name;
        _node_represents = node_represents;
    }

    public NodesElement(final String id, final String node_name, final String node_represents) {
        _id = Long.parseLong(id);
        _node_name = node_name;
        _node_represents = node_represents;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        return (o instanceof NodesElement) && (_id == ((NodesElement) o).getId());
    }

    @Override
	@JsonIgnore
    final public String getAspectName() {
        return NodesElement.ASPECT_NAME;
    }

	@JsonIgnore
    final public long getId() {
        return _id;
    }
    
    public void setId(long id) { _id = id; }

	@JsonIgnore
    public String getNodeName() {
        return _node_name;
    }
    
	@JsonIgnore
    final public String getNodeRepresents() {
        return _node_represents;
    }
    
    public void setNodeRepresents(String represents) {
    	_node_represents = represents;
    }
    
    final public void setNodeName( final String node_name ) {
        _node_name = node_name;
    }

    @Override
    public int hashCode() {
        return String.valueOf(_id).hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (_node_name != null) {
            sb.append(_node_name);
            sb.append(" ");
        }
        if (_node_represents != null) {
            sb.append("represents: ");
            sb.append(_node_represents);
            sb.append(" ");
        }
        sb.append("id: ");
        sb.append(_id);
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
	       w.writeStartObject();
	       w.writeNumberField(NodesElement.ID, _id);
	        if (_node_name != null) {
	            w.writeStringField(NodesElement.NODE_NAME, _node_name);
	        }
	        if (_node_represents != null) {
	            w.writeStringField(NodesElement.NODE_REPRESENTS, _node_represents);
	        }
	        w.writeEndObject();	 
	        w.flush();
	} 

}
