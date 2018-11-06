package org.ndexbio.cxio.aspects.readers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.CyViewsElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Deprecated
public class CyViewsFragmentReader extends AbstractFragmentReader {

    public final static CyViewsFragmentReader createInstance() {
        return new CyViewsFragmentReader();
    }

    private CyViewsFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return CyViewsElement.ASPECT_NAME;
    }

    @Override
    public AspectElement readElement(final ObjectNode o) throws IOException {
        return new CyViewsElement(ParserUtils.getTextValueRequiredAsLong(o, CyViewsElement.SUBWORKNET_ID), ParserUtils.getTextValueRequiredAsLong(o, CyViewsElement.VIEW_ID));
    }

}
