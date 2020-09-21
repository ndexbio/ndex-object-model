package org.ndexbio.cx2.converter;

import static org.junit.Assert.*;
import org.junit.Test;
import org.ndexbio.model.exceptions.NdexException;

/**
 *
 * @author churas
 */
public class ConverterUtilitiesTest {
    
    
    @Test
    public void testPassingInNullDataType(){
        try {
            ConverterUtilities.cvtStringValueToObj(null, "1.0");
            fail("Expected NdexException");
        } catch(NdexException ne){
            assertEquals("Unsupported cy data type: <null>", ne.getMessage());
        }
    }
    
    @Test public void testPassingInStringWithNull() throws NdexException {
        assertNull(ConverterUtilities.cvtStringValueToObj("string", null));
    }
    
    @Test public void testPassingInStringWithValidValue() throws NdexException {
        assertEquals("foo", ConverterUtilities.cvtStringValueToObj("string", "foo"));
    }
    
    @Test public void testPassingInDoubleWithNull() throws NdexException {
        try {
            ConverterUtilities.cvtStringValueToObj("double", null);
            fail("Expected NdexException");
        } catch(NdexException ne){
            
        }
    }
}
