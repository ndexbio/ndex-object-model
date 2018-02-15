package org.cxio.core;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.cxio.core.interfaces.AspectElement;
import org.cxio.core.interfaces.AspectFragmentReader;
import org.cxio.metadata.MetaDataCollection;
import org.cxio.misc.AspectElementCounts;
import org.cxio.misc.NumberVerification;
import org.cxio.misc.OpaqueFragmentReader;
import org.cxio.misc.Status;
import org.cxio.util.CxioUtil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is used to read aspect fragments (lists of aspect elements) from a
 * input stream.
 *
 *
 * @author cmzmasek
 *
 */
public final class CxReader extends AbstractCxReader {

    private static final boolean                        DEBUG = false;
    private List<AspectElement>                         _current;
    private final HashMap<String, AspectFragmentReader> _element_readers;
    private final Object                                _input;
    private JsonParser                                  _jp;
    private int                                         _level;
    private final boolean                               _read_anonymous_aspect_fragments;
    private JsonToken                                   _token;
    private boolean                                     _was_in_recognized_aspect;
    private boolean                                     _saw_number_check;

    /**
     * This creates a new CxtReader with all AspectFragmentReaders implemented in this library already added.
     *
     * @param input the input object to parse
     * @param read_anonymous_aspect_fragments to enable reading of anonymous aspect fragments
     * @return a CxReader
     * @throws IOException
     */
    public final static CxReader createInstanceWithAllAvailableReaders(final Object input, final boolean read_anonymous_aspect_fragments) throws IOException {
        try {
            return new CxReader(input, CxioUtil.getAllAvailableAspectFragmentReaders(), read_anonymous_aspect_fragments, false);
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * This creates a new CxtReader with all AspectFragmentReaders implemented in this library already added.
     *
     * @param input the input object to parse
     * @param read_anonymous_aspect_fragments to enable reading of anonymous aspect fragments
     * @param calculate_md5_checksum to turn checksum calculation on/off
     * @return a CxReader
     * @throws IOException
     */
    public final static CxReader createInstanceWithAllAvailableReaders(final Object input, final boolean read_anonymous_aspect_fragments, final boolean calculate_md5_checksum) throws IOException {
        try {
            return new CxReader(input, CxioUtil.getAllAvailableAspectFragmentReaders(), read_anonymous_aspect_fragments, calculate_md5_checksum);
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     *  This creates a new CxtReader with all AspectFragmentReaders implemented in this library already added.
     *  It allows to add additional AspectFragmentReader.
     *
     * @param input the input object to parse
     * @param read_anonymous_aspect_fragments to enable reading of anonymous aspect fragments
     * @param calculate_md5_checksum to turn checksum calculation on/off
     * @param fragment_readers the set of additional AspectFragmentReaders to use
     * @return a CxReader
     * @throws IOException
     */
    public final static CxReader createInstanceWithAllAvailableReaders(final Object input,
                                                                       final boolean read_anonymous_aspect_fragments,
                                                                       final boolean calculate_md5_checksum,
                                                                       final Set<AspectFragmentReader> fragment_readers) throws IOException {

        final Set<AspectFragmentReader> r = CxioUtil.getAllAvailableAspectFragmentReaders();
        r.addAll(fragment_readers);
        try {
            return new CxReader(input, r, read_anonymous_aspect_fragments, calculate_md5_checksum);
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * This creates a new CxReader.
     *
     * @param input the input object to parse
     * @param read_anonymous_aspect_fragments to enable reading of anonymous aspect fragments
     * @param calculate_md5_checksum to turn checksum calculation on/off
     * @param fragment_readers the set of AspectFragmentReaders to use
     * @return a CxReader
     * @throws IOException
     */
    public final static CxReader createInstance(final Object input,
                                                final boolean read_anonymous_aspect_fragments,
                                                final boolean calculate_md5_checksum,
                                                final Set<AspectFragmentReader> fragment_readers) throws IOException {
        try {
            return new CxReader(input, fragment_readers, read_anonymous_aspect_fragments, calculate_md5_checksum);
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * This creates a new CxReader.
     *
     * @param input the input object to parse
     * @param fragment_readers the set of AspectFragmentReaders to use
     * @return a CxReader
     * @throws IOException
     */
    public final static CxReader createInstance(final Object input, final Set<AspectFragmentReader> fragment_readers) throws IOException {
        try {
            return new CxReader(input, fragment_readers, false, false);
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Convenience method. Returns a sorted map of lists of aspects, where the
     * keys are the names of the aspect. Takes a CxReader as argument.
     *
     */
    public static SortedMap<String, List<AspectElement>> parseAsMap(final CxReader cxr) throws IOException {
        if (cxr == null) {
            throw new IllegalArgumentException("reader is null");
        }
        final SortedMap<String, List<AspectElement>> all_aspects = new TreeMap<>();
        while (cxr.hasNext()) {
            final List<AspectElement> aspects = cxr.getNext();
            if ((aspects != null) && !aspects.isEmpty()) {
                final String name = aspects.get(0).getAspectName();
                if (!all_aspects.containsKey(name)) {
                    all_aspects.put(name, aspects);
                }
                else {
                    all_aspects.get(name).addAll(aspects);
                }
            }
        }
        return all_aspects;
    }

    /**
     * This returns a list of aspect elements and advances to the reader to the
     * next list of aspect elements.
     *
     * @return
     * @throws IOException
     */
    public final List<AspectElement> getNext() throws IOException {
        if (_token == null) {
            throw new IllegalStateException("this should never have happened: token is null");
        }
        if (_jp == null) {
            throw new IllegalStateException("this should never have happened: json parser is null");
        }
        final List<AspectElement> prev = _current;
        _current = null;
        while ((_token != JsonToken.END_ARRAY) || (_jp.getCurrentName() != null)) {
            List<AspectElement> elements = null;
            final String name = _jp.getCurrentName();
            if (DEBUG) {
                System.out.println(">>> ASPECT_NAME: " + name);
            }
            _was_in_recognized_aspect = false;
            if ((_level == 2) && (_token == JsonToken.FIELD_NAME) && (name != null)) {
                if (_element_readers.containsKey(name)) {
                    elements = _element_readers.get(name).readAspectFragment(_jp);
                    _was_in_recognized_aspect = true;
                    _encountered_non_meta_content = true;
                    if (!_saw_number_check) {
                        throw new IOException(NumberVerification.NAME + " element is missing, aborting");
                    }
                }
                else if (name.equals(MetaDataCollection.NAME)) {
                    --_level;
                    if (_level < 1) {
                        throw new IllegalStateException("this should never have happened (likely cause: problem with '" + name + "' reader)");
                    }
                    addMetaData(_jp);
                }
                else if (name.equals(NumberVerification.NAME)) {
                    --_level;
                    if (_level < 1) {
                        throw new IllegalStateException("this should never have happened (likely cause: problem with '" + name + "' reader)");
                    }
                    performNumberVerification(_jp);
                    _saw_number_check = true;
                }
                else if (name.equals(Status.NAME)) {
                    --_level;
                    if (_level < 1) {
                        throw new IllegalStateException("this should never have happened (likely cause: problem with '" + name + "' reader)");
                    }
                    addStatus(_jp);
                }
                else if (_read_anonymous_aspect_fragments) {
                    if (!_saw_number_check) {
                        throw new IOException(NumberVerification.NAME + " element is missing, aborting");
                    }
                    final OpaqueFragmentReader reader = OpaqueFragmentReader.createInstance();
                    reader.setAspectName(name);
                    elements = reader.readAspectFragment(_jp);
                    if (!reader.isList()) {
                        --_level;
                        if (_level < 1) {
                            throw new IllegalStateException("this should never have happened (likely cause: problem with '" + name + "' reader)");
                        }
                    }
                    _was_in_recognized_aspect = true;
                    _encountered_non_meta_content = true;
                }
            }
            if (_was_in_recognized_aspect && (_jp.getCurrentToken() != JsonToken.END_ARRAY) && (_jp.getCurrentToken() != JsonToken.END_OBJECT)) {
                throw new IllegalStateException("this should never have happened (likely cause: problem with '" + name + "' reader)");
            }
            if ((_token == JsonToken.START_ARRAY) || (_token == JsonToken.START_OBJECT)) {
                ++_level;
            }
            else if ((_token == JsonToken.END_ARRAY) || (_token == JsonToken.END_OBJECT)) {
                --_level;
                if (_level < 1) {
                    throw new IllegalStateException("this should never have happened (likely cause: problem with '" + name + "' reader)");
                }
            }
            _token = _jp.nextToken();
            if (elements != null) {
                _current = elements;
                if (_calculate_element_counts) {
                    if ((prev != null) && !prev.isEmpty()) {
                        _element_counts.processAspectElements(prev);
                    }
                }
                return prev;
            }
        }
        _jp.close();
        if (_calculate_element_counts) {
            if ((prev != null) && !prev.isEmpty()) {
                _element_counts.processAspectElements(prev);
            }
        }
        return prev;
    }

    /**
     * Returns true if more lists of aspect elements can be read in.
     *
     * @return
     * @throws IOException
     */
    public final boolean hasNext() throws IOException {
        return _current != null;
    }

    /**
     * This attempts to reset the iterator.
     * <br>
     * ONLY works when the input is based on a String.
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public final void reset() throws IOException, NoSuchAlgorithmException {
        if (_input == null) {
            throw new IllegalStateException("input for cx parser is null");
        }
        if (!_read_anonymous_aspect_fragments) {
            if ((_element_readers == null) || _element_readers.isEmpty()) {
                throw new IllegalStateException("aspect handlers are null or empty");
            }
        }
        _token = null;
        _was_in_recognized_aspect = false;
        _level = 0;
        _current = null;
        _jp = createJsonParser(_input);
        _token = _jp.nextToken();
        _encountered_non_meta_content = false;
        _pre_meta_data = null;
        _post_meta_data = null;
        _saw_number_check = false;
        if (_token != JsonToken.START_ARRAY) {
            throw new IllegalStateException("illegal cx json format: expected to start with an array: " + _token.asString());
        }
        getNext();
    }

    /**
     * Hidden constructor.
     *
     * @param input
     * @param aspect_readers
     * @param read_anonymous_aspect_fragments
     * @param calculate_md5_checksum
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private CxReader(final Object input, final Set<AspectFragmentReader> aspect_readers, final boolean read_anonymous_aspect_fragments, final boolean calculate_md5_checksum) throws IOException,
    NoSuchAlgorithmException {
        if (input == null) {
            throw new IllegalArgumentException("cx input is null");
        }
        checkInputType(input);
        _input = input;
        _element_readers = setupAspectReaders(aspect_readers, read_anonymous_aspect_fragments);
        _read_anonymous_aspect_fragments = read_anonymous_aspect_fragments;
        _calculate_element_counts = true;
        _calculate_md5_checksum = calculate_md5_checksum;
        _element_counts = AspectElementCounts.createInstance();
        _encountered_non_meta_content = false;
        _pre_meta_data = null;
        _post_meta_data = null;
        reset();
    }

}
