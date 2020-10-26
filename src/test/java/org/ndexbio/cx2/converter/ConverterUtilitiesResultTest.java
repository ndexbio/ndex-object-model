package org.ndexbio.cx2.converter;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author churas
 */
public class ConverterUtilitiesResultTest {
    
    @Test
    public void testGettersAndSetters(){
        ConverterUtilitiesResult res = new ConverterUtilitiesResult(null, null);
        assertNull(res.getWarnings());
        assertNull(res.getResult());
        assertFalse(res.hasWarnings());

        res = new ConverterUtilitiesResult("boo", Arrays.asList("hi"));
        assertEquals("hi", res.getWarnings().get(0));
        assertEquals("boo", res.getResult());
        assertTrue(res.hasWarnings());
        
        
        res = new ConverterUtilitiesResult(5L, Arrays.asList());
        assertTrue(res.getWarnings().isEmpty());
        assertEquals(5L, res.getResult());
        assertFalse(res.hasWarnings());
        
        
    }
}
