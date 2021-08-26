package org.ndexbio.cx2.aspect.element.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

public class FontFace {
	
	public static final String NORMAL = "normal";
	public static final String BOLD = "bold";
	public static final String ITALIC = "italic";
	
	public static final String PORTABLE_SERIF_FONT = "serif";
	public static final String PORTABLE_SANS_SERIF_FONT = "sans-serif";
	public static final String PORTABLE_MONOSPACE_FONT = "monospace";	

	@JsonProperty("FONT_FAMILY")
	private String family;
	
	@JsonProperty("FONT_STYLE")	
	private String style;
	
	@JsonProperty("FONT_WEIGHT")
	private String weight;
	
	@JsonProperty("FONT_NAME")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FontFace() {
		family = PORTABLE_SANS_SERIF_FONT;
		style = NORMAL;
		weight = NORMAL;
	}

	public FontFace(String family, String style, String weight) {
		this.family = family;
		this.style = style;
		this.weight = weight;
	}

	public FontFace(String family, String name, String style, String weight) {
		this.family = family;
		this.style = style;
		this.weight = weight;
		this.name = name;
	}

	public static FontFace createFromMap(Map<String,String> m ) {
		FontFace result = new FontFace();
		String s = m.get("FONT_FAMILY");
		
		result.setFamily(s);
		result.setStyle(m.get("FONT_STYLE"));
		result.setWeight(m.get("FONT_WEIGHT"));
		result.setName(m.get("FONT_NAME"));
		return result;
		
	}
	
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	/*public boolean equals(Object object) {
		if (object instanceof FontFace) {
			FontFace labelFontFace = (FontFace) object;
			return labelFontFace.getFamily().equals(family) 
					&& labelFontFace.getStyle().equals(style)
					&& labelFontFace.getWeight().equals(weight);
		} else {
			return false;
		}
		
	} */

}
