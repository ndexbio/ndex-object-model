package org.ndexbio.cx2.aspect.element.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EdgeBendPoint {

	@JsonProperty("sin")
	private double sin;
	
	@JsonProperty("cos")
	private double cos;
	
	@JsonProperty("ratio")
	private double ratio;
	
	public EdgeBendPoint(double sin, double cos, double ratio) {
		this.sin = sin;
		this.cos = cos;
		this.ratio = ratio;
	}
	
	
	public static EdgeBendPoint createFromCX1String(String cx1Value) {
		final String[] values = cx1Value.split(",");
		
		return new EdgeBendPoint(
			Double.parseDouble(values[0]),
			Double.parseDouble(values[1]),
			Double.parseDouble(values[2]));
	
	}
}
