package org.cxio.aspects.readers;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.cxio.aspects.datamodels.Mapping;
import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class CyVisualPropertiesFragmentReader extends AbstractFragmentReader {

    public static CyVisualPropertiesFragmentReader createInstance() {
        return new CyVisualPropertiesFragmentReader();
    }

    private CyVisualPropertiesFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return CyVisualPropertiesElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        CyVisualPropertiesElement vpe;

        if (o.has(CyVisualPropertiesElement.APPLIES_TO) && (o.has(CyVisualPropertiesElement.VIEW))) {
            vpe = new CyVisualPropertiesElement(ParserUtils.getTextValueRequired(o, CyVisualPropertiesElement.PROPERTIES_OF),
                                                ParserUtils.getTextValueAsLong(o, CyVisualPropertiesElement.APPLIES_TO),
                                                ParserUtils.getTextValueAsLong(o, CyVisualPropertiesElement.VIEW));
        }
        else if (o.has(CyVisualPropertiesElement.APPLIES_TO)) {
            vpe = new CyVisualPropertiesElement(ParserUtils.getTextValueRequired(o, CyVisualPropertiesElement.PROPERTIES_OF), 
            			ParserUtils.getTextValueAsLong(o, CyVisualPropertiesElement.APPLIES_TO),null
            			/*ParserUtils.getTextValueAsLong(o, CyVisualPropertiesElement.APPLIES_TO)*/);
        }
        else {
            vpe = new CyVisualPropertiesElement(ParserUtils.getTextValueRequired(o, CyVisualPropertiesElement.PROPERTIES_OF));
        }
        if (o.has(CyVisualPropertiesElement.PROPERTIES)) {
            final Iterator<Entry<String, JsonNode>> it = o.get(CyVisualPropertiesElement.PROPERTIES).fields();
            if (it != null) {
                while (it.hasNext()) {
                    final Entry<String, JsonNode> kv = it.next();
                    vpe.putProperty(kv.getKey(), kv.getValue().asText());
                }

            }
        }
        if (o.has(CyVisualPropertiesElement.DEPENDENCIES)) {
            final Iterator<Entry<String, JsonNode>> it = o.get(CyVisualPropertiesElement.DEPENDENCIES).fields();
            if (it != null) {
                while (it.hasNext()) {
                    final Entry<String, JsonNode> kv = it.next();
                    vpe.putDependency(kv.getKey(), kv.getValue().asText());
                }

            }
        }
        if (o.has(CyVisualPropertiesElement.MAPPINGS)) {
            final Iterator<Entry<String, JsonNode>> it = o.get(CyVisualPropertiesElement.MAPPINGS).fields();
            if (it != null) {
                while (it.hasNext()) {
                    final Entry<String, JsonNode> kv = it.next();
                    vpe.putMapping(kv.getKey(), kv.getValue().get(Mapping.TYPE).asText(), kv.getValue().get(Mapping.DEFINITION).asText());
                }

            }
        }
        return vpe;
    }

}
