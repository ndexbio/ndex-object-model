package org.ndexbio.model.object;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProvenanceEventTest {

	static ObjectMapper mapper;
	
	 @BeforeClass
	 public static void setup() {
		 mapper = new ObjectMapper(); // create once, reuse
	 }

		@Test
		public void test() throws JsonParseException, JsonMappingException, IOException {
			
			Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
			ProvenanceEvent original = new ProvenanceEvent ("COPY", now);
			
			//set values 
		
	        
	        // serialize object
	        String s = mapper.writeValueAsString( original);
	        
	        System.out.println (s);

	        
	        ProvenanceEvent m = mapper.readValue(s, ProvenanceEvent.class);
	        
	        assertEquals(m.getEventType(), "COPY");
	        assertEquals(m.getEndedAtTime(), now);
        
		}

}
