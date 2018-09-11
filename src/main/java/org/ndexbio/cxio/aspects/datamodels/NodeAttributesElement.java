package org.ndexbio.cxio.aspects.datamodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class is used to present one attribute of a network node.
 * An attribute consists of a name, value(s), type, and
 * a(n) identifier(s) of the node(s) the attribute is a property of.
 *
 * @author cmzmasek
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public final class NodeAttributesElement extends AbstractElementAttributesAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5486834312638179999L;
	public final static String ASPECT_NAME = "nodeAttributes";

    
    public NodeAttributesElement() {
    	super();
    }
  /*  
    public NodeAttributesElement(final Long subnetwork, final Long property_of, final String name, final List<String> values) {
        _data_type = ATTRIBUTE_DATA_TYPE.LIST_OF_STRING;
//        _is_single_value = false;
        _subnetwork = subnetwork;
        _property_of = property_of;
        _name = name;
        _values = values;
    }
*/

    public NodeAttributesElement(final Long subnetwork, final Long property_of, final String name, final List<String> values, final ATTRIBUTE_DATA_TYPE type)  {
        if (!AttributesAspectUtils.isListType(type)) {
            throw new IllegalArgumentException("node attribute element '" + name + "': list of values provided, but given data type is " + type.toString());
        }
        _data_type = type;
 //       _is_single_value = false;
        _subnetwork = subnetwork;
        _property_of = property_of;
        _name = name;
        _values = values;
    }
/*
    public NodeAttributesElement(final Long subnetwork, final Long property_of, final String name, final String value) {
        _data_type = ATTRIBUTE_DATA_TYPE.STRING;
 //       _is_single_value = true;
        _subnetwork = subnetwork;
        _property_of = property_of;
        _name = name;
        _values = value;

    }

    public NodeAttributesElement(final Long subnetwork, final Long property_of, final String name, final Object value) {
        if (value instanceof List) {
            throw new IllegalArgumentException("constructor only applicable for singe values");
        }
        _data_type = AttributesAspectUtils.determineDataType(value);
//       _is_single_value = true;
        _subnetwork = subnetwork;
        _property_of = property_of;
        _name = name;
        _values = String.valueOf(value);
    } */

    public NodeAttributesElement(final Long subnetwork, final Long property_of, final String name, final String value, final ATTRIBUTE_DATA_TYPE type)  {
        if (AttributesAspectUtils.isListType(type)) {
            throw new IllegalArgumentException("node attribute element '" + name + "': single value provided, but given data type is " + type.toString());
        }
        _data_type = type;
 //       _is_single_value = true;
        _subnetwork = subnetwork;
        _property_of = property_of;
        _name = name;
        _values = value;

    }

    public NodeAttributesElement(final Long property_of, final String name, final String value, final ATTRIBUTE_DATA_TYPE type)  {

        if (AttributesAspectUtils.isListType(type)) {
            throw new IllegalArgumentException("node attribute element '" + name + "': single value provided, but given data type is " + type.toString());
        }
        _data_type = type;
 //       _is_single_value = true;
        _subnetwork = null;
        _property_of = property_of;
        _name = name;
        _values = value;

    }

    public NodeAttributesElement(final Long property_of, final String name, final List<String> values, final ATTRIBUTE_DATA_TYPE type)  {

        if (!AttributesAspectUtils.isListType(type)) {
            throw new IllegalArgumentException("node attribute element '" + name + "': list of values provided, but given data type is " + type.toString());
        }
        _data_type = type;
//        _is_single_value = false;
        _subnetwork = null;
        _property_of = property_of;
        _name = name;
        _values = values;

    }

 /*   public NodeAttributesElement(final Long property_of, final String name, final Object value) {
        if (value instanceof List) {
            throw new IllegalArgumentException("constructor only applicable for singe values");
        }
        _data_type = AttributesAspectUtils.determineDataType(value);
   //     _is_single_value = true;
        _subnetwork = null;
        _property_of = (property_of);
        _name = name;
        _values = String.valueOf(value);
    } */

    @Override
	@JsonIgnore
    public String getAspectName() {
        return ASPECT_NAME;
    }

 /*   @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(ASPECT_NAME);
        sb.append(": ");
        sb.append("\n");
        sb.append("property of nodes: ");
        sb.append(_property_of);
        sb.append("\n");
        sb.append("name : ");
        sb.append(_name);
        sb.append("\n");
        if (IsSingleValue()) {
            sb.append("value            : ");
            sb.append(getValue());
        }
        else {
            sb.append("values           : ");
            sb.append(_values);
        }
        sb.append("\n");
        sb.append("type : ");
        sb.append(_data_type.toString());
        sb.append("\n");
        return sb.toString();
    } */


/*
    public final static NodeAttributesElement createInstanceWithSingleValue(final Long subnetwork, final Long property_of, final String name, final String value, final ATTRIBUTE_DATA_TYPE type) throws JsonProcessingException {

        return new NodeAttributesElement(subnetwork, property_of, name, DatamodelsUtil.removeParenthesis(value, type), type);
    }



    public final static NodeAttributesElement createInstanceWithMultipleValues(final Long subnetwork, final Long property_of, final String name, final String values, final ATTRIBUTE_DATA_TYPE type) throws IOException {

        return new NodeAttributesElement(subnetwork, property_of, name, DatamodelsUtil.parseStringToStringList(values), type);
    }
*/
}
