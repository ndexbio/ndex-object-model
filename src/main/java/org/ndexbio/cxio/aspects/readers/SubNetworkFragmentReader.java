package org.ndexbio.cxio.aspects.readers;

import java.io.IOException;

import org.ndexbio.cxio.aspects.datamodels.SubNetworkElement;
import org.ndexbio.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class SubNetworkFragmentReader extends AbstractFragmentReader {

    public static SubNetworkFragmentReader createInstance() {
        return new SubNetworkFragmentReader();
    }

    private SubNetworkFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return SubNetworkElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        final SubNetworkElement e = new SubNetworkElement(ParserUtils.getTextValueRequiredAsLong(o, SubNetworkElement.SUBNET_ID));
        if (o.has(SubNetworkElement.SUBNET_NODES)) {
            if (!o.get(SubNetworkElement.SUBNET_NODES).isArray() && o.get(SubNetworkElement.SUBNET_NODES).asText().equalsIgnoreCase("all")) {
                e.setNodesAll(true);
            }
            else {
                e.getNodes().addAll(ParserUtils.getAsLongList(o, SubNetworkElement.SUBNET_NODES));
            }
        }
        if (o.has(SubNetworkElement.SUBNET_EDGES)) {
            if (!o.get(SubNetworkElement.SUBNET_EDGES).isArray() && o.get(SubNetworkElement.SUBNET_EDGES).asText().equalsIgnoreCase("all")) {
                e.setEdgesAll(true);
            }
            else {
                e.getEdges().addAll(ParserUtils.getAsLongList(o, SubNetworkElement.SUBNET_EDGES));
            }
        }
        return e;
    }

}
