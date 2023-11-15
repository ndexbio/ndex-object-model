package org.ndexbio.model.network.query;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CXObjectFilterTest {

	@Test
	public void test() throws JsonProcessingException {
		   // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
       // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Create an instance of CXObjectFilter and set values
        CXObjectFilter originalFilter = new CXObjectFilter();
        originalFilter.setIds(new TreeSet<>(Set.of(1L, 2L, 3L)));
        originalFilter.setAttributeNames(new TreeSet<>(Set.of("name", "type")));

        // Serialize the CXObjectFilter instance to a JSON string
        String jsonString = objectMapper.writeValueAsString(originalFilter);
        
        System.out.println(jsonString);

        // Deserialize the JSON string back to a CXObjectFilter instance
        CXObjectFilter deserializedFilter = objectMapper.readValue(jsonString, CXObjectFilter.class);

        // Assert that the deserialized object matches the original object
        assertEquals(originalFilter.getIds(), deserializedFilter.getIds());
        assertEquals(originalFilter.getAttributeNames(), deserializedFilter.getAttributeNames());	
        
        //check if duplicates are removed.
        String jsonStr2 = "{\"ids\":[1,2,3,1],\"attributeNames\":[\"name\",\"type\",\"name\"]}";
        deserializedFilter = objectMapper.readValue(jsonStr2, CXObjectFilter.class);
        
        assertEquals(originalFilter.getIds(), deserializedFilter.getIds());
        assertEquals(originalFilter.getAttributeNames(), deserializedFilter.getAttributeNames());	
        
        jsonStr2 = "{\"attributeNames\":[\"name\",\"type\",\"name\"]}";
        deserializedFilter = objectMapper.readValue(jsonStr2, CXObjectFilter.class);
        
        assertEquals(0, deserializedFilter.getIds().size());
        assertEquals(originalFilter.getAttributeNames(), deserializedFilter.getAttributeNames());	
        
	}

}
