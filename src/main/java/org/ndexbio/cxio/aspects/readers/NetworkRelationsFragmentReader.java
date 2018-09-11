package org.ndexbio.cxio.aspects.readers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.NetworkRelationsElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class NetworkRelationsFragmentReader extends AbstractFragmentReader {

    public static NetworkRelationsFragmentReader createInstance() {
        return new NetworkRelationsFragmentReader();
    }

    private NetworkRelationsFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return NetworkRelationsElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {

        return new NetworkRelationsElement(ParserUtils.getTextValueAsLong(o, NetworkRelationsElement.PARENT),
                                           ParserUtils.getTextValueRequiredAsLong(o, NetworkRelationsElement.CHILD),
                                           ParserUtils.getTextValue(o, NetworkRelationsElement.RELATIONSHIP),
                                           ParserUtils.getTextValueRequired(o, NetworkRelationsElement.CHILD_NAME));
    }
}
