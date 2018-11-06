package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.ndexbio.cxio.aspects.datamodels.CyTableColumnElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

@Deprecated
public class CyTableColumnFragmentWriter extends AbstractFragmentWriter {

  //  private AspectKeyFilter _filter;

    public static CyTableColumnFragmentWriter createInstance() {
        return new CyTableColumnFragmentWriter();
    }

    private CyTableColumnFragmentWriter() {
    //    _filter = null;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        writeAttributesElement(w, (CyTableColumnElement) element /*_filter,*/);
    }

    @Override
    public String getAspectName() {
        return CyTableColumnElement.ASPECT_NAME;
    }
/*
    @Override
    public void addAspectKeyFilter(final AspectKeyFilter filter) {
        _filter = filter;
    }
*/
    private static final void writeAttributesElement(final JsonWriter w, final AbstractAttributesAspectElement e /*, final AspectKeyFilter filter*/) throws IOException {
 //       if ((filter == null) || filter.isPass(e.getName())) {
            final CyTableColumnElement te = (CyTableColumnElement) e;
            w.writeStartObject();
            w.writeNumberFieldIfNotEmpty(AbstractAttributesAspectElement.ATTR_SUBNETWORK, te.getSubnetwork());
            w.writeStringField(CyTableColumnElement.APPLIES_TO, te.getAppliesTo());
            w.writeStringField(AbstractAttributesAspectElement.ATTR_NAME, te.getName());
            if (te.getDataType() != ATTRIBUTE_DATA_TYPE.STRING) {
                w.writeStringField(AbstractAttributesAspectElement.ATTR_DATA_TYPE, te.getDataType().toString());
            }
            w.writeEndObject();
        }
 //   }

}
