package org.cxio.aspects.datamodels;

import java.util.List;

public class AttributesAspectUtils {

    public final static boolean isListType(final ATTRIBUTE_DATA_TYPE data_type) {
        return (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_BOOLEAN) || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_BYTE) || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_CHAR)
                || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE) || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_FLOAT) || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER)
                || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_LONG) || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_SHORT) || (data_type == ATTRIBUTE_DATA_TYPE.LIST_OF_STRING);
    }

    /**
     * Convenience method to go from a type described by a string to an actual
     * type enum entry.
     *
     * @param s
     * @return
     */
    public final static ATTRIBUTE_DATA_TYPE toDataType(final String s) {
        if (s.equals(ATTRIBUTE_DATA_TYPE.STRING.toString())) {
            return ATTRIBUTE_DATA_TYPE.STRING;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.BOOLEAN.toString())) {
            return ATTRIBUTE_DATA_TYPE.BOOLEAN;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.DOUBLE.toString())) {
            return ATTRIBUTE_DATA_TYPE.DOUBLE;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.INTEGER.toString())) {
            return ATTRIBUTE_DATA_TYPE.INTEGER;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LONG.toString())) {
            return ATTRIBUTE_DATA_TYPE.LONG;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.FLOAT.toString())) {
            return ATTRIBUTE_DATA_TYPE.FLOAT;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.SHORT.toString())) {
            return ATTRIBUTE_DATA_TYPE.SHORT;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.BYTE.toString())) {
            return ATTRIBUTE_DATA_TYPE.BYTE;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.CHAR.toString())) {
            return ATTRIBUTE_DATA_TYPE.CHAR;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_STRING.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_STRING;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_BOOLEAN.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_BOOLEAN;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_DOUBLE;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_INTEGER;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_LONG.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_LONG;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_FLOAT.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_FLOAT;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_SHORT.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_SHORT;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_BYTE.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_BYTE;
        }
        else if (s.equals(ATTRIBUTE_DATA_TYPE.LIST_OF_CHAR.toString())) {
            return ATTRIBUTE_DATA_TYPE.LIST_OF_CHAR;
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
        else if (o instanceof Double) {
            return ATTRIBUTE_DATA_TYPE.DOUBLE;
        }
        else if (o instanceof Integer) {
            return ATTRIBUTE_DATA_TYPE.INTEGER;
        }
        else if (o instanceof Long) {
            return ATTRIBUTE_DATA_TYPE.LONG;
        }
        else if (o instanceof Float) {
            return ATTRIBUTE_DATA_TYPE.FLOAT;
        }
        else if (o instanceof Short) {
            return ATTRIBUTE_DATA_TYPE.SHORT;
        }
        else if (o instanceof Byte) {
            return ATTRIBUTE_DATA_TYPE.BYTE;
        }
        else if (o instanceof Character) {
            return ATTRIBUTE_DATA_TYPE.CHAR;
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
                return ATTRIBUTE_DATA_TYPE.LIST_OF_FLOAT;
            }
            else if (e instanceof Short) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_SHORT;
            }
            else if (e instanceof Byte) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_BYTE;
            }
            else if (e instanceof Character) {
                return ATTRIBUTE_DATA_TYPE.LIST_OF_CHAR;
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
