/**
 * Copyright (c) 2013, 2015
 *  	The Regents of the University of California, The Cytoscape Consortium
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
