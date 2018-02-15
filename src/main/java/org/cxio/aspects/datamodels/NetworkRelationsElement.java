package org.cxio.aspects.datamodels;

import java.io.IOException;

import org.cxio.util.CxioUtil;
import org.cxio.util.JsonWriter;

/**
 * This is used to represent hierarchical relationship between networks/sub-networks/views.
 *
 *
 * @author cmzmasek
 *
 */
public final class NetworkRelationsElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3906606304503606487L;

	private enum RELATIONSHIP_TYPE {
        SUBNETWORK, VIEW;
    }

    final public static String      CHILD           = "c";
    final public static String      ASPECT_NAME     = "cyNetworkRelations"; //"networkRelations" is the old aspectName;
    final public static String      PARENT          = "p";
    final public static String      TYPE_SUBNETWORK = "subnetwork";
    final public static String      RELATIONSHIP    = "r";
    final public static String      CHILD_NAME      = "name";
    final public static String      TYPE_VIEW       = "view";

    final private Long              _child;
    final private Long              _parent;
    final private RELATIONSHIP_TYPE _relationship;
    final private String            _child_name;

    public NetworkRelationsElement(final Long parent, final Long child, final String relationship, final String child_name) throws IOException {
        _parent = parent;
        _child = child;
        _relationship = determineRelationship(relationship);
        _child_name = child_name;
    }

    public NetworkRelationsElement(final Long child, final String relationship, final String child_name) throws IOException {
        _parent = null;
        _child = child;
        _relationship = determineRelationship(relationship);
        _child_name = child_name;
    }

    public NetworkRelationsElement(final Long child, final String child_name) {
        _parent = null;
        _child = child;
        _relationship = RELATIONSHIP_TYPE.SUBNETWORK;
        _child_name = child_name;
    }

    @Override
    public String getAspectName() {
        return ASPECT_NAME;
    }

    public final Long getChild() {
        return _child;
    }

    public final Long getParent() {
        return _parent;
    }

    public final String getChildName() {
        return _child_name;
    }

    public final String getRelationship() {
        switch (_relationship) {
        case SUBNETWORK:
            return TYPE_SUBNETWORK;
        case VIEW:
            return TYPE_VIEW;
        default:
            throw new IllegalStateException("don't know how to handle relationship '" + _relationship + "'");
        }
    }

    private final static RELATIONSHIP_TYPE determineRelationship(final String type) throws IOException {
        if (CxioUtil.isEmpty(type)) {
            return RELATIONSHIP_TYPE.SUBNETWORK;
        }
        else if (type.equalsIgnoreCase(TYPE_SUBNETWORK)) {
            return RELATIONSHIP_TYPE.SUBNETWORK;
        }
        else if (type.equalsIgnoreCase(TYPE_VIEW)) {
            return RELATIONSHIP_TYPE.VIEW;
        }
        else {
            throw new IOException("unknown relationship type '" + type + "'");
        }
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(_parent);
        sb.append("<-");
        sb.append(_child);
        sb.append(": ");
        sb.append(_child_name);
        sb.append(" [");
        sb.append(_relationship);
        sb.append("]");
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
        final NetworkRelationsElement e = this;

		   w.writeStartObject();
	        w.writeNumberFieldIfNotEmpty(NetworkRelationsElement.PARENT, e.getParent());
	        w.writeNumberField(NetworkRelationsElement.CHILD, e.getChild());
	        w.writeStringFieldIfNotEmpty(NetworkRelationsElement.RELATIONSHIP, e.getRelationship());
	        w.writeStringField(NetworkRelationsElement.CHILD_NAME, e.getChildName());
	        w.writeEndObject();
	        w.flush();
		
	}
}
