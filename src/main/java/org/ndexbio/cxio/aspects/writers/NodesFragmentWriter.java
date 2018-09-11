package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

public class NodesFragmentWriter extends AbstractFragmentWriter {

    public static NodesFragmentWriter createInstance() {
        return new NodesFragmentWriter();
    }

    private NodesFragmentWriter() {
    }

    @Override
    public String getAspectName() {
        return NodesElement.ASPECT_NAME;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final NodesElement e = (NodesElement) element;
        w.writeStartObject();
        w.writeNumberField(NodesElement.ID, e.getId());
        if (e.getNodeName() != null) {
            w.writeStringField(NodesElement.NODE_NAME, e.getNodeName());
        }
        if (e.getNodeRepresents() != null) {
            w.writeStringField(NodesElement.NODE_REPRESENTS, e.getNodeRepresents());
        }
        w.writeEndObject();
    }
}
