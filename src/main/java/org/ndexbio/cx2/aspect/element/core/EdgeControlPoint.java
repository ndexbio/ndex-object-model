package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EdgeControlPoint {


	private static final String SIN = "sin";
	
	@JsonProperty(SIN)
	private double sin;
	
	@JsonProperty("cos")
	private double cos;
	
	@JsonProperty("ratio")
	private double ratio;
	
	public EdgeControlPoint(double sin, double cos, double ratio) {
		this.sin = sin;
		this.cos = cos;
		this.ratio = ratio;
	}
	
	
	public static EdgeControlPoint createFromCX1String(String cx1Value) {
		final String[] values = cx1Value.split(",");
		
		return new EdgeControlPoint(
			Double.parseDouble(values[0]),
			Double.parseDouble(values[1]),
			Double.parseDouble(values[2]));
	
	}
	
	public static EdgeControlPoint createFromMap(Map<String,Object> m)  {
		return new EdgeControlPoint(
				((Number)m.get(SIN)).doubleValue(),
				((Number)m.get("cos")).doubleValue(),
				((Number)m.get("ratio")).doubleValue()				
				);
	}

	@JsonIgnore
	public double getSin() {
		return sin;
	}

	@JsonIgnore
	public double getCos() {
		return cos;
	}

	@JsonIgnore
	public double getRatio() {
		return ratio;
	}

}
