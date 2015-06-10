/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
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
