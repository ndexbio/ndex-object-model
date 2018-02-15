package org.cxio.aspects.readers;

import java.io.IOException;

import org.cxio.aspects.datamodels.CartesianLayoutElement;
import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class CartesianLayoutFragmentReader extends AbstractFragmentReader {

    public static CartesianLayoutFragmentReader createInstance() {
        return new CartesianLayoutFragmentReader();
    }

    private CartesianLayoutFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return CartesianLayoutElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        if (o.has(CartesianLayoutElement.Z)) {
            if (o.has(CartesianLayoutElement.VIEW)) {
                return new CartesianLayoutElement(ParserUtils.getTextValueRequiredAsLong(o, CartesianLayoutElement.NODE),
                                                  ParserUtils.getTextValueAsLong(o, CartesianLayoutElement.VIEW),
                                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.X),
                                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.Y),
                                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.Z));
            }
			return new CartesianLayoutElement(ParserUtils.getTextValueRequiredAsLong(o, CartesianLayoutElement.NODE),
											  null,
			                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.X),
			                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.Y),
			                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.Z));
        }
		if (o.has(CartesianLayoutElement.VIEW)) {
		    return new CartesianLayoutElement(ParserUtils.getTextValueRequiredAsLong(o, CartesianLayoutElement.NODE),
		                                      ParserUtils.getTextValueAsLong(o, CartesianLayoutElement.VIEW),
		                                      ParserUtils.getTextValueRequired(o, CartesianLayoutElement.X),
		                                      ParserUtils.getTextValueRequired(o, CartesianLayoutElement.Y));

		}
		return new CartesianLayoutElement(ParserUtils.getTextValueRequiredAsLong(o, CartesianLayoutElement.NODE),
		                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.X),
		                                  ParserUtils.getTextValueRequired(o, CartesianLayoutElement.Y));
    }

}
