package org.ndexbio.cx2.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Copied from StringPaser class in cx_support 
 * @author jingchen
 *
 */
public class MappingValueStringParser {
	   private final Map<String, String> _data = new HashMap<>(); 
	     
	    private static final Pattern p0 = Pattern.compile("(?<=(^|,))([^,]|,,)*(?=(,|$))"); 
	 
	    private static final Pattern p = Pattern.compile("^((K|V|L|E|G|OV)=([0-9]+))=(.*)$"); 
	 
	    public static List<String> commaDelimitedListStringToStringList2(String list) { 
	 
	        Matcher matcher = p0.matcher(list); 
	        ArrayList<String> result = new ArrayList<>(); 
	        while (matcher.find()) { 
	            String v = matcher.group(); 
	            if ( v.length() > 0 ) 
	              result.add(v); 
	        } 
	        return result; 
	    } 
	 
	    public MappingValueStringParser(final String str) throws IOException { 
	       
	        List<String> array = commaDelimitedListStringToStringList2(str); 
	        for( String n : array) { 
	            if(n.startsWith("COL=")) { 
	              String v = n.substring(4); 
	              _data.put("COL", v.replace(",,", ",")); 
	            } else if ( n.startsWith("T=")) { 
	              String v = n.substring(2); 
	              _data.put("T", v.replace(",,", ","));              
	            } else { 
	              Matcher m = p.matcher(n); 
	              if ( !m.matches()) 
	                throw new IOException ("Failed to parse mapping string: "+ n + " in mapping: " + str); 
	 
	              _data.put(m.group(1), m.group(4).replace(",,", ",")); 
	            }  
	 
	        } 
	    } 
	 
	    public final String get(final String key) { 
	        return _data.get(key); 
	    } 
}
