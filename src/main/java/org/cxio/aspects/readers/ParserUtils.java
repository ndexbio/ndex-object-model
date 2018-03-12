package org.cxio.aspects.readers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.cxio.util.CxioUtil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class ParserUtils {

/*    public final static List<String> parseSimpleStringList(final JsonParser jp, JsonToken t) throws IOException, JsonParseException {
        final List<String> elements = new ArrayList<String>();
        while (t != JsonToken.END_ARRAY) {
            if (t == JsonToken.VALUE_STRING) {
                elements.add(jp.getText());
            }
            else if (t != JsonToken.START_OBJECT) {
                throw new IOException("malformed cx json, expected '" + JsonToken.START_OBJECT + "', got '" + t + "'");
            }
            t = jp.nextToken();
        }
        return elements;
    }

    public final static List<String> getStringList(final ObjectNode o, final String label) {
        final List<String> l = new ArrayList<String>();
        if (o.has(label)) {
            final Iterator<JsonNode> it = o.get(label).iterator();
            while (it.hasNext()) {
                final String s = it.next().asText();
                if (!CxioUtil.isEmpty(s)) {
                    l.add(s);
                }
            }
        }
        return l;
    }

    public final static List<String> getStringListRequired(final ObjectNode o, final String label) throws IOException {
        final List<String> l = new ArrayList<String>();
        if (o.has(label)) {
            final Iterator<JsonNode> it = o.get(label).iterator();
            while (it.hasNext()) {
                final String s = it.next().asText();
                if (!CxioUtil.isEmpty(s)) {
                    l.add(s);
                }
            }
        }
        if (l.isEmpty()) {
            throw new IOException("malformed CX json: list '" + label + "' is missing or empty in " + o.toString());
        }
        return l;
    }

    public final static List<String> getAsStringListRequired(final ObjectNode o, final String label) throws IOException {
        final List<String> l = ParserUtils.getAsStringList(o, label);
        if (l.isEmpty()) {
            throw new IOException("malformed CX json: list '" + label + "' is missing or empty in " + o.toString());
        }
        return l;
    }
*/
    public final static List<Long> getAsLongListRequired(final ObjectNode o, final String label) throws IOException {
        final List<Long> l = ParserUtils.getAsLongList(o, label);
        if (l.isEmpty()) {
            throw new IOException("malformed CX json: list '" + label + "' is missing or empty in " + o.toString());
        }
        return l;
    }

    public final static List<String> getAsStringList(final ObjectNode o, final String label)  {
        final List<String> l = new ArrayList<String>();
        if (o.has(label)) {
            if (!o.get(label).isArray()) {
                l.add(o.get(label).asText());
            }
            else {
                final Iterator<JsonNode> it = o.get(label).iterator();
                while (it.hasNext()) {
                    final String s = it.next().asText();
                    if (s != null) {
                        l.add(s);
                    }
                }
            }
        }
        return l;
    }

    public final static List<Long> getAsLongList(final ObjectNode o, final String label) {
        final List<Long> l = new ArrayList<Long>();
        if (o.has(label)) {
            if (!o.get(label).isArray()) {
                l.add(o.get(label).asLong());
            }
            else {
                final Iterator<JsonNode> it = o.get(label).iterator();
                while (it.hasNext()) {
                    final String s = it.next().asText();
                    if (!CxioUtil.isEmpty(s)) {
                        l.add(Long.valueOf(s));
                    }
                }
            }
        }
        return l;
    }

    public final static boolean isArray(final ObjectNode o, final String label) {
    	JsonNode o2 = o.get(label);
    	if ( o2 != null)
    			return o2.isArray();
    	
    	return false;
    }

    /**
     * Returns the value for label.
     * Return null if no such label.
     *
     * @param o
     * @param label
     * @return
     */
    public final static String getTextValue(final ObjectNode o, final String label) {
        if (o.has(label)) {
            return o.get(label).asText();
        }
        return null;
    }

    public final static Long getTextValueAsLong(final ObjectNode o, final String label) throws IOException {
        if (o.has(label)) {
            final String s = o.get(label).asText();
            try {
                return Long.valueOf(s);
            }
            catch (final NumberFormatException e) {
                throw new IOException("malformed CX json: element '" + label + "' has mal-formed long integer: " + s + ". Error: " + e.getMessage());
            }

        }
        return null;
    }

    public final static String getTextValueRequired(final ObjectNode o, final String label) throws IOException {
        String s = null;
        if (o.has(label)) {
            s = o.get(label).asText();
        }
        if (CxioUtil.isEmpty(s)) {
            throw new IOException("malformed CX json: element '" + label + "' is missing in " + o.toString());
        }
        return s;
    }

    public final static Long getTextValueRequiredAsLong(final ObjectNode o, final String label) throws IOException {
        String s = null;
        if (o.has(label)) {
            s = o.get(label).asText();
        }
        if (CxioUtil.isEmpty(s)) {
            throw new IOException("malformed CX json: element '" + label + "' is missing in " + o.toString());
        }

        try {
            return Long.valueOf(s);
        }
        catch (final NumberFormatException e) {
            throw new IOException("malformed CX json: element '" + label + "' has mal-formed long integer: " 
        + s + ". Error: " + e.getMessage());

        }
    }

    public final static SortedMap<String, List<String>> getMap(final ObjectNode o, final String label) {
        final SortedMap<String, List<String>> map = new TreeMap<String, List<String>>();
        if (o.has(label)) {
            final Iterator<Entry<String, JsonNode>> it1 = o.get(label).fields();
            while (it1.hasNext()) {
                final Entry<String, JsonNode> s = it1.next();
                final ArrayList<String> l = new ArrayList<String>();
                map.put(s.getKey(), l);
                final Iterator<JsonNode> it2 = s.getValue().iterator();
                while (it2.hasNext()) {
                    l.add(it2.next().asText());
                }
            }
        }
        return map;
    }

}
