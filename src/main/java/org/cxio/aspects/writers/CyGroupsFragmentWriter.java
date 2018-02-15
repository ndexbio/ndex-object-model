package org.cxio.aspects.writers;

import java.io.IOException;

import org.cxio.aspects.datamodels.CyGroupsElement;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.util.JsonWriter;

public class CyGroupsFragmentWriter extends AbstractFragmentWriter {

    public static CyGroupsFragmentWriter createInstance() {
        return new CyGroupsFragmentWriter();
    }

    private CyGroupsFragmentWriter() {
    }

    @Override
    public String getAspectName() {
        return CyGroupsElement.ASPECT_NAME;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final CyGroupsElement e = (CyGroupsElement) element;
        w.writeStartObject();

        w.writeNumberFieldIfNotEmpty(CyGroupsElement.GROUP_ID, e.getGroupId());
        w.writeNumberFieldIfNotEmpty(CyGroupsElement.VIEW, e.getView());
        w.writeStringFieldIfNotEmpty(CyGroupsElement.GROUP_NAME, e.getName());

        if (e.isNodesAll()) {
            w.writeStringField(CyGroupsElement.NODES, "all");
        }
        else {
            w.writeLongList(CyGroupsElement.NODES, e.getNodes());
        }
        if (e.isExternalEdgesAll()) {
            w.writeStringField(CyGroupsElement.EXTERNAL_EDGES, "all");
        }
        else {
            w.writeLongList(CyGroupsElement.EXTERNAL_EDGES, e.getExternalEdges());
        }
        if (e.isInternalEdgesAll()) {
            w.writeStringField(CyGroupsElement.INTERNAL_EDGES, "all");
        }
        else {
            w.writeLongList(CyGroupsElement.INTERNAL_EDGES, e.getInternalEdges());
        }
        w.writeEndObject();

    }

}
