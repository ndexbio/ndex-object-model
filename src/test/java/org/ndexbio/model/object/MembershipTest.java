/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.object;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.ndexbio.model.cx.SupportElement;

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
		
		
		SupportElement cxs = new SupportElement ();
		cxs.setText("_:foobar");
		
		String s0 = mapper.writeValueAsString( cxs);
		System.out.println(s0);
		
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
        
       // assertEquals(m.getType(), "Membership");
	}
	
	
}
