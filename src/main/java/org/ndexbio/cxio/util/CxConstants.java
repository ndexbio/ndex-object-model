package org.ndexbio.cxio.util;

import java.util.HashMap;

/**
 * This class is to hold constants.
 *
 *
 * @author cmzmasek
 *
 */
public final class CxConstants {
    
    
    public final static String ID               = "@id";

    @Deprecated
    public final static Long   LONG_NUMBER_TEST = Long.valueOf(281474976710655L);
    
    public final static String EMPTY_NETWORK = "[ { \"numberVerification\" : [ { \"longNumber\" : 281474976710655 } ] }, {" + 
    		" \"metaData\" : [ { \"consistencyGroup\" : 1, \"elementCount\" : 0, \"idCounter\" : 0, \"name\" : \"nodes\", \"version\" : \"1.0\" }]},"
    		+ "{ \"status\": [{ \"error\": \"\",\"success\": true} ]}]";
    
	public final static  HashMap<String,Object> EMPTYOBJECT = new HashMap<>();


}
