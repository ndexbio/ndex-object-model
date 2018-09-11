package org.ndexbio.cxio.misc;

import java.io.IOException;
import java.io.InputStream;

import org.ndexbio.cxio.aspects.datamodels.AbstractAspectElement;
import org.ndexbio.cxio.util.CxioUtil;
import org.ndexbio.cxio.util.JsonWriter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This used to represent a "anonymous", general aspect element. Essentially it
 * is a wrapper for ObjectNode from faster XML library. A ObjectNode in turn is
 * the root of a tree-structure representing json-data.
 *
 * @author cmzmasek
 *
 */
public final class OpaqueElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5157720596377220141L;
	private final JsonNode            _data;
    private final String              _name;
    private final static ObjectMapper mapper = new ObjectMapper();

    public OpaqueElement(final String name, final JsonNode data) {
        _name = name;
        _data = data;
    }

    public OpaqueElement(final String name, final ObjectNode data) {
        _name = name;
        _data = data;
    }

    public OpaqueElement(final String name, final String json_string) throws IOException {
        _name = name;
        _data = mapper.readTree(json_string);
    }

    public OpaqueElement(final String name, final byte[] json_byte_array) throws IOException {
        _name = name;
        _data = mapper.readTree(json_byte_array);
    }

    public OpaqueElement(final String name, final InputStream json_is) throws IOException {
        _name = name;
        _data = mapper.readTree(json_is);
    }

    @Override
    final public String getAspectName() {
        return _name;
    }

    final public JsonNode getData() {
        return _data;
    }

    public final String toJsonString() throws IOException {
        if (_data != null) {
            return mapper.writeValueAsString(_data);
        }
		return "";
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        if (!CxioUtil.isEmpty(_name)) {
            sb.append(_name);
            sb.append(": ");
        }
        try {
            sb.append(toJsonString());
        }
        catch (final IOException e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

	@Override
	public void write(JsonWriter out) throws IOException {
		out.writeJsonObject(_data);
		out.flush();
	}

}
