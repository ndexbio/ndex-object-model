package org.ndexbio.cxio.core;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxEdge;

public class AspectIteratorTest {

	@Test
	public void test() throws URISyntaxException {
        File aspectFile = new File(getClass().getClassLoader().getResource("aspects_cx2/edges").toURI());

        
        try (AspectIterator<CxEdge> iterator = new AspectIterator<>(aspectFile.getAbsolutePath(), CxEdge.class)) {
            // Perform tests
            assertTrue(iterator.hasNext());
            CxEdge e1 = iterator.next();
            assertNotNull(e1);
            
            assertTrue(iterator.hasNext());
            e1 = iterator.next();
            assertNotNull(e1);
            
            assertTrue(iterator.hasNext());
            e1 = iterator.next();
            assertNotNull(e1);
            assertEquals(e1.getId(), Long.valueOf(13972L));
            assertEquals(e1.getSource(), Long.valueOf(13654));
            assertEquals(e1.getTarget(), Long.valueOf(13680));
            assertEquals(e1.getAttributes().get("i"), "c-c");
             
            assertFalse(iterator.hasNext());
            
        } catch (Exception e) {
            fail("Exception occurred during test: " + e.getMessage());
        } 
	}

}
