package org.ndexbio.cxio.misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ndexbio.cxio.aspects.readers.AbstractFragmentReader;
import org.ndexbio.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *  This is for parsing "anonymous", general aspect element fragments.
 *
 * @author cmzmasek
 *
 */
public final class OpaqueFragmentReader extends AbstractFragmentReader {

    private boolean _is_list;
    private String  _name;

    public final static OpaqueFragmentReader createInstance() {
        return new OpaqueFragmentReader();
    }

  /*  final static OpaqueFragmentReader createInstance(final JsonParser jp) {
        return new OpaqueFragmentReader(jp);
    } */

    public final static OpaqueFragmentReader createInstance( final String name) {
        return new OpaqueFragmentReader(name);
    }

    private OpaqueFragmentReader() {
        _name = null;
        _m = new ObjectMapper();
        _is_list = false;

    }

 /*   private OpaqueFragmentReader(final JsonParser jp) {
        _name = null;
        _m = new ObjectMapper();
        _is_list = false;
    } */

    private OpaqueFragmentReader( final String name) {
        _name = name;
        _m = new ObjectMapper();
        _is_list = false;
    }

    @Override
    public final String getAspectName() {
        return _name;
    }

    public final boolean isList() {
        return _is_list;
    }

    @Override
    public final List<AspectElement> readAspectFragment(final JsonParser jp) throws IOException {
        JsonToken t = jp.nextToken();
        _is_list = false;
        if (t == JsonToken.START_ARRAY) {
            _is_list = true;
        }
        final List<AspectElement> elements = new ArrayList<>();
        if (_is_list) {
            while (t != JsonToken.END_ARRAY) {
                if (t == JsonToken.START_OBJECT) {
                    final OpaqueElement e = new OpaqueElement(_name, (ObjectNode) _m.readTree(jp));
                    elements.add(e);
                    
                }
                t = jp.nextToken();
            }
        }
        else {
            final OpaqueElement e = new OpaqueElement(_name, (ObjectNode) _m.readTree(jp));
            elements.add(e);
            
            t = jp.nextToken();
        }
        return elements;
    }

    public final void setAspectName(final String name) {
        _name = name;
    }

    @Override
    public final OpaqueElement readElement(final ObjectNode o) {
        return new OpaqueElement(_name, o);
    }
}
