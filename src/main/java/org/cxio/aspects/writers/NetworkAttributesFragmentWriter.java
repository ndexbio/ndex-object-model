package org.cxio.aspects.writers;

import java.io.IOException;

import org.cxio.aspects.datamodels.NetworkAttributesElement;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.util.JsonWriter;

public class NetworkAttributesFragmentWriter extends AbstractFragmentWriter {

   // private AspectKeyFilter _filter;

    public static NetworkAttributesFragmentWriter createInstance() {
        return new NetworkAttributesFragmentWriter();
    }

    private NetworkAttributesFragmentWriter() {
//        _filter = null;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        WriterUtil.writeAttributesElement(w, (NetworkAttributesElement) element /*, _filter */);
    }

    @Override
    public String getAspectName() {
        return NetworkAttributesElement.ASPECT_NAME;
    }

/*    @Override
    public void addAspectKeyFilter(final AspectKeyFilter filter) {
        _filter = filter;
    } */

}
