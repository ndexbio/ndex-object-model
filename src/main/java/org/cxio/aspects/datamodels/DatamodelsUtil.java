package org.cxio.aspects.datamodels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public final class DatamodelsUtil {

    final static String removeParenthesis(final String string, final ATTRIBUTE_DATA_TYPE type) {
        if (string == null) {
            return null;
        }
        String substring = string.trim();
        if (substring.equals("null")) {
            return null;
        }
        if (substring.startsWith("\"") && substring.endsWith("\"")) {
            substring = substring.substring(1, substring.length() - 1);
        }
        final boolean allow_empty_string = (type == ATTRIBUTE_DATA_TYPE.STRING);
        if (!allow_empty_string && (substring.trim().length() < 1)) {
            throw new IllegalArgumentException("illegal format, empty strings not allowed: " + string);
        }
        return substring;
    }

    private final static List<String> split(final String json_array) throws IOException {

        final List<String> splitted_json_elements = new ArrayList<>();
        final ObjectMapper m = new ObjectMapper();
        final JsonNode n = m.readTree(json_array);

        if (n.isArray()) {
            final ArrayNode arrayNode = (ArrayNode) n;
            for (int i = 0; i < arrayNode.size(); i++) {
                final JsonNode individualElement = arrayNode.get(i);
                splitted_json_elements.add(individualElement.asText());
            }
        }
        return splitted_json_elements;
    }

    public final static List<String> parseStringToStringList(final String string) throws IOException {
        final List<String> l = new ArrayList<>();
        if (string == null) {
            return null;
        }
        final String str = string.trim();
        if (str.startsWith("[") && str.endsWith("]")) {
            final List<String> str_split = split(str);
            for (String s : str_split) {
                if (s.equals("null")) {
                   l.add(null);
                }
                else {
                    l.add(s);
                }
            }
        }
        else {
            throw new IllegalArgumentException("parsing string to string list: illegal format: expected to begin and end with square brackets, instead got : " + str);
        }
        return l;
    }

}
