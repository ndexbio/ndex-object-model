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
package org.ndexbio.model.object.network;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTermTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	

	@Test
	public void test0()  {
		
		String aa = " abc(bar:foo) (pp@()@d) dd(bar(dls:0df0,f:xoo)) ";
		Pattern p = Pattern.compile("^\\s*\\S+\\s+\\((\\S+)\\)\\s+\\S+\\s*$");
		Matcher m = p.matcher(aa);
		System.out.println(m.find());
		System.out.println(m.groupCount());
		System.out.println(m.group(0));
		System.out.println(m.group(1));
		
		
		
	}
	
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		BaseTerm a = new BaseTerm();
		a.setId(22);
		a.setName("bar");
		a.setNamespaceId(232);
		//a.setType("baseTerm");

		String s = objectMapper.writeValueAsString( a );
        
        System.out.println (s);

        
         objectMapper.readValue(s, BaseTerm.class);
        
		
		String foo = 
			"{\"name\":\"NM_004379\",\"namespace\":756,\"termType\":\"BaseTerm\",\"id\":1153,\"type\":\"BaseTerm\"}";
		 objectMapper.readValue(foo, BaseTerm.class);
	}

}
