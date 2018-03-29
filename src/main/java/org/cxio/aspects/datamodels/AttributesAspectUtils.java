package org.cxio.aspects.datamodels;

import java.util.List;

public class AttributesAspectUtils {

    public final static boolean isListType(final ATTRIBUTE_DATA_TYPE data_type) {
        return ! data_type.isSingleValueType();
    }

    /**
     * Convenience method to go from a type described by a string to an actual
     * type enum entry.
     *
     * @param s
     * @return
     */
    public final static ATTRIBUTE_DATA_TYPE toDataType(final String s) {
        if (s.equals(ATTRIBUTE_DATA_TYPE.STRING.toString()) || 
        		s.equals("char")) {
            return ATTRIBUTE_DATA_TYPE.STRING;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.BOOLEAN.toString())) {
            return ATTRIBUTE_DATA_TYPE.BOOLEAN;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.DOUBLE.toString()) ||
        		  s.equals("float")) {
            return ATTRIBUTE_DATA_TYPE.DOUBLE;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.INTEGER.toString()) ||
        		s.equals("byte") || s.equals ("short")) {
            return ATTRIBUTE_DATA_TYPE.INTEGER;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LONG.toString())) {
            return ATTRIBUTE_DATA_TYPE.LONG;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_STRING.toString())||
        		s.equals ("list_of_string")) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_STRING;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_BOOLEAN.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_BOOLEAN;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE.toString())||
        		s.equals ("list_of_float")) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER.toString()) ||
        		s.equals ("list_of_byte") || s.equals("list_of_short")) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_LONG.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_LONG;
        }
        else {
            throw new IllegalArgumentException("type '" + s + "' is not supported");
        }
    }

    /**
     * Convenience method to determine the type of an object.
     *
     * @param o
     * @return
     */
    @SuppressWarnings("rawtypes")
    final public static ATTRIBUTE_DATA_TYPE determineDataType(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("attempt to determine type of null object");
        }
        if (o instanceof String) {
            return ATTRIBUTE_DATA_TYPE.STRING;
        }
        else if (o instanceof Boolean) {
            return ATTRIBUTE_DATA_TYPE.BOOLEAN;
        }
        else if (o instanceof Double || o instanceof Float) {
            return ATTRIBUTE_DATA_TYPE.DOUBLE;
        }
        else if (o instanceof Integer || o instanceof Short || o instanceof Byte) {
            return ATTRIBUTE_DATA_TYPE.INTEGER;
        }
        else if (o instanceof Long ) {
            return ATTRIBUTE_DATA_TYPE.LONG;
        }
        else if (o instanceof Character) {
            return ATTRIBUTE_DATA_TYPE.STRING;
        }
        else if (o instanceof List) {
            if (((List) o).isEmpty()) {
                throw new IllegalArgumentException("attempt to determine type of empty list");
            }
            final Object e = ((List) o).get(0);
            if (e instanceof String) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_STRING;
            }
            else if (e instanceof Boolean) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_BOOLEAN;
            }
            else if (e instanceof Double) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE;
            }
            else if (e instanceof Integer) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER;
            }
            else if (e instanceof Long) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_LONG;
            }
            else if (e instanceof Float) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE;
            }
            else if (e instanceof Short) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_LONG;
            }
            else if (e instanceof Byte) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_LONG;
            }
            else if (e instanceof Character) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_STRING;
            }
            else {
                throw new IllegalArgumentException("type '" + o.getClass() + "' is not supported");
            }
        }
        else {
            throw new IllegalArgumentException("type '" + o.getClass() + "' is not supported");
        }
    }

}
