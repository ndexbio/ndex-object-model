package org.ndexbio.cxio.metadata;

import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.CxioUtil;
import org.ndexbio.cxio.util.ForceLongDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This is to hold meta data.
 * It is intended to be used together with class MetaData.
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(Include.NON_NULL)

public class MetaDataElement {


    //final private SortedMap<String, Object> _data;

	@JsonProperty("name")	
	private String name;

	@JsonProperty("elementCount")
	@JsonDeserialize(using=ForceLongDeserializer.class)	
	private Long elementCount;
	
	@JsonProperty("idCounter")	
	@JsonDeserialize(using=ForceLongDeserializer.class)
	private Long idCounter;
	
	//@JsonProperty("checksum")	
	//private String checksum;
	
	@JsonProperty("version")	
	private String version;
	
	@JsonProperty("consistencyGroup")
	@JsonDeserialize(using=ForceLongDeserializer.class)
	private Long consistencyGroup;
	
	//@JsonProperty("properties")	
	//private List<Map.Entry<String, String>> properties;
   
	private static final String errMsg = "Name attribute of MetaDataElement can't be null.";
	
    /**
     * Constructor to create an empty MetaDataElement.
     *
     */
    public MetaDataElement() {
    //		properties = new ArrayList<>();
    }

    public MetaDataElement(String name, String version) {   	
    		setName(name);
    		this.version = version;
    }
    /*
     * Constructor to create a MetaDataElement containing the
     * data provided in a SortedMap (String to Object).
     *
     */
  /*  public MetaDataElement(final SortedMap<String, Object> data) {
        _data = data;
    } */

    /**
     * Convenience method to add a "property".
     *
     * @param property a key value pair
     */
   /* public final void addProperty(final String key, final String value) {
    	
    	 	properties.add(new SimpleEntry<>(key,value));
 
    } */

    /**
     * Convenience method to get the consistency group.
     *
     * @return the consistency group
     */
    public final Long getConsistencyGroup() {
    		return this.consistencyGroup;
    }

   
    /**
     * Convenience method to get the element count.
     *
     * @return the element count
     */
    public final Long getElementCount() {
       return this.elementCount;
    }

    /**
     * Convenience method to get the id counter.
     *
     * @return the id counter
     */
    public final Long getIdCounter() {
       return this.idCounter;
    }

    /**
     * Convenience method to get the name (of the corresponding aspect).
     *
     * @return the name (of the corresponding aspect)
     */
    public final String getName() {
      return name;
    }

    /**
     * Convenience method to get all "properties" .
     *
     * @return  all "properties"
     */
   /* public final List<Map.Entry<String, String>> getProperties() {
    		return properties;
    } */

    /**
     * Convenience method to get the (corresponding aspect) version.
     *
     * @return the (corresponding aspect) version
     */
    public final String getVersion() {
       return version;
    }

    /**
     * Convenience method to set the consistency group.
     *
     * @param c
     */
    public final void setConsistencyGroup(final Long c) {
        this.consistencyGroup = c;
    }

    /**
     * Convenience method to set the element count.
     *
     * @param c
     */
    public final void setElementCount(final Long c) {
        elementCount = c;
    }

    /**
     * Convenience method to set the id counter.
     *
     * @param c
     */
    public final void setIdCounter(final Long c) {
        idCounter= c;
    }


    /**
     * Convenience method to set the name (of the corresponding aspect).
     *
     * @param name the name (of the corresponding aspect)
     */
    public final void setName(final String elementName) {
    		if ( elementName == null)
    			throw new IllegalArgumentException(errMsg);
        this.name = elementName;
    }

    /**
     * Convenience method to set the name from an AspectElement.
     *
     * @param e an AspectElement (to get the name from)
     */
    public final void setName(final AspectElement e) {
        setName(e.getAspectName());
    }

    /**
     * Convenience method to set the (corresponding aspect) version.
     *
     * @param version the (corresponding aspect) version
     */
    public final void setVersion(final String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
            sb.append("name: " + name );
            sb.append(CxioUtil.LINE_SEPARATOR);
            sb.append("version: " + version );
            sb.append(CxioUtil.LINE_SEPARATOR);
            sb.append("elementCount: " + this.elementCount);
            sb.append(CxioUtil.LINE_SEPARATOR);
            sb.append("idCounter: " + this.idCounter );
            sb.append(CxioUtil.LINE_SEPARATOR);
        return sb.toString();
    }

    /**
     * The checksum attribute in metadata is optional. It might be removed in future versions of CX specification.
     * @return
     */
    //@Deprecated
/*	public String getChecksum() {
		return checksum;
	} */

    /**
     * checksum attribute in metadata is optional. It might be removed in future versions of CX specification.
     * 
     * @param checksum
     */
    //@Deprecated
	/*public void setChecksum(String checksum) {
		this.checksum = checksum;
	}*/

}
