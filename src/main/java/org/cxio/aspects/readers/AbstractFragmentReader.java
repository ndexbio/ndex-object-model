package org.cxio.aspects.readers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cxio.core.interfaces.AspectElement;
import org.cxio.core.interfaces.AspectFragmentReader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This abstract class is for simplifying the implementation of AspectFragmentReaders.
 *
 * @author cmzmasek
 *
 */
public abstract class AbstractFragmentReader implements AspectFragmentReader {

    protected ObjectMapper _m = null;

    protected AbstractFragmentReader() {
        _m = new ObjectMapper();
    }

    /**
     * This uses method readElement to parse an entire Json array into a list of AspectElements.
     *
     */
    @Override
    public List<AspectElement> readAspectFragment(final JsonParser jp) throws IOException {
        JsonToken t = jp.nextToken();
        if (t != JsonToken.START_ARRAY) {
            throw new IOException("malformed cx json in '" + getAspectName() + "'");
        }
        final List<AspectElement> elements = new ArrayList<>();
        while (t != JsonToken.END_ARRAY) {
            if (t == JsonToken.START_OBJECT) {
                final ObjectNode o = _m.readTree(jp);
                if (o == null) {
                    throw new IOException("malformed cx json in '" + getAspectName() + "'");
                }
                final AspectElement e = readElement(o);
                if (e != null) {
                    elements.add(e);
                }
            }
            t = jp.nextToken();
        }
        return elements;
    }

    @Override
    public int compareTo(final AspectFragmentReader o) {
        return getAspectName().compareTo(o.getAspectName());
    }

    @Override
    public boolean equals(final Object o) {
        if ((o != null) && (o instanceof AbstractFragmentReader)) {
            return getAspectName().equals(((AbstractFragmentReader) o).getAspectName());
        }
        return false;
    }

    /**
     * This is used for parsing one AspectElement from a ObjectNode.
     *
     *
     * @param o the ObjectNode to be parsed
     * @return an AspectElement
     * @throws IOException
     */
    @Override
    public abstract AspectElement readElement(final ObjectNode o) throws IOException;
}
