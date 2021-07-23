package org.ndexbio.cxio.core.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.IOException;
import java.io.Serializable;

import org.ndexbio.cxio.util.JsonWriter;

/**
 * The interface for all (named) AspectElements.
 *
 * @author cmzmasek
 *
 */
public interface AspectElement extends Comparable<AspectElement>,Serializable {

    /**
     * This returns the name of the aspect.
     *
     * @return the name of the aspect
     */
	@JsonIgnore()
    public String getAspectName();
    
    public abstract void write(JsonWriter out) throws IOException ;


}
