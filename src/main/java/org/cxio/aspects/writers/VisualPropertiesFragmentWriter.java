package org.cxio.aspects.writers;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.cxio.aspects.datamodels.Mapping;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.util.JsonWriter;

public class VisualPropertiesFragmentWriter extends AbstractFragmentWriter {

    public static VisualPropertiesFragmentWriter createInstance() {
        return new VisualPropertiesFragmentWriter();
    }

    private VisualPropertiesFragmentWriter() {
    }

    @Override
    public String getAspectName() {
        return CyVisualPropertiesElement.ASPECT_NAME;
    }

    @Override
    public final void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final CyVisualPropertiesElement c = (CyVisualPropertiesElement) element;
        w.writeStartObject();
        w.writeStringField(CyVisualPropertiesElement.PROPERTIES_OF, c.getProperties_of());
        w.writeNumberFieldIfNotEmpty(CyVisualPropertiesElement.APPLIES_TO, c.getApplies_to());
        w.writeNumberFieldIfNotEmpty(CyVisualPropertiesElement.VIEW, c.getView());
        if ((c.getProperties() != null) && !c.getProperties().isEmpty()) {
            w.writeObjectFieldStart(CyVisualPropertiesElement.PROPERTIES);
            for (final Map.Entry<String, String> entry : c.getProperties().entrySet()) {
                if (entry.getValue() != null) {
                    w.writeStringField(entry.getKey(), entry.getValue());
                }
            }
            w.writeEndObject();
        }
        if ((c.getDependencies() != null) && !c.getDependencies().isEmpty()) {
            w.writeObjectFieldStart(CyVisualPropertiesElement.DEPENDENCIES);
            for (final Map.Entry<String, String> entry : c.getDependencies().entrySet()) {
                if (entry.getValue() != null) {
                    w.writeStringField(entry.getKey(), entry.getValue());
                }
            }
            w.writeEndObject();
        }
        if ((c.getMappings() != null) && !c.getMappings().isEmpty()) {
            w.writeObjectFieldStart(CyVisualPropertiesElement.MAPPINGS);
            for (final Entry<String, Mapping> entry : c.getMappings().entrySet()) {
                if (entry.getValue() != null) {
                    w.writeObjectFieldStart(entry.getKey());
                    final Mapping m = entry.getValue();
                    w.writeStringField(Mapping.TYPE, m.getType());
                    w.writeStringField(Mapping.DEFINITION, m.getDefintion());
                    w.writeEndObject();
                }
            }
            w.writeEndObject();
        }
        w.writeEndObject();
    }

}
