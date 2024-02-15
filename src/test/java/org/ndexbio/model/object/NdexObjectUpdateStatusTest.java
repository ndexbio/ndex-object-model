package org.ndexbio.model.object;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NdexObjectUpdateStatusTest {


	static ObjectMapper mapper;
	
	 @BeforeAll
	 public static void setup() {
		 mapper = new ObjectMapper(); // create once, reuse
	 }
	
	@Test
	public void testSerializserDeserialize() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		
		NdexObjectUpdateStatus s = new NdexObjectUpdateStatus();
		s.setModificationTime(new Timestamp(167874545073l));
		s.setUuid(UUID.fromString("2e0b0494-c1ec-11ed-b2fd-42a25b6b2eb9"));
		
		String jsonS = mapper.writeValueAsString(s);
		
		System.out.println ( jsonS);
		assertEquals(jsonS, "{\"uuid\":\"2e0b0494-c1ec-11ed-b2fd-42a25b6b2eb9\",\"modificationTime\":167874545073}");
		
		
	    NdexObjectUpdateStatus s1 = mapper.readerFor(NdexObjectUpdateStatus.class)
				.readValue(jsonS);
		       
       assertEquals(s1.getModificationTime(), s.getModificationTime());
		
       assertEquals(s1.getUuid(), s.getUuid());
        
	}

}