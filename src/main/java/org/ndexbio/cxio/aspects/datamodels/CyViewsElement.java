package org.ndexbio.cxio.aspects.datamodels;

import java.io.IOException;

import org.ndexbio.cxio.util.JsonWriter;

public final class CyViewsElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3142005132518527096L;
	public final static String ASPECT_NAME   = "cyViews";
    public final static String SUBWORKNET_ID = "s";
    public final static String VIEW_ID       = "@id";
    private final Long         _subnetwork_id;
    private final Long         _view_id;

    public CyViewsElement(final Long view_id, final Long subnetwork_id) {
        if (view_id == null) {
            throw new IllegalArgumentException("view id must not be null");
        }
        if (subnetwork_id == null) {
            throw new IllegalArgumentException("sub-network id id must not be null");
        }
        _view_id = view_id;
        _subnetwork_id = subnetwork_id;
    }

    @Override
    public String getAspectName() {
        return ASPECT_NAME;
    }

    public Long getSubnetworkId() {
        return _subnetwork_id;
    }

    public Long getViewId() {
        return _view_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getViewId());
        sb.append("->");
        sb.append(getSubnetworkId());
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
	    final CyViewsElement e = this;
        w.writeStartObject();
        w.writeNumberField(CyViewsElement.VIEW_ID, e.getViewId());
        w.writeNumberField(CyViewsElement.SUBWORKNET_ID, e.getSubnetworkId());
        w.writeEndObject();	
        w.flush();
	}

}
