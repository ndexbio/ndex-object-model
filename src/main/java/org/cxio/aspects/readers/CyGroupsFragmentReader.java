package org.cxio.aspects.readers;

import java.io.IOException;

import org.cxio.aspects.datamodels.CyGroupsElement;
import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class CyGroupsFragmentReader extends AbstractFragmentReader {

    public static CyGroupsFragmentReader createInstance() {
        return new CyGroupsFragmentReader();
    }

    private CyGroupsFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return CyGroupsElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        final String name = ParserUtils.getTextValueRequired(o, CyGroupsElement.GROUP_NAME);
        final Long group_id = ParserUtils.getTextValueRequiredAsLong(o, CyGroupsElement.GROUP_ID);
        final Long subnet = ParserUtils.getTextValueRequiredAsLong(o, CyGroupsElement.SUBNET);

        final CyGroupsElement e = new CyGroupsElement(group_id, subnet, name);
        Object isCollapsed =  o.get(CyGroupsElement.IS_COLLAPSED);
        if ( isCollapsed != null)
        	e.set_isCollapsed(((Boolean)isCollapsed).booleanValue());
        if (o.has(CyGroupsElement.NODES)) {
  /*          if (!o.get(CyGroupsElement.NODES).isArray() && o.get(CyGroupsElement.NODES).asText().equalsIgnoreCase("all")) {
                e.setNodesAll(true);
            }
            else { */
                e.getNodes().addAll(ParserUtils.getAsLongList(o, CyGroupsElement.NODES));
    //        }
        }
        if (o.has(CyGroupsElement.INTERNAL_EDGES)) {
        /*    if (!o.get(CyGroupsElement.INTERNAL_EDGES).isArray() && o.get(CyGroupsElement.INTERNAL_EDGES).asText().equalsIgnoreCase("all")) {
                e.setExternalEdgesAll(true);
            }
            else { */
                e.getInternalEdges().addAll(ParserUtils.getAsLongList(o, CyGroupsElement.INTERNAL_EDGES));
        //    }
        }
        if (o.has(CyGroupsElement.EXTERNAL_EDGES)) {
        /*    if (!o.get(CyGroupsElement.EXTERNAL_EDGES).isArray() && o.get(CyGroupsElement.EXTERNAL_EDGES).asText().equalsIgnoreCase("all")) {
                e.setInternalEdgesAll(true);
            }
            else {  */
                e.getExternalEdges().addAll(ParserUtils.getAsLongList(o, CyGroupsElement.EXTERNAL_EDGES));
       //     }
        }
        return e;
    }

}
