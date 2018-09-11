package org.ndexbio.cxio.aspects.writers;

import java.io.IOException;
import java.util.List;

import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.core.interfaces.AspectFragmentReader;
import org.ndexbio.cxio.core.interfaces.AspectFragmentWriter;
import org.ndexbio.cxio.util.JsonWriter;

/**
 * This is a convenience class for classes implementing the AspectFragmentWriter
 * interface.
 *
 *
 */
public abstract class AbstractFragmentWriter implements AspectFragmentWriter {

  /*  @Override
    public void addAspectKeyFilter(final AspectKeyFilter filter) {
        throw new UnsupportedOperationException("this writer does not implement aspect key filtering");
    } */

    @Override
    abstract public String getAspectName();

    @Override
    public void write(final List<AspectElement> aspect_elements, final JsonWriter w) throws IOException {
        if (aspect_elements == null) {
            return;
        }
        w.startArray(getAspectName());
        for (final AspectElement aspect_element : aspect_elements) {
            writeElement(aspect_element, w);
        }
        w.endArray();
    }

    @Override
    public void writeElement(final AspectElement element, final JsonWriter w) throws IOException {
        w.writeObject(element);
    }

    @Override
    public boolean equals(final Object o) {
        if ((o != null) && (o instanceof AbstractFragmentWriter)) {
            return getAspectName().equals(((AbstractFragmentWriter) o).getAspectName());
        }
        return false;
    }

    @Override
    public int compareTo(final AspectFragmentReader o) {
        return getAspectName().compareTo(o.getAspectName());
    }

	@Override
	public int hashCode() {
		return getAspectName().hashCode();
	}

}
