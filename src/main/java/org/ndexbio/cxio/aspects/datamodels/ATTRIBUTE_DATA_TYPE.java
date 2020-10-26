package org.ndexbio.cxio.aspects.datamodels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The supported data types (either as atomic value or as list).
 *
 */
public enum ATTRIBUTE_DATA_TYPE {

    BOOLEAN("boolean",true),
    DOUBLE("double",true),
    INTEGER("integer",true),
    LONG("long",true),
    STRING("string",true),
    LIST_OF_BOOLEAN("list_of_boolean",false),
    LIST_OF_DOUBLE("list_of_double",false),
    LIST_OF_INTEGER("list_of_integer",false),
    LIST_OF_LONG("list_of_long", false),
    LIST_OF_STRING("list_of_string",false);

    private final String _name;
    private boolean isSingleValueType;

    private ATTRIBUTE_DATA_TYPE(final String name, boolean isSingleValue) {
        _name = name;
        isSingleValueType = isSingleValue;
    }

    @Override
    @JsonValue
    public String toString() {
        return _name;
    }

 
    @JsonCreator
    public static ATTRIBUTE_DATA_TYPE fromCxLabel(final String s) {
        switch (s) {
        case "boolean":
            return BOOLEAN;
        case "double":
        case "float":	
            return DOUBLE;
        case "integer":
            return INTEGER;
        case "long":
            return LONG;
        case "string":
            return STRING;
        case "list_of_boolean":
            return LIST_OF_BOOLEAN;
        case "list_of_double":
        case "list_of_float":	
            return LIST_OF_DOUBLE;
        case "list_of_integer":
            return LIST_OF_INTEGER;
        case "list_of_long":
            return LIST_OF_LONG;
        case "list_of_string":
            return LIST_OF_STRING;
        default:
            throw new IllegalStateException("data type " + s + " is not supported in this version of CX.");
        }
    }
    
    public  boolean isSingleValueType() {
    	return isSingleValueType;
    }
    
    
    public  ATTRIBUTE_DATA_TYPE elementType() {
    	if ( isSingleValueType )
    		return null;
    	
        switch (_name) {
        case "list_of_string":
            return STRING;
        case "list_of_double":
        case "list_of_float":	
            return DOUBLE;
        case "list_of_integer":
            return INTEGER;
        case "list_of_long":
            return LONG;
        case "list_of_boolean":
            return BOOLEAN;
        default:
            throw new IllegalStateException("data type " + _name + " is not supported in this version of CX.");
        }
    }

}