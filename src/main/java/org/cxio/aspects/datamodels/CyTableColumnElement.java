package org.cxio.aspects.datamodels;

import java.io.IOException;

import org.cxio.util.JsonWriter;

public class CyTableColumnElement extends AbstractAttributesAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1766588560652332381L;
	public final static String ASPECT_NAME = "cyTableColumn";
    public final static String APPLIES_TO  = "applies_to";
    private final String       _applies_to;

    @Override
    public String getAspectName() {
        return ASPECT_NAME;
    }

    public CyTableColumnElement(final Long subnetwork, final String applies_to, final String name, final ATTRIBUTE_DATA_TYPE type) {
        _applies_to = applies_to;
        _data_type = type;
        _subnetwork = subnetwork;
        _name = name;
        _values = null;
    }

    public CyTableColumnElement(final String applies_to, final String name, final ATTRIBUTE_DATA_TYPE type) {
        _applies_to = applies_to;
        _data_type = type;
        _subnetwork = null;
        _name = name;
        _values = null;
    }

    public final String getAppliesTo() {
        return _applies_to;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("name               : ");
        sb.append(_name);
        sb.append("\n");
        if (_subnetwork != null) {
            sb.append("property of network: ");
            sb.append(_subnetwork);
            sb.append("\n");
        }
        sb.append("applies to         : ");
        sb.append(_applies_to);
        sb.append("\n");
        sb.append("type               : ");
        sb.append(_data_type.toString());
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
	            final CyTableColumnElement te = this;
	            w.writeStartObject();
	            w.writeNumberFieldIfNotEmpty(AbstractAttributesAspectElement.ATTR_SUBNETWORK, te.getSubnetwork());
	            w.writeStringField(CyTableColumnElement.APPLIES_TO, te.getAppliesTo());
	            w.writeStringField(AbstractAttributesAspectElement.ATTR_NAME, te.getName());
	            if (te.getDataType() != ATTRIBUTE_DATA_TYPE.STRING) {
	                w.writeStringField(AbstractAttributesAspectElement.ATTR_DATA_TYPE, te.getDataType().toString());
	            }
	            w.writeEndObject();
	            w.flush();
	}

}
