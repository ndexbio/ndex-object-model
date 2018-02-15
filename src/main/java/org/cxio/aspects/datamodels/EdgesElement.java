package org.cxio.aspects.datamodels;

import java.io.IOException;

import org.cxio.util.CxConstants;
import org.cxio.util.CxioUtil;
import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to represent a edge in a network.
 *
 *
 * @author cmzmasek
 *
 */

public final class EdgesElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2375466043279507205L;
	public final static String ID             = CxConstants.ID;
    final public static String ASPECT_NAME    = "edges";
    public final static String SOURCE_NODE_ID = "s";
    public final static String TARGET_NODE_ID = "t";
    public final static String INTERACTION    = "i";
    
	@JsonProperty(CxConstants.ID)
     private long         _id;

	@JsonProperty(SOURCE_NODE_ID)
     private long         _source;
	
	@JsonProperty(TARGET_NODE_ID)
     private long         _target;
	
	@JsonProperty(INTERACTION)
     private String       _interaction;

    public EdgesElement(final long id, final long source, final long target) {
        _id = id;
        _source = source;
        _target = target;
        _interaction = null;
    }
    
    public EdgesElement() { 
    	super();
    	_id = -1; 
    	_source = -1;
    	_target = -1;
    	_interaction = null;
    }

    public EdgesElement(final long id, final String source, final String target) {
        _id = id;
        _source = Long.parseLong(source);
        _target = Long.parseLong(target);
        _interaction = null;
    }

    public EdgesElement(final String id, final String source, final String target) {
        _id = Long.parseLong(id);
        _source = Long.parseLong(source);
        _target = Long.parseLong(target);
        _interaction = null;
    }

    public EdgesElement(final long id, final long source, final long target, final String interaction) {
        _id = id;
        _source = source;
        _target = target;
        _interaction = interaction;
    }

    public EdgesElement(final long id, final String source, final String target, final String interaction) {
        _id = id;
        _source = Long.parseLong(source);
        _target = Long.parseLong(target);
        _interaction = interaction;
    }

    public EdgesElement(final String id, final String source, final String target, final String interaction) {
        _id = Long.parseLong(id);
        _source = Long.parseLong(source);
        _target = Long.parseLong(target);
        _interaction = interaction;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        return (o instanceof EdgesElement) && (_id == (((EdgesElement) o).getId()));

    }

    @Override
	@JsonIgnore
    public String getAspectName() {
        return EdgesElement.ASPECT_NAME;
    }

    @JsonIgnore
    public final long getId() {
        return _id;
    }
    
    public void setId(long id) {
    	_id = id;
    }

    @JsonIgnore
    public final long getSource() {
        return _source;
    }
    
    public void setSource(long src) {
    	_source = src;
    }

    @JsonIgnore
    public final long getTarget() {
        return _target;
    }
    
    public void setTarget(long tgt) {
    	_target = tgt;
    }

    @JsonIgnore
    public final String getInteraction() {
        return _interaction;
    }
    
    public void setInteraction(String interaction) {
    	_interaction = interaction;
    }

    @Override
    public int hashCode() {
        return String.valueOf(_id).hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(ASPECT_NAME);
        sb.append(": ");
        sb.append(getId());
        sb.append(" ");
        sb.append(getSource());
        sb.append("->");
        sb.append(getTarget());
        if (!CxioUtil.isEmpty(getInteraction())) {
            sb.append(" interaction: ");
            sb.append(getInteraction());
        }
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
	    final EdgesElement e = this;
        w.writeStartObject();
        w.writeNumberField(EdgesElement.ID, e.getId());
        w.writeNumberField(EdgesElement.SOURCE_NODE_ID, e.getSource());
        w.writeNumberField(EdgesElement.TARGET_NODE_ID, e.getTarget());
        w.writeStringFieldIfNotEmpty(EdgesElement.INTERACTION, e.getInteraction());
        w.writeEndObject();
		w.flush();
	}

}
