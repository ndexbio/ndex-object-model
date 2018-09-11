package org.cxio.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.cxio.core.interfaces.AspectElement;
import org.cxio.core.interfaces.AspectFragmentWriter;
import org.cxio.metadata.MetaDataCollection;
import org.cxio.misc.AspectElementCounts;
import org.cxio.misc.OpaqueElement;
import org.cxio.misc.Status;
import org.cxio.util.CxioUtil;
import org.cxio.util.JsonWriter;

/**
 * This class is for writing aspect fragments (lists of aspects).
 *
 * @author cmzmasek
 *
 */
public final class CxWriter {

    private final JsonWriter                        _jw;
 //   private final MessageDigest                     _md;
    private boolean                                 _started;
    private boolean                                 _ended;
    private boolean                                 _fragment_started;
    private String                                  _current_fragment_name;
    private final Map<String, AspectFragmentWriter> _writers;
    private final AspectElementCounts               _element_counts;
    private boolean                                 _calculate_element_counts;
    private MetaDataCollection                      _pre_meta_data;
    private MetaDataCollection                      _post_meta_data;
    private boolean                                 _in_fragment;


    /**
     * Returns a CxWriter for reading from OutputStream out.
     * <br>
     * Subsequent calls to method {@link #addAspectFragmentWriter(AspectFragmentWriter writer)} are
     * required to add {@link org.cxio.core.interfaces.AspectFragmentWriter} to the newly created CxWriter.
     *
     * @param out the OutputStream to read
     * @param use_default_pretty_printer to turn pretty printing on/off
     * @return a CxWriter writer
     * @throws IOException
     */
    public final static CxWriter createInstance(final OutputStream out, final boolean use_default_pretty_printer) throws IOException {
        return new CxWriter(out, use_default_pretty_printer);
    }



    /**
     * This method is for adding a {@link org.cxio.core.interfaces.AspectFragmentWriter} to this CxWriter.
     *
     * @param writer the AspectFragmentWrite to add
     */
    public void addAspectFragmentWriter(final AspectFragmentWriter writer) {
        if (writer == null) {
            throw new IllegalArgumentException("aspect fragment writer is null");
        }
        if (CxioUtil.isEmpty(writer.getAspectName())) {
            throw new IllegalArgumentException("aspect name is null or empty");
        }
        _writers.put(writer.getAspectName(), writer);
    }

    /**
     * This method is to be called prior to writing individual aspect elements of a given type/name.
     * <br>
     *
     * @param aspect_name the name of the aspect elements to be written
     * @throws IOException
     */
    public void startAspectFragment(final String aspect_name) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (_fragment_started) {
            throw new IllegalStateException("fragment already started");
        }
        if (CxioUtil.isEmpty(aspect_name)) {
            throw new IllegalStateException("aspect fragment name must not be empty or null");
        }
        _fragment_started = true;
        _current_fragment_name = aspect_name;
        _jw.startArray(aspect_name);
    }

    private final void checkIfEnded() {
        if (_ended) {
            throw new IllegalStateException("already ended");
        }
    }

    /**
     * This method is to be called after writing individual aspect elements of a given type/name.
     *
     * @throws IOException
     */
    public void endAspectFragment() throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (!_fragment_started) {
            throw new IllegalStateException("fragment not started");
        }
        _fragment_started = false;
        _in_fragment = false;
        _current_fragment_name = null;
        _jw.endArray();
    }

    /**
     * This method is to be called at the end of writing to a stream.
     *
     * @throws IOException
     */
    public void end(final boolean success, final String message) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if ( !success && ( _fragment_started || _in_fragment )) {
            _jw.endArray();
        }
        _ended = true;
        _started = false;
        _current_fragment_name = null;
        
        writeMetaData(_post_meta_data);
        final Status status = new Status(success, message);
        status.toJson(_jw);
        
        _jw.end();
    }

    private final void checkIfNotStarted() {
        if (!_started) {
            throw new IllegalStateException("not started");
        }
    }

    /**
     * This method is to be called at the beginning of writing to a stream.
     *
     *
     * @param long_number a Long for verification purposes
     * @throws IOException
     */
    public void start() throws IOException {
        checkIfEnded();
        if (_started) {
            throw new IllegalStateException("already started");
        }
        _started = true;
        _ended = false;
        _jw.start();

      //  final NumberVerification nv = new NumberVerification(CxConstants.LONG_NUMBER_TEST);
      //  nv.toJson(_jw);

        writeMetaData(_pre_meta_data);
    }

    /**
     * This is for writing a list of AspectElements (a aspect fragment) of a given type.
     * <br>
     * A appropriate {@link org.cxio.core.interfaces.AspectFragmentWriter} will be automatically
     * selected (if added previously).
     *
     *
     * @param elements the list of AspectElements to be written
     * @throws IOException
     */
    public void writeAspectElements(final List<AspectElement> elements) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        
        if (_fragment_started) {
            throw new IllegalStateException("in individual elements writing state");
        }
        if ((elements == null) || elements.isEmpty()) {
            return;
        }
        if (_writers.containsKey(elements.get(0).getAspectName())) {
            final AspectFragmentWriter writer = _writers.get(elements.get(0).getAspectName());
            _in_fragment = true;
            writer.write(elements, _jw);
            _in_fragment = false;
            if (_calculate_element_counts) {
                _element_counts.processAspectElements(elements);
            }
        }
    }

    /**
     * This is for writing a list of AspectElements (a aspect fragment) of a given type.
     *
     * @param elements the list of AspectElements to be written
     * @param writer a appropriate {@link org.cxio.core.interfaces.AspectFragmentWriter}
     * @throws IOException
     */
    public void writeAspectElements(final List<AspectElement> elements, final AspectFragmentWriter writer) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (_fragment_started) {
            throw new IllegalStateException("in individual elements writing state");
        }
        if ((elements == null) || elements.isEmpty()) {
            return;
        }
        _in_fragment = true;
        writer.write(elements, _jw);
        _in_fragment = false;
        if (_calculate_element_counts) {
            _element_counts.processAspectElements(elements);
        }
    }

    /**
     * This is for writing a single AspectElement.
     * A appropriate {@link org.cxio.core.interfaces.AspectFragmentWriter} will be automatically
     * selected (if added previously).
     * <br>
     * Prior to calling this method for a AspectElement of a given type/name,
     * {@link #startAspectFragment(String aspect_name)} needs to be called.
     * <br>
     * After all AspectElements of a given type/name a written, {@link #endAspectFragment()} needs to be called.
     *
     * @param element the AspectElements to be written
     * @throws IOException
     */
    public void writeAspectElement(final AspectElement element) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (!_fragment_started) {
            throw new IllegalStateException("fragment not started");
        }
        if (element == null) {
            return;
        }
        if (_writers.containsKey(element.getAspectName())) {
            final AspectFragmentWriter writer = _writers.get(element.getAspectName());
            writer.writeElement(element, _jw);
            if (_calculate_element_counts) {
                _element_counts.processAspectElement(element);
            }
        }
    }

    /**
     * This is for writing a single AspectElement.
     * <br>
     * Prior to calling this method for a AspectElement of a given type/name,
     * {@link #startAspectFragment(String aspect_name)} needs to be called.
     * <br>
     * After all AspectElements of a given type/name a written, {@link #endAspectFragment()} needs to be called.
     *
     * @param element the AspectElements to be written
     * @param writer a appropriate {@link org.cxio.core.interfaces.AspectFragmentWriter}
     * @throws IOException
     */
    public void writeAspectElement(final AspectElement element, final AspectFragmentWriter writer) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (!_fragment_started) {
            throw new IllegalStateException("fragment not started");
        }

        if (element == null) {
            return;
        }
        writer.writeElement(element, _jw);
        if (_calculate_element_counts) {
            _element_counts.processAspectElement(element);
        }
    }

    /**
     * This returns an object which gives access to the element counts
     * for the aspect element written out.
     *
     * @return the ElementCounts
     */
    public final AspectElementCounts getAspectElementCounts() {
        return _element_counts;
    }

    /**
     * To turn on/off the calculation of checksum and aspect element counts.
     *
     * @param calculate_element_counts
     */
    public final void setCalculateAspectElementCounts(final boolean calculate_element_counts) {
        _calculate_element_counts = calculate_element_counts;
    }

/*    public final byte[] getMd5Checksum() {
        if (_md == null) {
            throw new IllegalStateException("cx writer is not set up to calculare checksum");
        }
        return _md.digest();
    } */

    public final void addPreMetaData(final MetaDataCollection pre_meta_data) {
        checkIfEnded();
        if (_started) {
            throw new IllegalStateException("illegal attempt to add pre meta-data: already started");
        }
        _pre_meta_data = pre_meta_data;
    }

    public final void addPostMetaData(final MetaDataCollection post_meta_data) {
        checkIfEnded();
        _post_meta_data = post_meta_data;
    }

    private final void writeMetaData(final MetaDataCollection md) throws IOException {
        if ((md != null) && !md.isEmpty()) {
            md.toJson(_jw);
        }
    }

    public final void writeOpaqueAspectFragment(final String name, final OpaqueElement opque_element) throws IOException {
        writeOpaqueAspectFragment(name, opque_element.toJsonString());
    }

    public final void writeOpaqueAspectFragment(final String name, final String json_string) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (_fragment_started) {
            throw new IllegalStateException("in individual elements writing state");
        }
        if (CxioUtil.isEmpty(json_string)) {
            return;
        }
        _jw.writeJsonNodeAsList(name, json_string);
        if (_calculate_element_counts) {
            _element_counts.processAspectElement(name);
        }
    }

    public final void writeOpaqueAspectElement(final OpaqueElement opque_element) throws IOException {
        writeOpaqueAspectElement(opque_element.toJsonString());
    }

    public final void writeOpaqueAspectElement(final String json_string) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (!_fragment_started) {
            throw new IllegalStateException("fragment not started");
        }
        if (CxioUtil.isEmpty(json_string)) {
            return;
        }
        _jw.writeAnonymousAspectElement(json_string);
        if (_calculate_element_counts) {
            _element_counts.processAspectElement(_current_fragment_name);
        }
    }

    public final void writeOpaqueAspectFragment2(final String name, final Collection<OpaqueElement> opque_elements) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (_fragment_started) {
            throw new IllegalStateException("in individual elements writing state");
        }
        if ((opque_elements == null) || opque_elements.isEmpty()) {
            return;
        }
        startAspectFragment(name);
        for (final OpaqueElement opaque_element : opque_elements) {
        	  _jw.writeJsonObject(opaque_element.getData());
         //   writeOpaqueAspectElement(opaque_element);
        }
        endAspectFragment();
    }

    public final void writeOpaqueAspectFragment(final String name, final Collection<String> json_strings) throws IOException {
        checkIfEnded();
        checkIfNotStarted();
        if (_fragment_started) {
            throw new IllegalStateException("in individual elements writing state");
        }
        if ((json_strings == null) || json_strings.isEmpty()) {
            return;
        }
        _jw.writeAnonymousAspectElements(name, json_strings);
        if (_calculate_element_counts) {
            _element_counts.processAspectElement(name, json_strings.size());
        }

    }

    /**
     * Convenience method to write a map (aspect name to lists of aspect elements) to
     * a output stream.
     *
     *
     * @param aspect_elements
     * @param cx_writer
     * @param output_stream
     * @throws IOException
     */
    public final static void writeFromMap(final SortedMap<String, List<AspectElement>> aspect_elements, final CxWriter cx_writer, final OutputStream output_stream) throws IOException {
        final boolean success = true;
        String msg = "";
        cx_writer.start();
        try {
            for (final String k : aspect_elements.keySet()) {
                final List<AspectElement> x = aspect_elements.get(k);
                cx_writer.writeAspectElements(x);
            }
        }
        catch (final Exception e) {
            msg = e.getMessage();
        }
        cx_writer.end(success, msg);
    }

 

    private CxWriter(final OutputStream os, final boolean use_default_pretty_printer) throws IOException {
        if (os == null) {
            throw new IllegalArgumentException("attempt to use null outputstream");
        }
        _writers = new HashMap<>();
  //      _md = null;
        _jw = JsonWriter.createInstance(os, use_default_pretty_printer);
        _started = false;
        _ended = false;
        _fragment_started = false;
        _calculate_element_counts = true;
        _element_counts = AspectElementCounts.createInstance();
        _pre_meta_data = null;
        _post_meta_data = null;
    }
}
