package org.cxio.aspects.datamodels;

import java.io.IOException;

import org.cxio.aspects.writers.WriterUtil;
import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * This is the base class for EdgeAttributeElement, NodeAttributeElement, and NetworkAttributesElement.
 *
 * @author cmzmasek
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public abstract class AbstractElementAttributesAspectElement extends AbstractAttributesAspectElement {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The node or edge this attribute is a property of. */
    public final static String ATTR_PROPERTY_OF = "po";

 	@JsonProperty(ATTR_PROPERTY_OF)
    Long                 _property_of;
	

 
    /**
     * This returns a list of identifiers of the elements this attribute is a property of.
     *
     * @return a list of identifiers of the elements this attribute is a property o
     */
    public Long getPropertyOf() {
        return _property_of;
    }
    
    
    public void setPropertyOf(Long id) {
    	this._property_of = id;
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
        sb.append("po: ");
        sb.append(_property_of);
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