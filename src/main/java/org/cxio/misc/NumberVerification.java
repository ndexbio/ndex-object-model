package org.cxio.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class NumberVerification implements Serializable {

    private static final long             serialVersionUID = 5323786737832032583L;
    public final static String            NAME             = "numberVerification";
    private static final String           LONG_NUMBER      = "longNumber";
    private final List<Map<String, Long>> _data;

    public final static NumberVerification createInstanceFromJson(final InputStream is) throws IOException {
        final ObjectMapper m = new ObjectMapper();
        return m.readValue(is, NumberVerification.class);
    }

    public final static NumberVerification createInstanceFromJson(final JsonParser jp) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper m = new ObjectMapper();
        return m.readValue(jp, NumberVerification.class);
    }

    public final static NumberVerification createInstanceFromJson(final String str) throws IOException {
        final ObjectMapper m = new ObjectMapper();
        return m.readValue(str, NumberVerification.class);
    }

    public NumberVerification() {
        _data = new ArrayList<>();
   /*     HashMap<String,Long> numMap = new HashMap<>(1);
        numMap.put(LONG_NUMBER, CxConstants.LONG_NUMBER_TEST);
        _data.add(numMap); */

    }

    public NumberVerification(final Long long_number) {
        _data = new ArrayList<>();
        _data.add(new HashMap<String, Long>());
        _data.get(0).put(LONG_NUMBER, long_number);
    }

    @JsonIgnore
    public final Long getLongNumber() {
        return _data.get(0).get(LONG_NUMBER);
    }

    public List<Map<String, Long>> getNumberVerification() {
        return _data;
    }

    public final void toJson(final JsonWriter w) throws IOException {
        w.writeObject(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("long number: ");
        sb.append(getLongNumber());
        return sb.toString();
    }

}
