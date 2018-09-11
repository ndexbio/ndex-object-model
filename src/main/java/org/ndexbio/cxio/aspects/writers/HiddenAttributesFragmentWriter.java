package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.HiddenAttributesElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

public class HiddenAttributesFragmentWriter extends AbstractFragmentWriter {

//    private AspectKeyFilter _filter;

    public static HiddenAttributesFragmentWriter createInstance() {
        return new HiddenAttributesFragmentWriter();
    }

    private HiddenAttributesFragmentWriter() {
//        _filter = null;
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {

        WriterUtil.writeAttributesElement(w, (HiddenAttributesElement) element /*, _filter*/);
    }

    @Override
    public String getAspectName() {
        return HiddenAttributesElement.ASPECT_NAME;
    }

    /*
    @Override
    public void addAspectKeyFilter(final AspectKeyFilter filter) {
        _filter = filter;
    }
 */
}
