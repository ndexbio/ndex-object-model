package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)

//Abstract class for object position such as CustomGraphics, EdgeLabelPosition, etc.
public abstract class CyObjectPosition {
	
	@JsonProperty(LabelPosition.JUSTIFICATION)	
	private HorizontalAlignment justification;
	
	@JsonProperty(ObjectPosition.MARGIN_X)
	private float marginX;
	
	public float getMarginX() {
		return marginX;
	}

	public void setMarginX(float marginX) {
		this.marginX = marginX;
	}

	@JsonProperty(ObjectPosition.MARGIN_Y)
	private float marginY;
	
	public float getMarginY() {
		return marginY;
	}

	public void setMarginY(float marginY) {
		this.marginY = marginY;
	}

	public HorizontalAlignment getJustification() {
		return justification;
	}

	public void setJustification(HorizontalAlignment justification) {
		this.justification = justification;
	}
	
	public CyObjectPosition() {
		justification = HorizontalAlignment.center;
	}
	
	protected String creatPartialCX1ValueString() {
		return getJustification().toCX1() + "," + getMarginX() + "," + getMarginY();
	}

	protected void populateFromMap(Map<String, Object> m) {
		setMarginX(((Number)m.get("MARGIN_X")).floatValue());
		setMarginY(((Number)m.get("MARGIN_Y")).floatValue());
		setJustification(HorizontalAlignment.valueOf((String)m.get(LabelPosition.JUSTIFICATION)));
		
	}
	
}
