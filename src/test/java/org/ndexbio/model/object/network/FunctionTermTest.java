/**
 * Copyright (c) 2013, 2016, The Regents of the University of California, The Cytoscape Consortium
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

import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionTermTest {

	@Test
	public void test() {
      FunctionTerm t1 = new FunctionTerm();
      t1.setFunctionTermId(10);
      t1.getParameterIds().add((long)20);
      
      FunctionTerm t2 = new FunctionTerm();
      t2.setFunctionTermId(10);
      t2.getParameterIds().add((long)20);
      t2.getParameterIds().add((long)2);
      
      assertEquals(t1.compareTo(t2) < 0 ,true);
      assertEquals(t2.compareTo(t1) > 0 ,true);
      
      FunctionTerm t3 = new FunctionTerm();
      t3.setFunctionTermId(10);
      t3.getParameterIds().add((long)20);
      t3.getParameterIds().add((long)2);

      assertEquals(t3.compareTo(t2) ,0);
      assertEquals(t2.compareTo(t3) ,0);
      
      FunctionTerm t4 = new FunctionTerm();
      t4.setFunctionTermId(9);
      t4.getParameterIds().add((long)20);
      t4.getParameterIds().add((long)2);
      
      assertEquals(t4.compareTo(t2) <0,true);
      assertEquals(t2.compareTo(t4) >0,true);
      
      
      
	}

}
