package org.cxio.aspects.datamodels;

import java.io.IOException;
import java.util.List;

import org.cxio.aspects.writers.WriterUtil;
import org.cxio.core.AttributeValueSerializer;
import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 * This is the base class for EdgeAttributeElement, NodeAttributeElement, and NetworkAttributesElement.
 *
 * @author cmzmasek
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractAttributesAspectElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The name of this attribute. */
    public final static String ATTR_NAME        = "n";

    /** The subnetwork this attribute belongs to. */
    public final static String ATTR_SUBNETWORK  = "s";

    /** The data type of this attribute (either atomic or list). */
    public final static String ATTR_DATA_TYPE   = "d";

    /** The value(s) of this attribute. */
    public final static String ATTR_VALUES      = "v";

	@JsonProperty(ATTR_NAME)
    String                     _name;
	
	@JsonProperty(ATTR_SUBNETWORK)
    Long                       _subnetwork;

	@JsonProperty(ATTR_DATA_TYPE)
	
    ATTRIBUTE_DATA_TYPE        _data_type;
	
	@JsonProperty(ATTR_VALUES )
//	@JsonSerialize(using = AttributeValueSerializer.class)
    Object               _values;

    
    
	protected AbstractAttributesAspectElement ( ) { _data_type = ATTRIBUTE_DATA_TYPE.STRING; }
	
	
    /**
     * This is for getting the name of the attribute.
     *
     * @return the name of the attribute
     */
	@JsonIgnore
    public final String getName() {
        return _name;
    }

 
    /**
     * This returns the identifier of the subnetwork this attribute belongs to.
     *
     * @return the identifier of the subnetwork this attribute belongs to
     */
	@JsonIgnore
    public final Long getSubnetwork() {
        return _subnetwork;
    }

    /**
     * This returns the data type of the attribute.
     *
     *
     * @return the data type of the attribute
     */
    @JsonIgnore
    public final ATTRIBUTE_DATA_TYPE getDataType() {
        return _data_type;
    }

    /**
     * This returns the list values of the (list) attribute as list of Strings.
     *
     * @return the list values of the (list) attribute as list of Strings
     */
    @SuppressWarnings("unchecked")
    @JsonIgnore
	public final List<String> getValues() {
    	if ( _values == null) return null;
        if (_values instanceof String) {
            throw new IllegalStateException("attempt to return single value as list of values: \"" + 
              _values + "\" in attribute " + getName());
        }
        return (List<String>)_values;
    }

    @JsonIgnore
    public String getValueAsJsonString() throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsString(_values);
    }
    
    /**
     * This returns the value of the (single) attribute as String.
     *
     * @return the value of the (single) attribute as String
     */
    @JsonIgnore
    public final String getValue() {
    	if (_values == null) return null;
    	
        if (! (_values instanceof String)) {
            throw new IllegalStateException("attempt to return list of values as single value: \"" + 
              _values + "\" in attribute " + getName());
        }
        return ( String) _values;
    }

    /**
     * This returns true if the value of this attribute is a single value,
     * false if it is a list of values (even if the list just contains one value).
     *
     *
     * @return true if single value, false if list of values
     */
    @JsonIgnore
   public final boolean isSingleValue() {
	  return ATTRIBUTE_DATA_TYPE.isSingleValueType(this._data_type) ;
      //  return (_values == null) || (_values instanceof String) ;
    } 
    
    
    @Override
	public void write(JsonWriter w) throws IOException {
        WriterUtil.writeAttributesElement(w,  this /*, null*/);		
        w.flush();
	} 
    

    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getAspectName());
        sb.append(": ");
        sb.append("\n");
        sb.append("\n");
        if (_subnetwork != null) {
            sb.append("subnetwork       : ");
            sb.append(_subnetwork);
            sb.append("\n");
        }
        sb.append("name             : ");
        sb.append(_name);
        sb.append("\n");
        if (isSingleValue()) {
            sb.append("value            : ");
            sb.append(getValue());
        }
        else {
            sb.append("values           : ");
            sb.append(_values);
        }
        sb.append("\n");
        sb.append("data type        : ");
        sb.append(_data_type.toString());
        sb.append("\n");
        return sb.toString();
    }

}