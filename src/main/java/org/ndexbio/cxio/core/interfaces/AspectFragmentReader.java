package org.ndexbio.cxio.core.interfaces;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * The interface for Aspect Fragment readers (Aspect Fragments are lists of
 * Aspect Elements). Each AspectElement implementation needs to have a
 * corresponding AspectFragmentReader.
 *
 * @author cmzmasek
 *
 */
public interface AspectFragmentReader extends Comparable<AspectFragmentReader> {

    /**
     * This returns the name of the Aspect a AspectFragmentReader can read.
     *
     * @return the name of the Aspect this AspectFragmentReader can read
     */
    public String getAspectName();

    /**
     * This is the main method of AspectFragmentReaders. It takes a JsonParser
     * from Jackson Faster XML library
     * http://fasterxml.github.io/jackson-core/javadoc
     * /2.0.0/com/fasterxml/jackson/core/JsonParser.html and returns a list of
     * AspectElements (a aspect fragment).
     *
     * @param jp the JsonParser
     * @return a List of AspectElements
     * @throws IOException
     */
    public List<AspectElement> readAspectFragment(final JsonParser jp) throws IOException;

    /**
     * This method takes one ObjectNode and parses it into a AspectElement.
     *
     * @param o an ObjectNode
     * @return an AspectElement
     * @throws IOException
     */
    public AspectElement readElement(final ObjectNode o) throws IOException;
}
