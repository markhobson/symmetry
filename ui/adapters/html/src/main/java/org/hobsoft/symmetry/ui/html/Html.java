/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/Html.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class Html
{
	// constants --------------------------------------------------------------
	
	public static final String XMLNS = "http://www.w3.org/1999/xhtml";
	
	// types ------------------------------------------------------------------

	/**
	 * 
	 */
	public enum Element
	{
		// constants --------------------------------------------------------------
		
		A,
		ABBR,
		ACRONYM,
		ADDRESS,
		APPLET,
		AREA,
		B,
		BASE,
		BASEFONT,
		BDO,
		BIG,
		BLOCKQUOTE,
		BODY,
		BR,
		BUTTON,
		CAPTION,
		CENTER,
		CITE,
		CODE,
		COL,
		COLGROUP,
		DD,
		DEL,
		DFN,
		DIR,
		DIV,
		DL,
		DT,
		EM,
		FIELDSET,
		FONT,
		FORM,
		FRAME,
		FRAMESET,
		H1,
		H2,
		H3,
		H4,
		H5,
		H6,
		HEAD,
		HR,
		HTML,
		I,
		IFRAME,
		IMG,
		INPUT,
		INS,
		ISINDEX,
		KBD,
		LABEL,
		LEGEND,
		LI,
		LINK,
		MAP,
		MENU,
		META,
		NOFRAMES,
		NOSCRIPT,
		OBJECT,
		OL,
		OPTGROUP,
		OPTION,
		P,
		PARAM,
		PRE,
		Q,
		S,
		SAMP,
		SCRIPT,
		SELECT,
		SMALL,
		SPAN,
		STRIKE,
		STRONG,
		STYLE,
		SUB,
		SUP,
		TABLE,
		TBODY,
		TD,
		TEXTAREA,
		TFOOT,
		TH,
		THEAD,
		TITLE,
		TR,
		TT,
		U,
		UL,
		VAR;
		
		// public methods ---------------------------------------------------------
		
		public String localName()
		{
			return toHtml(name());
		}
	}
	
	/**
	 * 
	 */
	public enum Attribute
	{
		// constants --------------------------------------------------------------
		
		ABBR,
		ACCEPT,
		ACCEPT_CHARSET,
		ACCESSKEY,
		ACTION,
		ALIGN,
		ALINK,
		ALT,
		ARCHIVE,
		AXIS,
		BACKGROUND,
		BGCOLOR,
		BORDER,
		CELLPADDING,
		CELLSPACING,
		CHAR,
		CHAROFF,
		CHARSET,
		CHECKED,
		CITE,
		CLASS,
		CLASSID,
		CLEAR,
		CODE,
		CODEBASE,
		CODETYPE,
		COLOR,
		COLS,
		COLSPAN,
		COMPACT,
		CONTENT,
		COORDS,
		DATA,
		DATETIME,
		DECLARE,
		DEFER,
		DIR,
		DISABLED,
		ENCTYPE,
		FACE,
		FOR,
		FRAME,
		FRAMEBORDER,
		HEADERS,
		HEIGHT,
		HREF,
		HREFLANG,
		HSPACE,
		HTTP_EQUIV,
		ID,
		ISMAP,
		LABEL,
		LANG,
		LANGUAGE,
		LINK,
		LONGDESC,
		MARGINHEIGHT,
		MARGINWIDTH,
		MAXLENGTH,
		MEDIA,
		METHOD,
		MULTIPLE,
		NAME,
		NOHREF,
		NORESIZE,
		NOSHADE,
		NOWRAP,
		OBJECT,
		ONBLUR,
		ONCHANGE,
		ONCLICK,
		ONDBLCLICK,
		ONFOCUS,
		ONKEYDOWN,
		ONKEYPRESS,
		ONKEYUP,
		ONLOAD,
		ONMOUSEDOWN,
		ONMOUSEMOVE,
		ONMOUSEOUT,
		ONMOUSEOVER,
		ONMOUSEUP,
		ONRESET,
		ONSELECT,
		ONSUBMIT,
		ONUNLOAD,
		PROFILE,
		PROMPT,
		READONLY,
		REL,
		REV,
		ROWS,
		ROWSPAN,
		RULES,
		SCHEME,
		SCOPE,
		SCROLLING,
		SELECTED,
		SHAPE,
		SIZE,
		SPAN,
		SRC,
		STANDBY,
		START,
		STYLE,
		SUMMARY,
		TABINDEX,
		TARGET,
		TEXT,
		TITLE,
		TYPE,
		USEMAP,
		VALIGN,
		VALUE,
		VALUETYPE,
		VERSION,
		VLINK,
		VSPACE,
		WIDTH;
		
		// public methods ---------------------------------------------------------
		
		public String localName()
		{
			return toHtml(name());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	private Html()
	{
		throw new AssertionError();
	}

	// private methods --------------------------------------------------------
	
	private static String toHtml(String name)
	{
		return name.toLowerCase().replace('_', '-');
	}
}
