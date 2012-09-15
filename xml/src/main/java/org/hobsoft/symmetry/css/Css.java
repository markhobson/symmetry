/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/main/java/uk/co/iizuka/kozo/css/Css.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.css;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class Css
{
	// types ------------------------------------------------------------------
	
	/**
	 * 
	 */
	public enum Property
	{
		// constants ----------------------------------------------------------
		
		AZIMUTH,
		BACKGROUND_ATTACHMENT,
		BACKGROUND_COLOR,
		BACKGROUND_IMAGE,
		BACKGROUND_POSITION,
		BACKGROUND_REPEAT,
		BACKGROUND,
		BORDER_COLLAPSE,
		BORDER_COLOR,
		BORDER_SPACING,
		BORDER_STYLE,
		BORDER_TOP,
		BORDER_TOP_COLOR,
		BORDER_TOP_STYLE,
		BORDER_TOP_WIDTH,
		BORDER_WIDTH,
		BORDER,
		BOTTOM,
		CAPTION_SIDE,
		CLEAR,
		CLIP,
		COLOR,
		CONTENT,
		COUNTER_INCREMENT,
		COUNTER_RESET,
		CUE_AFTER,
		CUE_BEFORE,
		CUE,
		CURSOR,
		DIRECTION,
		DISPLAY,
		ELEVATION,
		EMPTY_CELLS,
		FLOAT,
		FONT_FAMILY,
		FONT_SIZE,
		FONT_STYLE,
		FONT_VARIANT,
		FONT_WEIGHT,
		FONT,
		HEIGHT,
		LEFT,
		LETTER_SPACING,
		LINE_HEIGHT,
		LIST_STYLE_IMAGE,
		LIST_STYLE_POSITION,
		LIST_STYLE_TYPE,
		LIST_STYLE,
		MARGIN_RIGHT,
		MARGIN_TOP,
		MARGIN,
		MAX_HEIGHT,
		MAX_WIDTH,
		MIN_HEIGHT,
		MIN_WIDTH,
		ORPHANS,
		OUTLINE_COLOR,
		OUTLINE_STYLE,
		OUTLINE_WIDTH,
		OUTLINE,
		OVERFLOW,
		PADDING_TOP,
		PADDING,
		PAGE_BREAK_AFTER,
		PAGE_BREAK_BEFORE,
		PAGE_BREAK_INSIDE,
		PAUSE_AFTER,
		PAUSE_BEFORE,
		PAUSE,
		PITCH_RANGE,
		PITCH,
		PLAY_DURING,
		POSITION,
		QUOTES,
		RICHNESS,
		RIGHT,
		SPEAK_HEADER,
		SPEAK_NUMERAL,
		SPEAK_PUNCTUATION,
		SPEAK,
		SPEECH_RATE,
		STRESS,
		TABLE_LAYOUT,
		TEXT_ALIGN,
		TEXT_DECORATION,
		TEXT_INDENT,
		TEXT_TRANSFORM,
		TOP,
		UNICODE_BIDI,
		VERTICAL_ALIGN,
		VISIBILITY,
		VOICE_FAMILY,
		VOLUME,
		WHITE_SPACE,
		WIDOWS,
		WIDTH,
		WORD_SPACING,
		Z_INDEX;
		
		// public methods -----------------------------------------------------
		
		public String toCss()
		{
			return name().toLowerCase().replace('_', '-');
		}
	}
	
	/**
	 * 
	 */
	// TODO: is this the best way to enumerate property values?
	public enum Value
	{
		// display
		INLINE,
		BLOCK,
		LIST_ITEM,
		INLINE_BLOCK,
		TABLE,
		INLINE_TABLE,
		TABLE_ROW_GROUP,
		TABLE_HEADER_GROUP,
		TABLE_FOOTER_GROUP,
		TABLE_ROW,
		TABLE_COLUMN_GROUP,
		TABLE_COLUMN,
		TABLE_CELL,
		TABLE_CAPTION,
		NONE,
		
		// text-align
		LEFT,
		RIGHT,
		CENTER,
		JUSTIFY,
//		INHERIT,
		
		// vertical-align
		BASELINE,
		SUB,
		SUPER,
		TOP,
		TEXT_TOP,
		MIDDLE,
		BOTTOM,
		TEXT_BOTTOM,
		INHERIT;
		
		// public methods -----------------------------------------------------
		
		public String toCss()
		{
			return name().toLowerCase().replace('_', '-');
		}
	}
	
	/**
	 * 
	 */
	public enum Unit
	{
		// constants ----------------------------------------------------------
		
		UNKNOWN,
		NUMBER,
		PERCENTAGE("%"),
		EMS("em"),
		EXS("ex"),
		PX("px"),
		CM("cm"),
		MM("mm"),
		IN("in"),
		PT("pt"),
		PC("pc"),
		DEG("deg"),
		RAD("rad"),
		GRAD("grad"),
		MS("ms"),
		S("s"),
		HZ("Hz"),
		KHZ("kHz"),
		DIMENSION,
		STRING,
		URI,
		IDENT,
		ATTR,
		COUNTER,
		RECT,
		RGBCOLOR;
		
		// fields -------------------------------------------------------------
		
		private final String css;
		
		// constructors -------------------------------------------------------
		
		private Unit()
		{
			this("");
		}
		
		private Unit(String css)
		{
			this.css = css;
		}
		
		// public methods -----------------------------------------------------
		
		public String toCss()
		{
			return css;
		}
	}
}
