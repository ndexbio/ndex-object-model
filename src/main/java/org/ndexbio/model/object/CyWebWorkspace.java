package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true, value = { "externalId","isDeleted" })


@JsonInclude(Include.NON_NULL)

public class CyWebWorkspace extends NdexExternalObject {

	private String name;
	private Map<String,Object> options;
	private List<UUID> networkIDs;
	
	public CyWebWorkspace () {
		super();
		options = new HashMap<>();
		setNetworkIDs(new ArrayList<>());
	}



	public List<UUID> getNetworkIDs() {
		return networkIDs;
	}

	public void setNetworkIDs(List<UUID> networkIDs) {
		this.networkIDs = networkIDs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Map<String,Object> getOptions() {
		return options;
	}



	public void setOptions(Map<String,Object> options) {
		this.options = options;
	}
	
	public void setWorkspaceId(UUID id) {super.setExternalId(id);}
	public UUID getWorkspaceId()  {return super.getExternalId();}
}
