package org.cxio.aspects.writers;

import java.io.IOException;

import org.cxio.aspects.datamodels.NodeAttributesElement;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.util.JsonWriter;

public class NodeAttributesFragmentWriter extends AbstractFragmentWriter {

 //   private AspectKeyFilter _filter;

    public static NodeAttributesFragmentWriter createInstance() {
        return new NodeAttributesFragmentWriter();
    }

    private NodeAttributesFragmentWriter() {
   //     _filter = null;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        WriterUtil.writeAttributesElement(w, (NodeAttributesElement) element /*, _filter*/);
    }

    @Override
    public String getAspectName() {
        return NodeAttributesElement.ASPECT_NAME;
    }

 /*   @Override
    public void addAspectKeyFilter(final AspectKeyFilter filter) {
        _filter = filter;
    } */
}
