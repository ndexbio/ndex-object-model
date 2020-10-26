package org.ndexbio.cx2.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Copied from StringPaser class in cx_support 
 * @author jingchen
 *
 */
public class MappingValueStringParser {
	private final Map<String, String> _data = new HashMap<>(); 
	private static final Pattern p = Pattern.compile("^((K|V|L|E|G|OV)=([0-9]+))=(.*)$");  
		
	
	/**
	 * Given a count of commas {@code commaCount} 
	 * this function appends a single comma to the {@code sb}
	 * for every 2 commas encountered. 
	 * 
	 * If {@code commaCount} is odd the contents
	 * of {@code sb} is appended to {@splitStr} list and {@code sb} is cleared
	 * 
	 * @param commaCount Number of commas encountered
	 * @param sb Current String fragment
	 * @param splitStr Destination list
	 */
	private static void appendCommasAndOrAddNewStringToList(int commaCount, StringBuilder sb, List<String> splitStr){
		if (commaCount == 0){
			return;
		}
		
		// for every 2 consecutive commas output a single comma
		// this is because the format uses double comma as an
		// escape character for a single comma
		for (int x = 0 ; x < (int)Math.floor((double)commaCount/2.0) ; x++){
			sb.append(",");
		}
		
		// we had an even number of commas so just return
		if (commaCount % 2 == 0){
			return;
		}
		
		// we had an odd number of commas if we got here so
		// take the current string, append it to the list
		// and clear the StringBuilder buffer (sb)
		splitStr.add(sb.toString());
		sb.setLength(0);
	}

	/**
	 * 
	 * Splits {@code input} string by single commas '{@code ,}'. It is assumed
	 * if two commas are encountered they are assumed to be an escape for a single
	 * comma and the extra comma is removed.
	 * @param input
	 * @return 
	 */
	protected static List<String> splitStringBySingleCommas(final String input){
		char [] char_arr = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		List<String> splitStr = new ArrayList<>();
		int commaCount = 0;
		for (int i = 0 ; i < char_arr.length; i++){
			if (char_arr[i] == ','){
				commaCount++;
			} else {
				appendCommasAndOrAddNewStringToList(commaCount, sb, splitStr);
				commaCount = 0;
				sb.append(char_arr[i]);
			}
		}

		appendCommasAndOrAddNewStringToList(commaCount, sb, splitStr);

		splitStr.add(sb.toString());
		sb.setLength(0);

		return splitStr;
	}


	/**
	 * Constructor that parses cyVisualProperties mapping data
	 * passed in via the {@code str} parameter. The results can
	 * be obtained from {@link #get} and from {@link #getKeys} methods
	 * @param str
	 * @throws IOException 
	 */
	public MappingValueStringParser(final String str) throws IOException { 

		List<String> array = splitStringBySingleCommas(str);
		for( String n : array) {
			if(n.startsWith("COL=")) { 
			  String v = n.substring(4); 
			  _data.put("COL", v); 
			} else if ( n.startsWith("T=")) { 
			  String v = n.substring(2); 
			  _data.put("T", v);              
			} else { 
			  Matcher m = p.matcher(n); 
			  if ( !m.matches()) 
				throw new IOException ("Failed to parse mapping segment '"
						+ n + "' in mapping " + str + "."); 

			  _data.put(m.group(1), m.group(4)); 
			}  

		} 
	} 

	/**
	 * Gets value for key passed in. 
	 * @param key
	 * @return 
	 */
	public final String get(final String key) { 
		return _data.get(key); 
	} 

	/**
	 * Gets all keys in this parser
	 * @return 
	 */
	protected final Set<String> getKeys(){
		return _data.keySet();
	}
}
