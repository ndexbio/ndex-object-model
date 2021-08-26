package org.ndexbio.cx2.aspect.element.cytoscape;

import java.util.HashMap;
import java.util.Map;

import org.ndexbio.cx2.aspect.element.core.TableColumnVisualStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Abstract class for Cytoscape table visual properties. This class has 2 subclasses. CxTableVisualProperty for CX2,
 *  and CyTableVisualPropertiesElement for CX. 
 * @author jingchen
 *
 */

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractTableVisualProperty {
	
	public final static String ASPECT_NAME = "tableVisualProperties";

/*	@JsonProperty("tableType")
	private DefaultTableType tableType;
	*/
	
	/* First key: table type 
	 * Second key: Column name  
	 * Third key: Table visual property name */
	
	@JsonProperty("styles")
	private Map<DefaultTableType, Map<String, Map<String,TableColumnVisualStyle>>> tableStyles;

	
	@JsonProperty("s")
	private Long subnetId;
	
	public Long getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(Long subnetId) {
		this.subnetId = subnetId;
	}

	public AbstractTableVisualProperty() {
		tableStyles = new HashMap<>();
		
	}
	
/*	public DefaultTableType getTableType() {
		return tableType;
	}

	public void setTableType(DefaultTableType tableType) {
		this.tableType = tableType;
	} */

	public Map<DefaultTableType,Map<String, Map<String, TableColumnVisualStyle>>> getTableStyles() {
		return tableStyles;
	}

	public void setTableStyles(Map<DefaultTableType,Map<String, Map<String, TableColumnVisualStyle>>> tableStyles) {
		this.tableStyles = tableStyles;
	}
	
	@JsonIgnore
	public Map<String, Map<String, TableColumnVisualStyle>> getStylesInTable(DefaultTableType tableType) {
		if ( tableStyles == null)
			return null;
		
		return tableStyles.get(tableType);
	}
	
	@SuppressWarnings("static-method")
	@JsonIgnore
	public String getAspectName() {
		return ASPECT_NAME;
	}
	
}
