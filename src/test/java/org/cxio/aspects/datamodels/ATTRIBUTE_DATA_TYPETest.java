package org.cxio.aspects.datamodels;

import static org.junit.Assert.*;

import org.junit.Test;
import org.ndexbio.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;

public class ATTRIBUTE_DATA_TYPETest {

	@Test
	public void test() {
		ATTRIBUTE_DATA_TYPE t = ATTRIBUTE_DATA_TYPE.LIST_OF_LONG;
		String foo = t.name();
		String foo2 = t.toString();
		assertEquals(foo, "LIST_OF_LONG");
		assertEquals(foo2, "list_of_long");
		
	}

}
