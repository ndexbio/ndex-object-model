package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.CyViewsElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

@Deprecated
public class CyViewsFragmentWriter extends AbstractFragmentWriter {

    public static CyViewsFragmentWriter createInstance() {
        return new CyViewsFragmentWriter();
    }

    private CyViewsFragmentWriter() {
    }

    @Override
    public String getAspectName() {
        return CyViewsElement.ASPECT_NAME;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final CyViewsElement e = (CyViewsElement) element;
        w.writeStartObject();
        w.writeNumberField(CyViewsElement.VIEW_ID, e.getViewId());
        w.writeNumberField(CyViewsElement.SUBWORKNET_ID, e.getSubnetworkId());
        w.writeEndObject();
    }
}
