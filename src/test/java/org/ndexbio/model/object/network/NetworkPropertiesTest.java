package org.ndexbio.model.object.network;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NetworkPropertiesTest {

	@Test
	public void test() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper(); 
		NetworkProperties props = new NetworkProperties();
		Map<String, Object> nameV = new TreeMap<>();
		nameV.put("t", "string");
		nameV.put("v", "foo");
		props.add("name", nameV);
		
		//Map<String, Object> descV = new TreeMap<>();
		//descV.put("t", "string");
		//descV.put("v", "desc1");
		props.setProperty("description", "string", "desc1");
		
		Map<String, Object> versionV = new TreeMap<>();
		versionV.put("t", "string");
		versionV.put("v", "v1");
		props.add("version", versionV);
		
		Map<String, Object> v1 = new TreeMap<>();
		v1.put("t", "list_of_string");
		List<String> l1 = new ArrayList<>();
		l1.add("bar");
		v1.put("v", l1);
		props.add("networkType", v1);
		
		 String s = mapper.writeValueAsString( props);
	        
	     System.out.println (s);
	     
	     NetworkProperties prop1 = mapper.readValue(s, NetworkProperties.class);
	     
	     Map<String, Map<String,Object>> table = prop1.getProperties();
	     assertEquals(table.get("name").get("t"),"string");
	     assertEquals(table.get("name").get("v"),"foo");
	     
	     assertEquals(table.get("description").get("t"),"string");
	     assertEquals(table.get("description").get("v"),"desc1");
	     
	     assertEquals(table.get("version").get("t"),"string");
	     assertEquals(table.get("version").get("v"),"v1");
	     
	     assertEquals(table.get("networkType").get("t"),"list_of_string");
	     Object v2 = table.get("networkType").get("v");
	     assertTrue (v2 instanceof List);
	     assertEquals(((List<String>)v2).get(0), "bar");
	     assertEquals(((List<String>)v2).size(), 1);
	}

}
