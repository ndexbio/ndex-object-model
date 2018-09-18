package org.ndexbio.cxio.aspects.datamodels;

import java.io.IOException;

import org.ndexbio.cxio.util.CxConstants;
import org.ndexbio.cxio.util.CxioUtil;
import org.ndexbio.cxio.util.JsonWriter;

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
     private Long         _id;

	@JsonProperty(SOURCE_NODE_ID)
     private Long         _source;
	
	@JsonProperty(TARGET_NODE_ID)
     private Long         _target;
	
	@JsonProperty(INTERACTION)
     private String       _interaction;

    
    public EdgesElement() { 
    	super();
    	_id = null; 
    	_source = null;
    	_target = null;
    	_interaction = null;
    }


    public EdgesElement(final Long id, final Long source, final Long target, final String interaction) {
        _id = id;
        _source = source;
        _target = target;
        _interaction = interaction;
    }


    public EdgesElement(final String id, final String source, final String target, final String interaction) {
        _id = Long.valueOf(id);
        _source = Long.valueOf(source);
        _target = Long.valueOf(target);
        _interaction = interaction;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        return (o instanceof EdgesElement) && (_id.equals((((EdgesElement) o).getId())));

    }

    @Override
	@JsonIgnore
    public String getAspectName() {
        return EdgesElement.ASPECT_NAME;
    }

    @JsonIgnore
    public final Long getId() {
        return _id;
    }
    
    public void setId(Long id) {
    	_id = id;
    }

    @JsonIgnore
    public final Long getSource() {
        return _source;
    }
    
    public void setSource(Long src) {
    	_source = src;
    }

    @JsonIgnore
    public final Long getTarget() {
        return _target;
    }
    
    public void setTarget(Long tgt) {
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
