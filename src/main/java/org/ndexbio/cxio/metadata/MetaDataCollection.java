package org.ndexbio.cxio.metadata;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.CxioUtil;
import org.ndexbio.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is a collection of MetaDataElements.
 * Its primary purpose is to serialize this
 * data to json, and to de-serialize from json.
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public final class MetaDataCollection implements Serializable, Iterable<MetaDataElement> {

    /**
     * The name of the MetaDataElement list when serialized to json.
     *
     */
    public final static String                    NAME             = "metaData";

    private static final long                     serialVersionUID = 7233278148613095352L;

	@JsonProperty(NAME)	
    private List<MetaDataElement> _data;

	
	public List<MetaDataElement> getMetaData() {return _data;}
	
	public void setMetaData(List<MetaDataElement> md) { 
		_data = md;
	}

    /**
     * This is to create a MetaData object from a JsonParser.
     *
     * @param jp a JsonParser
     * @return a MetaDataCollection object
     * 
     */
    public static MetaDataCollection createInstanceFromJson(final JsonParser jp) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        return m.readValue(jp, MetaDataCollection.class);
    }

    /**
     * This is to create a MetaData object from a json formatted String.
     *
     * @param str a json formatted String
     * @return a MetaDataCollection object
     * @throws IOException
     */
    public final static MetaDataCollection createInstanceFromJson(final String str) throws IOException {
        final ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);        
        return m.readValue(str, MetaDataCollection.class);
    } 

    /**
     * Constructor, to create an empty MetaData object.
     *
     */
    public MetaDataCollection() {
        _data = new ArrayList<>();
    }

    /**
     * This method id to add a MetaDataElement
     *
     * @param e a MetaDataElement
     */
    public final void add(final MetaDataElement e) {
        _data.add(e);
    }
    
    public final void addAt(int position, final MetaDataElement e) {
        _data.add(position, e);
    }
    
    /**
     * * Convenience method to create and add one meta data element.
     *
     * @param elements
     * @param consistency_group
     * @param version
     * @param last_update
     * @param id_counter
     */
    public final void addMetaDataElement(final List<AspectElement> elements, final long consistency_group, final String version, final long id_counter) {
        if ((elements != null) && !elements.isEmpty()) {
            final MetaDataElement e = new MetaDataElement();
            e.setName(elements.get(0).getAspectName());
            e.setConsistencyGroup(consistency_group);
            e.setVersion(version);
            e.setIdCounter(id_counter);
            e.setElementCount((long) elements.size());
            add(e);
        }
    }

    public void clear() {
        _data.clear();
    }

    /**
     * Convenience method to get the consistency group of the meta data element with
     * a give name.
     *
     * @param name
     * @return
     */
    public final Long getConsistencyGroup(final String name) {
        final MetaDataElement e = getMetaDataElement(name);
        if (e != null) {
            return e.getConsistencyGroup();
        }
        return null;
    }

    /**
     * Convenience method to get the element count of the meta data element with
     * a give name.
     *
     * @param name
     * @return the element count
     */
    public final Long getElementCount(final String name) {
        final MetaDataElement e = getMetaDataElement(name);
        if (e != null) {
            return e.getElementCount();
        }
        return null;
    }

    /**
     * Convenience method to get the id counter of the meta data element with
     * a given aspect name.
     *
     * @param name
     * @return the id counter
     */
    public final Long getIdCounter(final String name) {
        final MetaDataElement e = getMetaDataElement(name);
        if (e != null) {
            return e.getIdCounter();
        }
        return null;
    }


    /*
     * This is to get the meta data as list of sorted maps (String to Object).
     *
     * @return a list of sorted maps (String to Object)
     */
   /* public final Collection<SortedMap<String, Object>> getMetaData() {
        return _data;
    }*/

    /**
     * This method returns the MetaDataElement with a given name
     * (for which getName() returns name).
     * Return null if not found.
     * Throws a IllegalArgumentException if more than two elements with the same name.
     *
     * @param name the name of the MetaDataElement to find
     * @return a MetaDataElement with a given name, null if no such element
     */
    public final MetaDataElement getMetaDataElement(final String name) {
        MetaDataElement res = null;
        for (final MetaDataElement e : _data) {
            if ((e.getName() != null) && e.getName().equals(name)) {
                if (res == null) {
                    res = e;
                }
                else {
                    throw new IllegalArgumentException("more than one meta data element with name '" + name + "'");
                }
            }
        }
        return res;
    }

    /**
     * Convenience method to get the (corresponding aspect) version of the meta data element with
     * a give name.
     *
     * @param name
     * @return the (corresponding aspect) version
     */
    public final String getVersion(final String name) {
        final MetaDataElement e = getMetaDataElement(name);
        if (e != null) {
            return e.getVersion();
        }
        return null;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns a Iterator to iterate over the MetaDataElements.
     *
     * @return Iterator to iterate over the MetaDataElements
     */
    @Override
    public Iterator<MetaDataElement> iterator() {
        return _data.iterator();
    }

    /**
     * To remove a MetaDataElement with a given name.
     * Returns null if no such element.
     *
     *
     * @param name the name of the MetaDataElement to remove
     * @return the removed MetaDataElement, null if no such element.
     */
    public MetaDataElement remove(final String name) {
        MetaDataElement remove_me = null;
        if (!isEmpty()) {
            int found_index = -1;
            for (int i = 0; i < size(); i++) {
                if (_data.get(i).getName().equals(name)) {
                    if (found_index >= 0) {
                        throw new IllegalArgumentException("more than one meta data element with name '" + name + "'");
                    }
                    found_index = i;
                }
            }
            if (found_index >= 0) {
                remove_me = _data.remove(found_index);
            }
        }
        return remove_me;
    }

     /**
     * Convenience method to set the id counter for the meta data element with
     * a give name.
     * If no such element exist, a new one will be created.
     *
     * @param name
     * @param c
     */
    public final void setIdCounter(final String name, final Long c) {
        final MetaDataElement e = checkIfElementPresent(name);
        e.setIdCounter(c);
    }

    /*
     * Convenience method to set the last update value for the meta data element with
     * a give name.
     * If no such element exist, a new one will be created.
     *
     * @param name
     * @param last_update
     */
   /* public final void setLastUpdate(final String name, final Long last_update) {
        final MetaDataElement e = checkIfElementPresent(name);
        e.setLastUpdate(last_update);
    } */

    /**
     * This is used to set a property.
     *
     * @param name
     * @param key
     * @param value
     */
  /*  public final void setProperty(final String name, final String key, final String value) {
        final MetaDataElement e = checkIfElementPresent(name);
        e.addProperty(key, value);
    } */

    /**
     * Convenience method to set the (corresponding aspect) version for the meta data element with
     * a give name.
     * If no such element exist, a new one will be created.
     *
     * @param name
     * @param version the (corresponding aspect) version
     */
    public final void setVersion(final String name, final String version) {
        final MetaDataElement e = checkIfElementPresent(name);
        e.setVersion(version);
    }

    /**
     * This returns the number of MetaDataElements.
     *
     * @return the number of MetaDataElements
     */
    public final int size() {
        return _data.size();
    }

 
    /**
     * This is the serialize this MetaData object to json.
     *
     * @param w a JsonWriter
     * @throws IOException
     */
    public final void toJson(final JsonWriter w) throws IOException {
        w.writeObject(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final MetaDataElement e : _data) {
                sb.append(e);
                sb.append(CxioUtil.LINE_SEPARATOR);
        }
        return sb.toString();
    }

    private MetaDataElement checkIfElementPresent(final String name) {
        MetaDataElement e = getMetaDataElement(name);
        if (e == null) {
            e = new MetaDataElement();
            e.setName(name);
            add(e);
        }
        return e;
    }
    
  /*  public void validate() throws IOException {
    		for ( SortedMap<String, Object> e: _data) {
    			// check name
    			Object n = e.get(MetaDataElement.NAME);
    			if ( n == null) {
    				throw new IOException ("name attribute was missing in one of the metadata element.");
    			} else if ( !(n instanceof String)){
    				throw new IOException ("name attriubute has to be a string in metadata element , but it is " + n.getClass().getName() + " instead in this document.");
    			}
    			
    			String name = (String)n;
    			
    			// check version 
    			Object v = e.get(MetaDataElement.VERSION);
    			if ( v == null) {
    				throw new IOException ("Attribute version was missing in metadata element " + name);
    			} else if ( !(n instanceof String)){
    				throw new IOException ("attriubute version is not a string in metadata element " + name);
    			}

    			v = e.get(MetaDataElement.CONSISTENCY_GROUP);
    			if ( v != null) {
    				throw new IOException ("attriubute version is not a string in metadata element " + name);
    			}
    			
    		}
    } */

    /*
     * Return the contents as Object array.
     *
     * @return the contents as Object arra
     */
  /*  public final Object[] toArray() {
        return _data.toArray();
    } */

    /*
     * This is to get the meta data as list of MetaDataElements
     *
     * @return a list of MetaDataElements
     */
  /*  public final Collection<MetaDataElement> toCollection() {
        final ArrayList<MetaDataElement> l = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            l.add(new MetaDataElement(_data.get(i)));
        }
        return l;
    } */

    /*
     * Convenience method to set the consistency group for the meta data element with
     * a give name.
     * If no such element exist, a new one will be created.
     *
     * @param name
     * @param c
     */
   /* public final void setConsistencyGroup(final String name, final Long c) {
        final MetaDataElement e = checkIfElementPresent(name);
        e.setConsistencyGroup(c);
    } */

    /**
     * Convenience method to set the element count for the meta data element with
     * a give name.
     * If no such element exist, a new one will be created.
     *
     * @param name
     * @param c
     */
    public final void setElementCount(final String name, final Long c) {
        final MetaDataElement e = checkIfElementPresent(name);
        e.setElementCount(c);
    } 

    /*  public final boolean addAt( int position, final MetaDataElement e) {
    _data.add(position, e.getData());
    return true;
} */

/*
 * Convenience method to create and add one meta data element.
 *
 * @param aspect_name
 * @param consistency_group
 * @param version
 * @param last_update
 * @param id_counter
 * @param element_count
 */
/*    public final void addMetaDataElement(final String aspect_name, final Long consistency_group, final String version, final Long last_update, final Long id_counter, final Long element_count) {
    final MetaDataElement e = new MetaDataElement();
    e.setName(aspect_name);
    e.setConsistencyGroup(consistency_group);
    e.setVersion(version);
    e.setLastUpdate(last_update);
    e.setIdCounter(id_counter);
    e.setElementCount(element_count);
    add(e);
} */

/*
 * * Convenience method to create and add one meta data element.
 *
 * @param elements
 * @param consistency_group
 * @param version
 * @param last_update
 * @param id_counter
 */
/* public final void addMetaDataElement(final List<AspectElement> elements, final Long consistency_group, final String version, final Long last_update, final Long id_counter) {
    if ((elements != null) && !elements.isEmpty()) {
        final MetaDataElement e = new MetaDataElement();
        e.setName(elements.get(0).getAspectName());
        e.setConsistencyGroup(consistency_group);
        e.setVersion(version);
        e.setIdCounter(id_counter);
        e.setElementCount((long) elements.size());
        add(e);
    }
} */

    
}