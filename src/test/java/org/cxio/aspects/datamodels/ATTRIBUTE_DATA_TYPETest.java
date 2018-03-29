package org.cxio.aspects.datamodels;

import static org.junit.Assert.*;

import org.junit.Test;

public class ATTRIBUTE_DATA_TYPETest {

	@Test
	public void test() {
		ATTRIBUTE_DATA_TYPE t = ATTRIBUTE_DATA_TYPE.LIST_OF_LONG;
		String foo = t.name();
		String foo2 = t.toString();
		assertEquals(foo, "LIST_OF_LONG");
		assertEquals(foo2, "list_of_long");
	//	String s = ATTRIBUTE_DATA_TYPE.toCxLabel(t);
//		assertEquals(s,"list_of_long");
		
	}

}
