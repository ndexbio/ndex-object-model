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


    public NodeAttributesElement(final Long subnetwork, final Long property_of, final String name, final List<String> values, final ATTRIBUTE_DATA_TYPE type)  {
        if (!AttributesAspectUtils.isListType(type)) {
            throw new IllegalArgumentException("node attribute element '" + name + "': list of values provided, but given data type is " + type.toString());
        }
        _data_type = type;
        _subnetwork = subnetwork;
        _property_of = property_of;
        _name = name;
        _values = values;
    }

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
        _subnetwork = null;
        _property_of = property_of;
        _name = name;
        _values = values;

    }

 

    @Override
	@JsonIgnore
    public String getAspectName() {
        return ASPECT_NAME;
    }

}
