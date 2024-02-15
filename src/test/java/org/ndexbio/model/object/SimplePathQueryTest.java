package org.ndexbio.model.object;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimplePathQueryTest {

	@Test
	public void testMembership() throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		SimplePathQuery spq = new SimplePathQuery();
		spq.setSearchString("foo");
		spq.setSearchDepth(2);
		spq.setErrorWhenLimitIsOver(false);
		spq.setEdgeLimit(200);
		
		
        // serialize object
        String s = mapper.writeValueAsString( spq);
        
        System.out.println (s);

        
        SimplePathQuery m = mapper.readValue(s, SimplePathQuery.class);
        

		
        assertEquals(m.getEdgeLimit(), spq.getEdgeLimit());
        
        assertEquals(m.getErrorWhenLimitIsOver(), spq.getErrorWhenLimitIsOver());

        assertEquals(m.getSearchDepth(), spq.getSearchDepth());

        assertEquals(m.getSearchString(), spq.getSearchString());

	}
	
	
}
