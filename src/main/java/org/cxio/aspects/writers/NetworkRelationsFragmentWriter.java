package org.cxio.aspects.writers;

import java.io.IOException;

import org.cxio.aspects.datamodels.NetworkRelationsElement;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.util.JsonWriter;

public class NetworkRelationsFragmentWriter extends AbstractFragmentWriter {

    public static NetworkRelationsFragmentWriter createInstance() {
        return new NetworkRelationsFragmentWriter();
    }

    private NetworkRelationsFragmentWriter() {
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final NetworkRelationsElement e = (NetworkRelationsElement) element;

        w.writeStartObject();
        w.writeNumberFieldIfNotEmpty(NetworkRelationsElement.PARENT, e.getParent());
        w.writeNumberField(NetworkRelationsElement.CHILD, e.getChild());
        w.writeStringFieldIfNotEmpty(NetworkRelationsElement.RELATIONSHIP, e.getRelationship());
        w.writeStringField(NetworkRelationsElement.CHILD_NAME, e.getChildName());
        w.writeEndObject();

    }

    @Override
    public String getAspectName() {
        return NetworkRelationsElement.ASPECT_NAME;
    }

}
