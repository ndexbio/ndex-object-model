package org.ndexbio.model.object.network;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

//TODO: nodeIds and edgeIds are not defined yet.
public class Subnetwork extends PropertiedNetworkElement {

	
	private SubnetworkType _subnetworktype; 
	private String _name; 

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

}
