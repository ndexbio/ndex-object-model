package org.ndexbio.model.object.network;

import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionTermTest {

	@Test
	public void test() {
      FunctionTerm t1 = new FunctionTerm();
      t1.setFunctionTermId(10);
      t1.getParameters().add((long)20);
      
      FunctionTerm t2 = new FunctionTerm();
      t2.setFunctionTermId(10);
      t2.getParameters().add((long)20);
      t2.getParameters().add((long)2);
      
      assertEquals(t1.compareTo(t2) < 0 ,true);
      assertEquals(t2.compareTo(t1) > 0 ,true);
      
      FunctionTerm t3 = new FunctionTerm();
      t3.setFunctionTermId(10);
      t3.getParameters().add((long)20);
      t3.getParameters().add((long)2);

      assertEquals(t3.compareTo(t2) ,0);
      assertEquals(t2.compareTo(t3) ,0);
      
      FunctionTerm t4 = new FunctionTerm();
      t4.setFunctionTermId(9);
      t4.getParameters().add((long)20);
      t4.getParameters().add((long)2);
      
      assertEquals(t4.compareTo(t2) <0,true);
      assertEquals(t2.compareTo(t4) >0,true);
      
      
      
	}

}
