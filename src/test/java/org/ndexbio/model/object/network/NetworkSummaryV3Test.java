package org.ndexbio.model.object.network;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NetworkSummaryV3Test {

	@Test
	public void test() throws JsonProcessingException {
		NetworkSummaryV3 summary = new NetworkSummaryV3();
		UUID uuid1 = UUID.fromString("7fc70ab6-9fb1-11ea-aaef-0ac135e8bacf");
		
		summary.setUuid(uuid1);
		summary.setModificationTime(new Timestamp(1683053081));
		
		ObjectMapper om = new ObjectMapper();
		String s1 = om.writeValueAsString(summary);
		System.out.println(s1);
		
		assertEquals(s1, "{\"uuid\":\"7fc70ab6-9fb1-11ea-aaef-0ac135e8bacf\",\"modificationTime\":1683053081}");
		

	}

}
