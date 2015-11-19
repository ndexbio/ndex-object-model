package org.ndexbio.model.object;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CXSimplePathQuery extends SimplePathQuery {

	private Set<String> aspects;
	public CXSimplePathQuery() {
		aspects = new TreeSet<>();
	}
	public Set<String> getAspects() {
		return aspects;
	}
	public void setAspects(Set<String> aspects) {
		this.aspects = aspects;
	}

}
