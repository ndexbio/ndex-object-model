package org.ndexbio.cxio.aspects.readers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.ndexbio.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.ndexbio.cxio.aspects.datamodels.AttributesAspectUtils;
import org.ndexbio.cxio.aspects.datamodels.NetworkAttributesElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class NetworkAttributesFragmentReader extends AbstractFragmentReader {

    public static NetworkAttributesFragmentReader createInstance() {
        return new NetworkAttributesFragmentReader();
    }

    private NetworkAttributesFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return NetworkAttributesElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        ATTRIBUTE_DATA_TYPE type = ATTRIBUTE_DATA_TYPE.STRING;
        if (o.has(AbstractAttributesAspectElement.ATTR_DATA_TYPE)) {
            type = AttributesAspectUtils.toDataType(ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_DATA_TYPE));
        }

        if (ParserUtils.isArray(o, AbstractAttributesAspectElement.ATTR_VALUES)) {
            return new NetworkAttributesElement(ParserUtils.getTextValueAsLong(o, AbstractAttributesAspectElement.ATTR_SUBNETWORK),
                                                ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_NAME),
                                                ParserUtils.getAsStringList(o, AbstractAttributesAspectElement.ATTR_VALUES),
                                                type);
        }
        return new NetworkAttributesElement(ParserUtils.getTextValueAsLong(o, AbstractAttributesAspectElement.ATTR_SUBNETWORK),
                                            ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_NAME),
                                            ParserUtils.getTextValue(o, AbstractAttributesAspectElement.ATTR_VALUES),
                                            type);

    }

}
