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
    
    @Test
    public void testPassingInUnsupportedCapitalizedDataType(){
        try {
            ConverterUtilities.cvtStringValueToObj("String", "hello");
            fail("Expected NdexException");
        } catch(NdexException ne){
            assertEquals("Unsupported cy data type found: String", ne.getMessage());
        }
    }
    
    @Test
    public void testPassingInUnsupportedDataType(){
        try {
            ConverterUtilities.cvtStringValueToObj("blah", "hello");
            fail("Expected NdexException");
        } catch(NdexException ne){
            assertEquals("Unsupported cy data type found: blah", ne.getMessage());
        }
    }
    
    @Test 
    public void testPassingInStringWithNull() throws NdexException {
        try {
            ConverterUtilities.cvtStringValueToObj("string", null);
            fail("Expected NdexException");
        } catch(NdexException ne){
            assertEquals("Unsupported cy data value: <null>", ne.getMessage());
        }
    }

    @Test 
    public void testPassingInStringWithValidValue() throws NdexException {
        ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("string", "foo");
        assertEquals("foo", res.getResult());
        assertNull(res.getWarnings());
    }

    
    @Test 
    public void testPassingInDoubleWithNull() throws NdexException {
        try {
            ConverterUtilities.cvtStringValueToObj("double", null);
            fail("Expected NdexException");
        } catch(NdexException ne){
            assertEquals("Unsupported cy data value: <null>", ne.getMessage());
        }
    }
    
    @Test 
    public void testPassingInDoubleWithInvalidValue() throws NdexException {
        try {
            ConverterUtilities.cvtStringValueToObj("double", "boo");
            fail("Expected NdexException");
        } catch(NdexException ne){
            assertEquals("Error converting data to type: double : For input string: \"boo\"",
                    ne.getMessage());
        }
    }
    
    @Test 
    public void testPassingInDoubleWithValidValue() throws NdexException {

        ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("double", "22.88");
        assertEquals(22.88, res.getResult());
        assertNull(res.getWarnings());
    }
    
    @Test 
    public void testPassingInIntegerWithValidValue() throws NdexException {

        ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("integer", "9");
        assertEquals(9, res.getResult());
        assertNull(res.getWarnings());
    }
    
    @Test 
    public void testPassingInLongWithValidValue() throws NdexException {

        ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("long", "567");
        assertEquals(567L, res.getResult());
        assertNull(res.getWarnings());
    }
    
    @Test 
    public void testPassingInBooleanWithValidValue() throws NdexException {
         ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("boolean", "true");
          assertEquals(Boolean.TRUE, res.getResult());
          assertNull(res.getWarnings());
          
          res = ConverterUtilities.cvtStringValueToObj("boolean", "false");
          assertEquals(Boolean.FALSE, res.getResult());
          assertNull(res.getWarnings());
    }
    
    @Test 
    public void testPassingInBooleanWithInvalidValue() throws NdexException {
         ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("boolean", "notvalid");
          assertEquals(Boolean.FALSE, res.getResult());
          assertNull(res.getWarnings());     
    }
    
    @Test 
    public void testPassingInDoubleWithDataTypeAsLong() throws NdexException {

        ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("long", "1.5");
        assertEquals(1L, res.getResult());
        assertEquals(1, res.getWarnings().size());
        assertEquals("Value 1.5 is not a valid string for long. "
                + "NDEx is converting it to long from double: For "
                + "input string: \"1.5\"", res.getWarnings().get(0));
    }
    
    @Test 
    public void testPassingInDoubleWithDataTypeAsInteger() throws NdexException {

        ConverterUtilitiesResult res = ConverterUtilities.cvtStringValueToObj("integer", "53.8");
        assertEquals(53, res.getResult());
        assertEquals(1, res.getWarnings().size());
        assertEquals("Value 53.8 is not a valid string for integer. "
                + "NDEx is converting it to integer from double: For "
                + "input string: \"53.8\"", res.getWarnings().get(0));
    }
    
    @Test
    public void testgetPassThroughMappingAttributeNullArgument(){
        try {
            ConverterUtilities.getPassThroughMappingAttribute(null);
            fail("Expected NDexException");
        } catch(NdexException ne){
            assertEquals("Mapping string for Passthrough mapping is null", ne.getMessage());
        }
    }
    
    @Test
    public void testgetPassThroughMappingAttributeEmptyString(){
        try {
            ConverterUtilities.getPassThroughMappingAttribute("");
            fail("Expected NDexException");
        } catch(NdexException ne){
            assertEquals("Malformed mapping string for Passthrough mapping: ", ne.getMessage());
        }
    }
    
    @Test
    public void testgetPassThroughMappingAttributeValidString() throws NdexException {
         String[] res = ConverterUtilities.getPassThroughMappingAttribute("COL=hello,T=something");
         assertEquals("hello", res[0]);
         assertEquals("something", res[1]);
    }
}
