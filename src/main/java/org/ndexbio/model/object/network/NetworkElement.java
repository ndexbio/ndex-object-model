package org.ndexbio.model.object.network;

import org.ndexbio.model.object.NdexObject;

public abstract class NetworkElement extends NdexObject {
	
	private long _id;

	public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}
	


}
