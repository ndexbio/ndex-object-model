package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.SubNetworkElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

public class SubNetworkFragmentWriter extends AbstractFragmentWriter {

    public static SubNetworkFragmentWriter createInstance() {
        return new SubNetworkFragmentWriter();
    }

    private SubNetworkFragmentWriter() {
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final SubNetworkElement e = (SubNetworkElement) element;
        w.writeStartObject();
        w.writeNumberFieldIfNotEmpty(SubNetworkElement.SUBNET_ID, e.getId());

        if (e.isNodesAll()) {
            w.writeStringField(SubNetworkElement.SUBNET_NODES, "all");
        }
        else {
            w.writeLongList(SubNetworkElement.SUBNET_NODES, e.getNodes());
        }
        if (e.isEdgesAll()) {
            w.writeStringField(SubNetworkElement.SUBNET_EDGES, "all");
        }
        else {
            w.writeLongList(SubNetworkElement.SUBNET_EDGES, e.getEdges());
        }
        w.writeEndObject();

    }

    @Override
    public String getAspectName() {
        return SubNetworkElement.ASPECT_NAME;
    }

}
