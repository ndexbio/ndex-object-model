package org.ndexbio.cxio.aspects.readers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.NodesElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class NodesFragmentReader extends AbstractFragmentReader {

    public final static NodesFragmentReader createInstance() {
        return new NodesFragmentReader();
    }

    private NodesFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return NodesElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        return new NodesElement(ParserUtils.getTextValueRequiredAsLong(o, NodesElement.ID),
        		ParserUtils.getTextValue(o, NodesElement.NODE_NAME),
        		ParserUtils.getTextValue(o,NodesElement.NODE_REPRESENTS));
    }

}