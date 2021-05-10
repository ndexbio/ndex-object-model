package org.ndexbio.model.network.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FilterCriterion {
    private String name;
    private String value;
    
    // comparison operator
    // Supported operators are: >, <, =, !=
    private String operator; 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) throws Exception {
		if ( operator.equals(">") || operator.equals("<") ||
				operator.equals("=") || operator.equals("!="))
		 this.operator = operator;
		else 
			throw new Exception("Invalid operator for filter. Valid values are: >, <, =, !=");
	}
    
    

}
