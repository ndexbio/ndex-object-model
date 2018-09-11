package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.CyGroupsElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

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
        w.writeNumberFieldIfNotEmpty(CyGroupsElement.SUBNET, e.getSubNet());
        w.writeStringFieldIfNotEmpty(CyGroupsElement.GROUP_NAME, e.getName());
        w.writeBooleanField(CyGroupsElement.IS_COLLAPSED, e.isCollapsed());

        w.writeLongList(CyGroupsElement.NODES, e.getNodes());
        w.writeLongList(CyGroupsElement.EXTERNAL_EDGES, e.getExternalEdges());
        w.writeLongList(CyGroupsElement.INTERNAL_EDGES, e.getInternalEdges());
        w.writeEndObject();

    }

}
