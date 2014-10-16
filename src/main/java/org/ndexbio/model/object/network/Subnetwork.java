package org.ndexbio.model.object.network;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Subnetwork extends PropertiedNetworkElement {

	
	private SubnetworkType _subnetworktype; 
	private String _name; 
	private long[] _nodeIds;  //sorted if we need to do lookups?
	private long[] _edgeIds;

	public Subnetwork () {
		super();
        _type = this.getClass().getSimpleName();

	}
 
	public SubnetworkType getSubnetworktype() {
		return _subnetworktype;
	}

	public void setSubnetworktype(SubnetworkType subnetworktype) {
		this._subnetworktype = subnetworktype;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public long[] getNodeIds() {
		return _nodeIds;
	}

	public void setNodeIds(long[] nodeIds) {
		this._nodeIds = nodeIds;
	}

	public long[] getEdgeIds() {
		return _edgeIds;
	}

	public void setEdgeIds(long[] edgeIds) {
		this._edgeIds = edgeIds;
	}

}
