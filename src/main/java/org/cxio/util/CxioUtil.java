package org.cxio.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.cxio.aspects.readers.CartesianLayoutFragmentReader;
import org.cxio.aspects.readers.CyGroupsFragmentReader;
import org.cxio.aspects.readers.CyTableColumnFragmentReader;
import org.cxio.aspects.readers.CyViewsFragmentReader;
import org.cxio.aspects.readers.CyVisualPropertiesFragmentReader;
import org.cxio.aspects.readers.EdgeAttributesFragmentReader;
import org.cxio.aspects.readers.EdgesFragmentReader;
import org.cxio.aspects.readers.HiddenAttributesFragmentReader;
import org.cxio.aspects.readers.NetworkAttributesFragmentReader;
import org.cxio.aspects.readers.NetworkRelationsFragmentReader;
import org.cxio.aspects.readers.NodeAttributesFragmentReader;
import org.cxio.aspects.readers.NodesFragmentReader;
import org.cxio.aspects.readers.SubNetworkFragmentReader;
import org.cxio.aspects.writers.CartesianLayoutFragmentWriter;
import org.cxio.aspects.writers.CyGroupsFragmentWriter;
import org.cxio.aspects.writers.CyTableColumnFragmentWriter;
import org.cxio.aspects.writers.CyViewsFragmentWriter;
import org.cxio.aspects.writers.EdgeAttributesFragmentWriter;
import org.cxio.aspects.writers.EdgesFragmentWriter;
import org.cxio.aspects.writers.HiddenAttributesFragmentWriter;
import org.cxio.aspects.writers.NetworkAttributesFragmentWriter;
import org.cxio.aspects.writers.NetworkRelationsFragmentWriter;
import org.cxio.aspects.writers.NodeAttributesFragmentWriter;
import org.cxio.aspects.writers.NodesFragmentWriter;
import org.cxio.aspects.writers.SubNetworkFragmentWriter;
import org.cxio.aspects.writers.VisualPropertiesFragmentWriter;
import org.cxio.core.interfaces.AspectFragmentReader;
import org.cxio.core.interfaces.AspectFragmentWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class CxioUtil {

    public final static String LINE_SEPARATOR = System.getProperty("line.separator");
 //   public final static String MD5            = "MD5";

    final public static boolean isEmpty(final String s) {
        return (s == null) || (s.length() < 1);
    }

    /**
     * This returns the values of AttributesAspectElement e as String in the form '"value"' for single values and in
     * the form '["value1","value2",...]' for list values. Null value are return as 'null'.
     *
     * @param a AttributesAspectElement
     * @return value(s) as String
     * @throws JsonProcessingException 
     */
    public final static String getAttributeValuesAsString(final AbstractAttributesAspectElement e) throws JsonProcessingException {
        if (e == null) {
            throw new IllegalArgumentException("attempt to get values as string for null AbstractAttributesAspectElement");
        }
        if (e.isSingleValue()) {
            final String value = e.getValue();
            if (value  != null) {
                final ObjectMapper mapper = new ObjectMapper();
                final String str = mapper.writeValueAsString(value );
                return str;
            }
            return "null";
            
        }
        
        final List<String> values = e.getValues();
        return stringListToJson(values);
        
    }

    public static String stringListToJson(final List<String> values) throws JsonProcessingException {
        if (values != null) {
            final ObjectMapper mapper = new ObjectMapper();
            final String str = mapper.writeValueAsString(values);
            return str;
        }
        return "null";
        
    }

 /*   public final static String writeAspectElementsToString(final ArrayList<AspectElement> elements, final boolean use_default_pretty_printer) throws IOException {
        final OutputStream out = new ByteArrayOutputStream();

        final CxWriter w = CxWriter.createInstance(out, use_default_pretty_printer, getAllAvailableAspectFragmentWriters());

        w.start();
        w.writeAspectElements(elements);
        w.end(true, "");

        return out.toString();
    } */

 /*   public final static String writeAspectElementsToString(final String cx_string, final boolean use_default_pretty_printer) throws IOException {
        final CxReader p = CxReader.createInstance(cx_string, CxioUtil.getAllAvailableAspectFragmentReaders());
        final SortedMap<String, List<AspectElement>> res = CxReader.parseAsMap(p);

        final OutputStream out = new ByteArrayOutputStream();

        final CxWriter w = CxWriter.createInstance(out, use_default_pretty_printer, getAllAvailableAspectFragmentWriters());
        w.start();
        w.writeAspectElements(res.get(NodesElement.ASPECT_NAME));
        w.writeAspectElements(res.get(EdgesElement.ASPECT_NAME));
        w.writeAspectElements(res.get(NetworkRelationsElement.ASPECT_NAME));
        w.writeAspectElements(res.get(NetworkAttributesElement.ASPECT_NAME));
        w.writeAspectElements(res.get(NodeAttributesElement.ASPECT_NAME));
        w.writeAspectElements(res.get(EdgeAttributesElement.ASPECT_NAME));
        w.writeAspectElements(res.get(HiddenAttributesElement.ASPECT_NAME));
        w.writeAspectElements(res.get(CartesianLayoutElement.ASPECT_NAME));
        w.writeAspectElements(res.get(CyVisualPropertiesElement.ASPECT_NAME));
        w.writeAspectElements(res.get(CyGroupsElement.ASPECT_NAME));
        w.writeAspectElements(res.get(SubNetworkElement.ASPECT_NAME));
        w.writeAspectElements(res.get(CyViewsElement.ASPECT_NAME));
        w.end(true, "");

        return out.toString();
    } */

    public final static Set<AspectFragmentReader> getAllAvailableAspectFragmentReaders() {
        final AspectFragmentReader node_reader = NodesFragmentReader.createInstance();
        final AspectFragmentReader edge_reader = EdgesFragmentReader.createInstance();
        final AspectFragmentReader cartesian_layout_reader = CartesianLayoutFragmentReader.createInstance();
        final AspectFragmentReader network_attributes_reader = NetworkAttributesFragmentReader.createInstance();
        final AspectFragmentReader edge_attributes_reader = EdgeAttributesFragmentReader.createInstance();
        final AspectFragmentReader node_attributes_reader = NodeAttributesFragmentReader.createInstance();
        final AspectFragmentReader hidden_attributes_reader = HiddenAttributesFragmentReader.createInstance();
        final AspectFragmentReader visual_properties_reader = CyVisualPropertiesFragmentReader.createInstance();
        final AspectFragmentReader group_reader = CyGroupsFragmentReader.createInstance();
        final AspectFragmentReader subnetwork_reader = SubNetworkFragmentReader.createInstance();
        final AspectFragmentReader network_rel_reader = NetworkRelationsFragmentReader.createInstance();
        final AspectFragmentReader views_reader = CyViewsFragmentReader.createInstance();
        final AspectFragmentReader col_reader = CyTableColumnFragmentReader.createInstance();

        final Set<AspectFragmentReader> aspect_readers = new HashSet<>();

        aspect_readers.add(node_reader);
        aspect_readers.add(edge_reader);
        aspect_readers.add(cartesian_layout_reader);
        aspect_readers.add(network_attributes_reader);
        aspect_readers.add(edge_attributes_reader);
        aspect_readers.add(node_attributes_reader);
        aspect_readers.add(hidden_attributes_reader);
        aspect_readers.add(visual_properties_reader);
        aspect_readers.add(group_reader);
        aspect_readers.add(subnetwork_reader);
        aspect_readers.add(network_rel_reader);
        aspect_readers.add(views_reader);
        aspect_readers.add(col_reader);
        return aspect_readers;
    }

    public final static Set<AspectFragmentWriter> getAllAvailableAspectFragmentWriters() {
        final AspectFragmentWriter node_writer = NodesFragmentWriter.createInstance();
        final AspectFragmentWriter edge_writer = EdgesFragmentWriter.createInstance();
        final AspectFragmentWriter cartesian_layout_writer = CartesianLayoutFragmentWriter.createInstance();
        final AspectFragmentWriter network_attributes_writer = NetworkAttributesFragmentWriter.createInstance();
        final AspectFragmentWriter edge_attributes_writer = EdgeAttributesFragmentWriter.createInstance();
        final AspectFragmentWriter node_attributes_writer = NodeAttributesFragmentWriter.createInstance();
        final AspectFragmentWriter hidden_attributes_writer = HiddenAttributesFragmentWriter.createInstance();
        final AspectFragmentWriter visual_properties_writer = VisualPropertiesFragmentWriter.createInstance();
        final AspectFragmentWriter group_writer = CyGroupsFragmentWriter.createInstance();
        final AspectFragmentWriter subnetwork_writer = SubNetworkFragmentWriter.createInstance();
        final AspectFragmentWriter network_rel_writer = NetworkRelationsFragmentWriter.createInstance();
        final AspectFragmentWriter views_writer = CyViewsFragmentWriter.createInstance();
        final AspectFragmentWriter col_writer = CyTableColumnFragmentWriter.createInstance();

        final Set<AspectFragmentWriter> aspect_writers = new HashSet<>();
        aspect_writers.add(node_writer);
        aspect_writers.add(edge_writer);
        aspect_writers.add(network_rel_writer);
        aspect_writers.add(network_attributes_writer);
        aspect_writers.add(node_attributes_writer);
        aspect_writers.add(edge_attributes_writer);
        aspect_writers.add(hidden_attributes_writer);
        aspect_writers.add(cartesian_layout_writer);
        aspect_writers.add(visual_properties_writer);
        aspect_writers.add(group_writer);
        aspect_writers.add(subnetwork_writer);
        aspect_writers.add(views_writer);
        aspect_writers.add(col_writer);
        return aspect_writers;
    }
/*
    public static boolean validate(final byte[] writer_checksum, final byte[] reader_checksum, final AspectElementCounts writer_counts, final AspectElementCounts reader_counts) {
        if (!AspectElementCounts.isCountsAreEqual(reader_counts, writer_counts)) {
            System.out.println("something went wrong: element counts do not match");
        }
        else if (!isAreByteArraysEqual(reader_checksum, writer_checksum)) {
            System.out.println("something went wrong: checksums do not match");
        }
        else {
            return true;
        }
        return false;
    }

    private final static boolean isAreByteArraysEqual(final byte[] a0, final byte[] a1) {
        if (a0.length != a1.length) {
            return false;
        }
        for (int i = 0; i < a1.length; ++i) {
            if (a0[i] != a1[i]) {
                return false;
            }
        }
        return true;
    }
*/
}
