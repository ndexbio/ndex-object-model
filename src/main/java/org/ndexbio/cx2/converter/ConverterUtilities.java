package org.ndexbio.cx2.converter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ndexbio.model.exceptions.NdexException;

public class ConverterUtilities {
    
        protected static final Pattern passThruPattern = Pattern.compile("^COL=(.*),T=.*$");
	
	/** 
          * Convert a cytoscape string VP value into a CX2 VP object value using
          * {@link java.lang.Double#valueOf}, {@link java.lang.Integer#valueOf}, 
          * {@link java.lang.Long#valueOf}, and {@link java.lang.Boolean#valueOf} to
          * perform conversion. (Strings are returned as is)
          * 
	 * @param cytoscapeDataType datatype to convert to. Should be one of the following
          *                           string, double, integer, long, boolean
	 * @param value The value to convert.
	 * @return The converted result along with any warnings about conversion
	 * @throws NdexException if either parameter passed in is {@code null}, if there was a
         *                        conversion error, or if the {@code cytoscapeDataType} is not
         *                        one of the valid types listed above
	 */
	public static ConverterUtilitiesResult cvtStringValueToObj(final String cytoscapeDataType,
                final String value) throws NdexException {
	        if (cytoscapeDataType == null){
                    throw new NdexException("Unsupported cy data type: <null>");
                 }
                 if (value == null){
                     throw new NdexException("Unsupported cy data value: <null>");
                 }
                 
                 if ( cytoscapeDataType.equals("string")) {
			return new ConverterUtilitiesResult(value, null);
		}
                 try {
                    if (cytoscapeDataType.equals("double")) {

                      return new ConverterUtilitiesResult(Double.valueOf(value), null);
                    }

                    if (cytoscapeDataType.equals("long")) {
                           try {
                              return new ConverterUtilitiesResult(Long.valueOf(value), null);
                           } catch (NumberFormatException e) {
                                      return new ConverterUtilitiesResult(Long.valueOf(Double.valueOf(value).longValue()),
                                            Arrays.asList("Value " + value
                                                    + " is not a valid string for long. NDEx is converting it to long from double: " + e.getMessage()));		
                           }
                   }
                    if (cytoscapeDataType.equals("integer")) {
                           try {
                                   return new ConverterUtilitiesResult(Integer.valueOf(value), null);
                           }  catch (NumberFormatException e) {
                                   return new ConverterUtilitiesResult(Integer.valueOf(Double.valueOf(value).intValue()),
                                           Arrays.asList("Value " + value
                                                   + " is not a valid string for integer. NDEx is converting it to integer from double: " + e.getMessage()));		
                           }
                   }

                    if (cytoscapeDataType.equals("boolean")) {
                       return new ConverterUtilitiesResult(Boolean.valueOf(value), null);
                   } 
                 } catch(NumberFormatException nfe){
                     throw new NdexException("Error converting data to type: " + cytoscapeDataType + " : " + nfe.getMessage());
                 }
		throw new NdexException ("Unsupported cy data type found: " + cytoscapeDataType);
		
	}

	/**
	 * Get the column name of a pass through mapping.
	 * @param mappingString
	 * @return
	 * @throws NdexException
	 */
    public static String getPassThroughMappingAttribute(final String mappingString) throws NdexException {
            if (mappingString == null){
                throw new NdexException("Mapping string for Passthrough mapping is null");
            }
           Matcher m = ConverterUtilities.passThruPattern.matcher(mappingString);
           if ( m.matches() ) {
                   return  m.group(1);
           }
           throw new NdexException("Malformed mapping string for Passthrough mapping: "
                   + mappingString);
    }


}
