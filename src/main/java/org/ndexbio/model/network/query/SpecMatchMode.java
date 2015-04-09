package org.ndexbio.model.network.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public enum SpecMatchMode {

	Source, Target, Both, Either;
}
