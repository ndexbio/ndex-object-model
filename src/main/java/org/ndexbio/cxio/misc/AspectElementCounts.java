package org.ndexbio.cxio.misc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.ndexbio.cxio.core.interfaces.AspectElement;
import org.ndexbio.cxio.util.CxioUtil;

public final class AspectElementCounts {

    private final SortedMap<String, Integer> _count_data;

    public final static AspectElementCounts createInstance() {
        return new AspectElementCounts();
    }

    public final Set<String> getAllAspectNames() {
        return _count_data.keySet();
    }

    public final int getAspectElementCount(final AspectElement element) {
        return getAspectElementCount(element.getAspectName());
    }

    public final int getAspectElementCount(final String aspect_name) {
        if (_count_data.containsKey(aspect_name)) {
            return _count_data.get(aspect_name).intValue();
        }
        return 0;
    }

    public final static boolean isCountsAreEqual(final AspectElementCounts c0, final AspectElementCounts c1) {
        if (c0.getAllAspectNames().size() != c1.getAllAspectNames().size()) {
            return false;
        }
        if (!c0.getAllAspectNames().containsAll(c1.getAllAspectNames())) {
            return false;
        }
        for (final String n : c0.getAllAspectNames()) {
            if (c0.getAspectElementCount(n) != c1.getAspectElementCount(n)) {
                return false;
            }
        }

        return true;
    }

    public final void setAspectElementCount(final AspectElement element, final int count) {
        increaseAspectElementCount(element.getAspectName(), count);
    }

    public final void setAspectElementCount(final String aspect_name, final int count) {
        _count_data.put(aspect_name, Integer.valueOf(count));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, Integer> e : _count_data.entrySet()) {
            sb.append(e.getKey());
            sb.append(": ");
            sb.append(e.getValue());
            sb.append(CxioUtil.LINE_SEPARATOR);
        }
        return sb.toString();
    }

    final public void processAspectElement(final AspectElement element) {
        increaseAspectElementCount(element.getAspectName());
    }

    public final void processAspectElement(final String element_name) {
        increaseAspectElementCount(element_name);
    }

    public final void processAspectElement(final String element_name, final int count) {
        increaseAspectElementCount(element_name, count);
    }

    public final void processAspectElements(final List<AspectElement> elements) {
        increaseAspectElementCount(elements.get(0).getAspectName(), elements.size());
    }

    final void processAnonymousAspectElements(final List<OpaqueElement> elements) {
        increaseAspectElementCount(elements.get(0).getAspectName(), elements.size());
    }

    private final void increaseAspectElementCount(final String aspect_name) {
        if (_count_data.containsKey(aspect_name)) {
            _count_data.put(aspect_name, _count_data.get(aspect_name) + 1);
        }
        else {
            _count_data.put(aspect_name, Integer.valueOf(1));
        }
    }

    private final void increaseAspectElementCount(final String aspect_name, final int count) {
        if (_count_data.containsKey(aspect_name)) {
            _count_data.put(aspect_name, _count_data.get(aspect_name) + count);
        }
        else {
            _count_data.put(aspect_name, Integer.valueOf(count));
        }
    }

    private AspectElementCounts() {
        _count_data = new TreeMap<>();
    }

}
