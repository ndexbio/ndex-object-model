package org.ndexbio.cx2.converter;

import java.util.Set;

import org.ndexbio.cx2.aspect.element.core.FontFace;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Conversion from common OS font faces to the most basic web-safe fonts.
 * 
 * Common font names are collected from https://www.cssfontstack.com/
 * 
 * Java Logical font names are also included: https://docs.oracle.com/javase/tutorial/2d/text/fonts.html#logical-fonts
 * 
 * @author dotasek.dev@gmail.com
 *
 */
public class FontFaceConverter {
	
	static final Set<String> SERIF_FONTS = Set.of("bigcaslon", "bodonimt", "bookantiqua", "calistomt",

			"cambria", "didot", "garamond", "georgia",

			"goudyoldstyle", "hoeflertext", "lucidabright", "palatino",

			"perpetua", "rockwell", "rockwellextrabold", "baskerville",

			"timesnewroman",
			
			"serif" // Java Logical Font
			);

	static final Set<String> SANS_SERIF_FONTS = Set.of("arial", "arialblack", "arialmt", "arialnarrow",
			"arialroundedmtbold",

			"avantgarde", "calibri", "candara", "centurygothic",

			"franklingothicmedium", "futura", "geneva", "gillsans",

			"helveticaneue", "impact", "lucidagrande", "optima",

			"segoeui", "tahoma", "trebuchetms", "verdana",
			
			"dialog", "sansserif" // Java Logical Font
			);

	static final Set<String> MONO_FONTS = Set.of("consolas", "couriernew", "lucidaconsole", "lucidasanstypewriter",
			"monaco", "andalemono", 
			"dialoginput", "monospace" // Java Logical Font
			);
	
	private static final String BOLD = "bold";
	private static final String ITALIC = "italic";
	private static final String BOLD_ITALIC = "bolditalic";
	
	static final String PORTABLE_SERIF_FONT = "serif";
	static final String PORTABLE_SANS_SERIF_FONT = "sans-serif";
	static final String PORTABLE_MONOSPACE_FONT = "monospace";
	static final String ALPHANUMERIC_REGEX = "[^a-zA-Z0-9-]";

	
	public static FontFace convertFont(String fontString) {
		final String normalizedFont = fontString.replaceAll(ALPHANUMERIC_REGEX, "").toLowerCase();
	
		final String fontWeight;
		final String fontStyle;
		final String fontName;
		
		if (normalizedFont.endsWith(BOLD_ITALIC)) {
			fontName = normalizedFont.substring(normalizedFont.length() - BOLD_ITALIC.length());
			fontWeight = FontFace.BOLD;
			fontStyle = FontFace.ITALIC;
		} else if (normalizedFont.endsWith(ITALIC)) {
			fontName = normalizedFont.substring(normalizedFont.length() - ITALIC.length());
			fontWeight = FontFace.NORMAL;
			fontStyle = FontFace.ITALIC;
		} else if (normalizedFont.endsWith(BOLD)) {
			fontName = normalizedFont.substring(normalizedFont.length() - BOLD.length());
			fontWeight = FontFace.BOLD;
			fontStyle = FontFace.NORMAL;
		} else 
		{
			fontName = normalizedFont;
			fontWeight = FontFace.NORMAL;
			fontStyle = FontFace.NORMAL;
		}
		
		final String fontFamily;
		if (SANS_SERIF_FONTS.contains(fontName)) {
			fontFamily = PORTABLE_SANS_SERIF_FONT;
		}
		else if (SERIF_FONTS.contains(fontName)) {
			fontFamily = PORTABLE_SERIF_FONT;
		}
		else if (MONO_FONTS.contains(fontName)) {
			fontFamily = PORTABLE_MONOSPACE_FONT;
		} else {
			fontFamily = PORTABLE_SANS_SERIF_FONT;
		}
		FontFace result = new FontFace(fontFamily, fontStyle, fontWeight);
		result.setName(fontString.split(",")[0]);
		return result;
	}
	
	
	public static String convertToCX1String(FontFace font) {
		if ( font.getName()!=null && font.getName().length()>0)
			return font.getName();
		
		String result = font.getFamily();
		if (font.getWeight().equals(FontFace.BOLD)) {
			if ( font.getStyle().equals(FontFace.ITALIC)) {
				result += " Bold Italic";
			} else
				result += " Bold";
		} else if ( font.getStyle().equals(FontFace.ITALIC)) {
			result += " Italic";
		}
		return result;
	}

	public static void main(String[] args) throws JsonProcessingException {
		String[] testFontStrings = new String[] {
			"Arial Italic",
			"Bookman Old Style Bold",
			"AvenirNext-BoldItalic",
			"Lucida Console",
			"Perpetua",
			"Dialog.bold",
			"Arial Bold Italic"
		};
				
		ObjectMapper om = new ObjectMapper();
		
		for (String testFontString : testFontStrings) {
			System.out.println(testFontString + "\t" + om.writeValueAsString(convertFont(testFontString)));
		}
	}
}