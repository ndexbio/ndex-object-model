package org.cxio.core;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


@Deprecated
public class AttributeValueSerializer extends JsonSerializer <Object> {
	
	public void serialize(Object v, JsonGenerator jsonGenerator, 
        SerializerProvider serializerProvider) 
    throws IOException, JsonProcessingException {

        if(v instanceof String){
            jsonGenerator.writeString((String)v);
        } else {
        		List<String> v2 = (List<String>)v;
            jsonGenerator.writeStartArray();
            for (String s : v2) {
            		jsonGenerator.writeString(s);
            }
            jsonGenerator.writeEndArray();
        }
    }

	

}
