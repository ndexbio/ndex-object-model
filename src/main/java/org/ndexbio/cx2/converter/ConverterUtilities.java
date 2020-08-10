package org.ndexbio.cx2.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ndexbio.model.exceptions.NdexException;

public class ConverterUtilities {

	
	/** covert a cytoscape string VP value into a CX2 VP object value
	 * 
	 * @param cytoscapeDataType
	 * @param value
	 * @return
	 * @throws NdexException
	 */
	public static Object cvtStringValueToObj(String cytoscapeDataType, String value) throws NdexException {
		if ( cytoscapeDataType.equals("string")) {
			return value;
		} else if (cytoscapeDataType.equals("double")) {
		   return Double.valueOf(value);
		}else if (cytoscapeDataType.equals("long")) {
			try {
			   return Long.valueOf(value);
			} catch (NumberFormatException e) {
				System.err.println("Value " + value + " is not a valid string for long. NDEx is converting it to long from double.");
				return Long.valueOf(Double.valueOf(value).longValue());		
			}
		}else if (cytoscapeDataType.equals("integer")) {
			try {
				return Integer.valueOf(value);
			}  catch (NumberFormatException e) {
				System.err.println("Value " + value + " is not a valid string for integer. NDEx is converting it to long from double.");
				return Integer.valueOf(Double.valueOf(value).intValue());		
			}
		}else if (cytoscapeDataType.equals("boolean")) {
				   return Boolean.valueOf(value);
		} else {
			throw new NdexException ("Unsupported cy data type found:" + cytoscapeDataType);
		}
	}

	/**
	 * Get the column name of a pass through mapping.
	 * @param mappingString
	 * @return
	 * @throws NdexException
	 */
    public static String getPassThroughMappingAttribute(String mappingString) throws NdexException {
		Pattern p = Pattern.compile("^COL=(.*),T=.*$");
		Matcher m = p.matcher(mappingString);
		if ( m.matches() ) {
			return  m.group(1);
		}
		throw new NdexException("Malformed mapping string for Passthrough mapping: " + mappingString);
    }


}
