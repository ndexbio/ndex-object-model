package org.ndexbio.cxio.misc;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.ndexbio.cxio.util.CxioUtil;
import org.ndexbio.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Status implements Serializable {

    private static final String                   ERROR            = "error";
    public final static String                    NAME             = "status";
    private static final long                     serialVersionUID = 6558992873250381652L;
    private static final String                   SUCCESS          = "success";
    private final List<SortedMap<String, Object>> _data;

 /*   public final static Status createInstanceFromJson(final InputStream is) throws IOException {
        final ObjectMapper m = new ObjectMapper();
        return m.readValue(is, Status.class);
    } */

    public final static Status createInstanceFromJson(final JsonParser jp) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper m = new ObjectMapper();
        Status s =  m.readValue(jp, Status.class);
        if ( s.getStatus().size() != 1)
        	throw new IOException ("Aspect 'status' should have one element in it.");
        return s; 
    }

 /*   public final static Status createInstanceFromJson(final String str) throws IOException {
        final ObjectMapper m = new ObjectMapper();
        return m.readValue(str, Status.class);
    } */

    public Status() {
        _data = new ArrayList<>();

    } 

    public Status(final boolean success) {
        _data = new ArrayList<>();
        _data.add(new TreeMap<String, Object>());
        _data.get(0).put(ERROR, "");
        _data.get(0).put(SUCCESS, Boolean.valueOf(success));
    }

    public Status(final boolean success, final String error) {
        _data = new ArrayList<>();
        _data.add(new TreeMap<String, Object>());
        _data.get(0).put(ERROR, error == null ? "" : error);
        _data.get(0).put(SUCCESS, Boolean.valueOf(success));
    }

    @JsonIgnore
    public final String getError() {
        return (String) _data.get(0).get(ERROR);
    }

    public List<SortedMap<String, Object>> getStatus() {
        return _data;
    }

    @JsonIgnore
    public final boolean isSuccess() {
        return ((Boolean) _data.get(0).get(SUCCESS)).booleanValue();
    }

    public final void toJson(final JsonWriter w) throws IOException {
        w.writeObject(this);
        w.flush();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("success: " + isSuccess());
        if (!CxioUtil.isEmpty(getError())) {
            sb.append("\n");
            sb.append("error  : " + getError());
        }
        return sb.toString();
    }

}
