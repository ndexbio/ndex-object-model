/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *   All rights reserved.
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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MembershipTest {


	static ObjectMapper mapper;
	
	 @BeforeClass
	 public static void setup() {
		 mapper = new ObjectMapper(); // create once, reuse
	 }
	
	@Test
	public void testMembershipDeserialize() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		
		
       Membership m= mapper.readValue(new File(getClass().getClassLoader().getResource("membership.json").toURI()), Membership.class);
       
       assertEquals(m.getResourceName(), "foo");
		
       assertEquals(m.getMemberAccountName(), "bar");
        
	}


	@Test
	public void testMembership() throws JsonParseException, JsonMappingException, IOException {
		
		Membership original = new Membership ();
		
		//set values 
	
//		UUID eId = UUID.randomUUID();
//		original.setExternalId( eId);
		original.setMemberAccountName("foo");
		
        original.setMembershipType(MembershipType.GROUP); 		
        original.setMemberUUID(UUID.randomUUID());
        original.setPermissions(Permissions.ADMIN);
        original.setResourceName("bar");
        original.setResourceUUID(UUID.randomUUID());
        
        // serialize object
        String s = mapper.writeValueAsString( original);
        
        System.out.println (s);

        
        Membership m = mapper.readValue(s, Membership.class);
        

 //       assertEquals(m.getExternalId(), eId);
		
        assertEquals(m.getMemberAccountName(), original.getMemberAccountName());
        
        assertEquals(m.getMembershipType(), original.getMembershipType());

        assertEquals(m.getMemberUUID(), original.getMemberUUID());

        assertEquals(m.getPermissions(), original.getPermissions());

        assertEquals(m.getResourceName(), original.getResourceName());

        assertEquals(m.getResourceUUID(), original.getResourceUUID());
        
//        assertEquals(m.getCreationDate(), original.getCreationDate());

//        assertEquals(m.getModificationDate(), original.getModificationDate());
        
        assertEquals(m.getType(), "Membership");
	}
	
	
}
