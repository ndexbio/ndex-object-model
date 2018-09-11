package org.ndexbio.cxio.aspects.datamodels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The supported data types (either as atomic value or as list).
 *
 */
public enum ATTRIBUTE_DATA_TYPE {

    BOOLEAN("boolean",true),
//    BYTE("byte"),
//    CHAR("char"),
    DOUBLE("double",true),
//    FLOAT("float"),
    INTEGER("integer",true),
    LONG("long",true),
//    SHORT("short"),
    STRING("string",true),
    LIST_OF_BOOLEAN("list_of_boolean",false),
//    LIST_OF_BYTE("list_of_byte"),
//    LIST_OF_CHAR("list_of_char"),
    LIST_OF_DOUBLE("list_of_double",false),
//    LIST_OF_FLOAT("list_of_float"),
    LIST_OF_INTEGER("list_of_integer",false),
    LIST_OF_LONG("list_of_long", false),
//    LIST_OF_SHORT("list_of_short"),
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

    // removed because this duplicates toString() function
  /*  public static String toCxLabel(final ATTRIBUTE_DATA_TYPE dt) {
        switch (dt) {
        case BOOLEAN:
            return "boolean";
 //       case BYTE:
 //           return "byte";
 //       case CHAR:
 //           return "char";
        case DOUBLE:
            return "double";
 //       case FLOAT:
 //           return "float";
        case INTEGER:
            return "integer";
        case LONG:
            return "long";
 //       case SHORT:
 //           return "short";
        case STRING:
            return "string";
        case LIST_OF_BOOLEAN:
            return "list_of_boolean";
//        case LIST_OF_BYTE:
//            return "list_of_byte";
//        case LIST_OF_CHAR:
//            return "list_of_char";
        case LIST_OF_DOUBLE:
            return "list_of_double";
//        case LIST_OF_FLOAT:
//            return "list_of_float";
        case LIST_OF_INTEGER:
            return "list_of_integer";
        case LIST_OF_LONG:
            return "list_of_long";
 //       case LIST_OF_SHORT:
 //           return "list_of_short";
        case LIST_OF_STRING:
            return "list_of_string";
        default:
            throw new IllegalStateException("don't know type " + dt);
        }
    } */

    @JsonCreator
    public static ATTRIBUTE_DATA_TYPE fromCxLabel(final String s) {
        switch (s) {
        case "boolean":
            return BOOLEAN;
  /*      case "byte":
            return BYTE;
        case "char":
            return CHAR;*/
        case "double":
            return DOUBLE;
      //  case "float":
      //      return FLOAT;
        case "integer":
            return INTEGER;
        case "long":
            return LONG;
 //       case "short":
 //           return SHORT;
        case "string":
            return STRING;
        case "list_of_boolean":
            return LIST_OF_BOOLEAN;
  //      case "list_of_byte":
  //          return LIST_OF_BYTE;
  //      case "list_of_char":
  //          return LIST_OF_CHAR;
        case "list_of_double":
            return LIST_OF_DOUBLE;
  //      case "list_of_float":
  //          return LIST_OF_FLOAT;
        case "list_of_integer":
            return LIST_OF_INTEGER;
        case "list_of_long":
            return LIST_OF_LONG;
  //      case "list_of_short":
  //          return LIST_OF_SHORT;
        case "list_of_string":
            return LIST_OF_STRING;
        default:
            throw new IllegalStateException("data type " + s + " is not supported in this version of CX.");
        }
    }
    
    public  boolean isSingleValueType() {
    	return isSingleValueType;
    }
}