package org.ndexbio.model.object;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
