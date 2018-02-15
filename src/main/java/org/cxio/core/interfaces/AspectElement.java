package org.cxio.core.interfaces;

import java.io.IOException;
import java.io.Serializable;

import org.cxio.util.JsonWriter;

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
    public String getAspectName();
    
    public abstract void write(JsonWriter out) throws IOException ;


}
