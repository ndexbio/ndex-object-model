package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.CartesianLayoutElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.JsonWriter;

public class CartesianLayoutFragmentWriter extends AbstractFragmentWriter {

    public static CartesianLayoutFragmentWriter createInstance() {
        return new CartesianLayoutFragmentWriter();
    }

    private CartesianLayoutFragmentWriter() {
    }

    @Override
    public final void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        final CartesianLayoutElement c = (CartesianLayoutElement) element;
        w.writeStartObject();
        w.writeNumberField(CartesianLayoutElement.NODE, c.getNode());
        if (c.getView() !=null) {
            w.writeNumberField(CartesianLayoutElement.VIEW, c.getView());
        }
        w.writeNumberField(CartesianLayoutElement.X, Double.parseDouble(c.getX()));
        w.writeNumberField(CartesianLayoutElement.Y, Double.parseDouble(c.getY()));
        if (c.isZset()) {
            w.writeNumberField(CartesianLayoutElement.Z, Double.parseDouble(c.getZ()));
        }
        w.writeEndObject();
    }

    @Override
    public String getAspectName() {
        return CartesianLayoutElement.ASPECT_NAME;
    }

}
