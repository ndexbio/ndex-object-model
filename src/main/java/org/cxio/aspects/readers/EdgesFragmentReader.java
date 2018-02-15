package org.cxio.aspects.readers;

import java.io.IOException;

import org.cxio.aspects.datamodels.EdgesElement;
import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class EdgesFragmentReader extends AbstractFragmentReader {

    public final static EdgesFragmentReader createInstance() {
        return new EdgesFragmentReader();
    }

    private EdgesFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return EdgesElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        return new EdgesElement(ParserUtils.getTextValueRequired(o, EdgesElement.ID),
                                ParserUtils.getTextValueRequired(o, EdgesElement.SOURCE_NODE_ID),
                                ParserUtils.getTextValueRequired(o, EdgesElement.TARGET_NODE_ID),
                                ParserUtils.getTextValue(o, EdgesElement.INTERACTION));
    }

}
