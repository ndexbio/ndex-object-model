package org.cxio.aspects.datamodels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The supported data types (either as atomic value or as list).
 *
 */
public enum ATTRIBUTE_DATA_TYPE {

    BOOLEAN("boolean"),
    BYTE("byte"),
    CHAR("char"),
    DOUBLE("double"),
    FLOAT("float"),
    INTEGER("integer"),
    LONG("long"),
    SHORT("short"),
    STRING("string"),
    LIST_OF_BOOLEAN("list_of_boolean"),
    LIST_OF_BYTE("list_of_byte"),
    LIST_OF_CHAR("list_of_char"),
    LIST_OF_DOUBLE("list_of_double"),
    LIST_OF_FLOAT("list_of_float"),
    LIST_OF_INTEGER("list_of_integer"),
    LIST_OF_LONG("list_of_long"),
    LIST_OF_SHORT("list_of_short"),
    LIST_OF_STRING("list_of_string");

    private final String _name;

    private ATTRIBUTE_DATA_TYPE(final String name) {
        _name = name;
    }

    @Override
    @JsonValue
    public String toString() {
        return _name;
    }

    public static String toCxLabel(final ATTRIBUTE_DATA_TYPE dt) {
        switch (dt) {
        case BOOLEAN:
            return "boolean";
        case BYTE:
            return "byte";
        case CHAR:
            return "char";
        case DOUBLE:
            return "double";
        case FLOAT:
            return "float";
        case INTEGER:
            return "integer";
        case LONG:
            return "long";
        case SHORT:
            return "short";
        case STRING:
            return "string";
        case LIST_OF_BOOLEAN:
            return "list_of_boolean";
        case LIST_OF_BYTE:
            return "list_of_byte";
        case LIST_OF_CHAR:
            return "list_of_char";
        case LIST_OF_DOUBLE:
            return "list_of_double";
        case LIST_OF_FLOAT:
            return "list_of_float";
        case LIST_OF_INTEGER:
            return "list_of_integer";
        case LIST_OF_LONG:
            return "list_of_long";
        case LIST_OF_SHORT:
            return "list_of_short";
        case LIST_OF_STRING:
            return "list_of_string";
        default:
            throw new IllegalStateException("don't know type " + dt);
        }
    }

    @JsonCreator
    public static ATTRIBUTE_DATA_TYPE fromCxLabel(final String s) {
        switch (s) {
        case "boolean":
            return BOOLEAN;
        case "byte":
            return BYTE;
        case "char":
            return CHAR;
        case "double":
            return DOUBLE;
        case "float":
            return FLOAT;
        case "integer":
            return INTEGER;
        case "long":
            return LONG;
        case "short":
            return SHORT;
        case "string":
            return STRING;
        case "list_of_boolean":
            return LIST_OF_BOOLEAN;
        case "list_of_byte":
            return LIST_OF_BYTE;
        case "list_of_char":
            return LIST_OF_CHAR;
        case "list_of_double":
            return LIST_OF_DOUBLE;
        case "list_of_float":
            return LIST_OF_FLOAT;
        case "list_of_integer":
            return LIST_OF_INTEGER;
        case "list_of_long":
            return LIST_OF_LONG;
        case "list_of_short":
            return LIST_OF_SHORT;
        case "list_of_string":
            return LIST_OF_STRING;
        default:
            throw new IllegalStateException("don't know type " + s);
        }
    }
    
    public static boolean isSingleValueType(final ATTRIBUTE_DATA_TYPE dt) {
    	switch (dt) {
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FLOAT:
        case INTEGER:
        case LONG:
        case SHORT:
        case STRING:
        	return true;
        default:
            return false;
        }
    }
}