package org.ndexbio.cxio.core.interfaces;

import java.io.IOException;
import java.util.List;

import org.ndexbio.cxio.util.JsonWriter;

public interface AspectFragmentWriter extends Comparable<AspectFragmentReader> {

    public String getAspectName();

//    public void addAspectKeyFilter(final AspectKeyFilter filter);

    public void write(final List<AspectElement> aspects, final JsonWriter json_writer) throws IOException;

    public void writeElement(final AspectElement element, final JsonWriter json_writer) throws IOException;

}
