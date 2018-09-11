package org.ndexbio.cxio.aspects.datamodels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ndexbio.cxio.util.JsonWriter;

/**
 * This class is used to represent a group of nodes in network.
 *
 *
 * @author cmzmasek
 *
 */
public final class CyGroupsElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String EXTERNAL_EDGES = "external_edges";
    public final static String GROUP_ID       = "@id";
    public final static String GROUP_NAME     = "n";
    public final static String INTERNAL_EDGES = "internal_edges";
    public final static String ASPECT_NAME    = "cyGroups";
    public final static String NODES          = "nodes";
    public final static String SUBNET           = "s";
    public final static String IS_COLLAPSED  = "collapsed";

    private final List<Long>   _external_edges;
    private final Long         _group_id;
    private final List<Long>   _internal_edges;
    private final String       _name;
    private final List<Long>   _nodes;
    private Long         		_subNet;
    private boolean 		  _isCollapsed;
/*    private boolean            _internal_edges_all;
    private boolean            _external_edges_all;
    private boolean            _nodes_all; */

    public CyGroupsElement(final Long group_id, final Long subNetId, final String name) {
        _name = name;
        _subNet = subNetId;
        _group_id = group_id;
        _nodes = new ArrayList<>();
        _internal_edges = new ArrayList<>();
        _external_edges = new ArrayList<>();
        _isCollapsed = false;
    }

 /*   public void setNodesAll(final boolean nodes_all) {
        _nodes_all = nodes_all;
        if (nodes_all) {
            _nodes.clear();
        }
    }

    public boolean isNodesAll() {
        return _nodes_all;
    }

    public boolean isInternalEdgesAll() {
        return _external_edges_all;
    }

    public boolean isExternalEdgesAll() {
        return _internal_edges_all;
    }

    public void setInternalEdgesAll(final boolean internal_edges_all) {
        _internal_edges_all = internal_edges_all;
        if (internal_edges_all) {
            _internal_edges.clear();
        }
    }

    public void setExternalEdgesAll(final boolean external_edges_all) {
        _external_edges_all = external_edges_all;
        if (external_edges_all) {
            _external_edges.clear();
        }
    }
*/
    final public void addExternalEdge(final Long edge_id) {
  //      _external_edges_all = false;
        _external_edges.add(edge_id);
    }

    final public void addInternalEdge(final Long edge_id) {
   //     _internal_edges_all = false;
        _internal_edges.add(edge_id);
    }

    final public void addNode(final Long node_id) {
  //      _nodes_all = false;
        _nodes.add(node_id);
    }

    @Override
    public final String getAspectName() {
        return ASPECT_NAME;
    }

    final public List<Long> getExternalEdges() {
        return _external_edges;
    }

    final public Long getGroupId() {
        return _group_id;
    }

    final public List<Long> getInternalEdges() {
        return _internal_edges;
    }

    final public String getName() {
        return _name;
    }

    final public List<Long> getNodes() {
        return _nodes;
    }

    final public Long getSubNet() {
        return _subNet;
    }
    
    public void setSubNet(Long subnetId) { _subNet = subnetId; } 

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(ASPECT_NAME);
        sb.append(": ");
        sb.append("name: ");
        sb.append(_name);
        sb.append("\n");
        sb.append("group id: ");
        sb.append(_group_id);
        sb.append("\n");
        if (_subNet != null) {
            sb.append("subNet: ");
            sb.append(_subNet);
            sb.append("\n");
        }
        sb.append("nodes:");
  /*      if (isNodesAll()) {
            sb.append(" all");
        } */
        for (final Long s : _nodes) {
            sb.append(" ");
            sb.append(s);

        }
        sb.append("\n");
        sb.append("internal edges:");
   /*     if (isInternalEdgesAll()) {
            sb.append(" all");
        } */
        for (final Long s : _internal_edges) {
            sb.append(" ");
            sb.append(s);

        }
        sb.append("\n");
        sb.append("external edges:");
 /*       if (isExternalEdgesAll()) {
            sb.append(" all");
        } */
        for (final Long s : _external_edges) {
            sb.append(" ");
            sb.append(s);

        }
        sb.append("\n");
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
	     final CyGroupsElement e = this;
	        w.writeStartObject();

	        w.writeNumberFieldIfNotEmpty(CyGroupsElement.GROUP_ID, e.getGroupId());
	        w.writeNumberFieldIfNotEmpty(CyGroupsElement.SUBNET, e.getSubNet());
	        w.writeStringFieldIfNotEmpty(CyGroupsElement.GROUP_NAME, e.getName());
	        w.writeBooleanField(CyGroupsElement.IS_COLLAPSED, e._isCollapsed);
	        
	/*        if (e.isNodesAll()) {
	            w.writeStringField(CyGroupsElement.NODES, "all");
	        }
	        else { */
	            w.writeLongList(CyGroupsElement.NODES, e.getNodes());
	//        }
	/*        if (e.isExternalEdgesAll()) {
	            w.writeStringField(CyGroupsElement.EXTERNAL_EDGES, "all");
	        }
	        else { */
	            w.writeLongList(CyGroupsElement.EXTERNAL_EDGES, e.getExternalEdges());
	 /*       }
	        if (e.isInternalEdgesAll()) {
	            w.writeStringField(CyGroupsElement.INTERNAL_EDGES, "all");
	        }
	        else { */
	            w.writeLongList(CyGroupsElement.INTERNAL_EDGES, e.getInternalEdges());
	     //   }
	        w.writeEndObject();	
	        w.flush();
	}

	public boolean isCollapsed() {
		return _isCollapsed;
	}

	public void set_isCollapsed(boolean _isCollapsed) {
		this._isCollapsed = _isCollapsed;
	}

}
