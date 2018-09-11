package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.EdgesElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

public class EdgesFragmentWriter extends AbstractFragmentWriter {

    public static EdgesFragmentWriter createInstance() {
        return new EdgesFragmentWriter();
    }

    private EdgesFragmentWriter() {
    }

    @Override
    public String getAspectName() {
        return EdgesElement.ASPECT_NAME;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final EdgesElement e = (EdgesElement) element;
        w.writeStartObject();
        w.writeNumberField(EdgesElement.ID, e.getId());
        w.writeNumberField(EdgesElement.SOURCE_NODE_ID, e.getSource());
        w.writeNumberField(EdgesElement.TARGET_NODE_ID, e.getTarget());
        w.writeStringFieldIfNotEmpty(EdgesElement.INTERACTION, e.getInteraction());
        w.writeEndObject();

    }

}
