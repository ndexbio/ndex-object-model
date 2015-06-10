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
