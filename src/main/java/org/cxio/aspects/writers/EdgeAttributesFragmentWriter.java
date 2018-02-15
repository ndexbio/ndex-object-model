package org.cxio.aspects.writers;

import java.io.IOException;

import org.cxio.aspects.datamodels.EdgeAttributesElement;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.util.JsonWriter;

public class EdgeAttributesFragmentWriter extends AbstractFragmentWriter {

  //  private AspectKeyFilter _filter;

    public static EdgeAttributesFragmentWriter createInstance() {
        return new EdgeAttributesFragmentWriter();
    }

    private EdgeAttributesFragmentWriter() {
 //       _filter = null;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {

        WriterUtil.writeAttributesElement(w, (EdgeAttributesElement) element /*, _filter */);
    }

    @Override
    public String getAspectName() {
        return EdgeAttributesElement.ASPECT_NAME;
    }
/*
    @Override
    public void addAspectKeyFilter(final AspectKeyFilter filter) {
        _filter = filter;
    }
*/
}
