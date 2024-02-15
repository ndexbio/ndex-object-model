package org.ndexbio.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CyWebWorkspaceTest {

	@Test
	public void test() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		CyWebWorkspace ws = new CyWebWorkspace();
		UUID randomId = UUID.randomUUID();
		ws.setExternalId(randomId);
		Timestamp current = new Timestamp(Calendar.getInstance().getTimeInMillis());
		ws.setCreationTime(current);
		ws.setModificationTime(current);
		ws.setName("foo");
		
		String s = mapper.writeValueAsString( ws);
		
		System.out.println(s);
		assertTrue ( s.contains("\"workspaceId\":"));
		assertTrue ( !s.contains("externalId"));
		
		 CyWebWorkspace ws2= mapper.readValue(s, CyWebWorkspace.class);
		 
		 assertEquals (ws2.getExternalId(), ws.getWorkspaceId());
		 assertEquals ( ws2.getModificationTime(), ws.getModificationTime());
		 assertEquals (ws2.getCreationTime(), ws.getCreationTime());
		 assertEquals (ws2.getName(), "foo");
	}

}
