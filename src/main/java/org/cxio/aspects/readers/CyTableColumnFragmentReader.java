package org.cxio.aspects.readers;

import java.io.IOException;

import org.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.cxio.aspects.datamodels.AttributesAspectUtils;
import org.cxio.aspects.datamodels.CyTableColumnElement;
import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class CyTableColumnFragmentReader extends AbstractFragmentReader {

    public static CyTableColumnFragmentReader createInstance() {
        return new CyTableColumnFragmentReader();
    }

    private CyTableColumnFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return CyTableColumnElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        ATTRIBUTE_DATA_TYPE type = ATTRIBUTE_DATA_TYPE.STRING;
        if (o.has(AbstractAttributesAspectElement.ATTR_DATA_TYPE)) {
            type = AttributesAspectUtils.toDataType(ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_DATA_TYPE));
        }
        return new CyTableColumnElement(ParserUtils.getTextValueAsLong(o, AbstractAttributesAspectElement.ATTR_SUBNETWORK),
                                        ParserUtils.getTextValueRequired(o, CyTableColumnElement.APPLIES_TO),
                                        ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_NAME),
                                        type);
    }
}
