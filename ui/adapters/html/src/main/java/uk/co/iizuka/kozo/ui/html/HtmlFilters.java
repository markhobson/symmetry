/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/HtmlFilters.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html;

import static uk.co.iizuka.common.xml.stax.filter.EventFilters.and;
import static uk.co.iizuka.common.xml.stax.filter.EventFilters.anyAttribute;
import static uk.co.iizuka.common.xml.stax.filter.EventFilters.attribute;
import static uk.co.iizuka.common.xml.stax.filter.EventFilters.element;
import static uk.co.iizuka.common.xml.stax.filter.EventFilters.not;
import static uk.co.iizuka.common.xml.stax.filter.EventFilters.or;
import static uk.co.iizuka.common.xml.stax.filter.TypeEventFilters.characters;

import javax.xml.stream.EventFilter;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlFilters.java 95182 2011-11-16 10:19:23Z mark@IIZUKA.CO.UK $
 */
public final class HtmlFilters
{
	// constants --------------------------------------------------------------
	
	public static final EventFilter ANY = htmlElement("*");
	
	public static final EventFilter HEADING = or(htmlElements("H1", "H2", "H3", "H4", "H5", "H6"));
	
	public static final EventFilter LIST = or(htmlElements("UL", "OL"));
	
	public static final EventFilter PREFORMATTED = or(htmlElement("PRE"));
	
	public static final EventFilter FONTSTYLE = or(htmlElements("TT", "I", "B", "BIG", "SMALL"));
	
	public static final EventFilter PHRASE = or(htmlElements("EM", "STRONG", "DFN", "CODE", "SAMP", "KBD", "VAR",
		"CITE", "ABBR", "ACRONYM"));
	
	public static final EventFilter SPECIAL = or(htmlElements("A", "IMG", "OBJECT", "BR", "SCRIPT", "MAP", "Q", "SUB",
		"SUP", "SPAN", "BDO"));
	
	public static final EventFilter FORMCTRL = or(htmlElements("INPUT", "SELECT", "TEXTAREA", "LABEL", "BUTTON"));
	
	// TODO: is CHARACTERS_FILTER sufficient for #PCDATA?  add entity references
	public static final EventFilter INLINE = or(characters(), FONTSTYLE, PHRASE, SPECIAL, FORMCTRL);
	
	public static final EventFilter BLOCK = or(
		htmlElement("P"),
		HEADING,
		LIST,
		PREFORMATTED,
		or(htmlElements("DL", "DIV", "NOSCRIPT", "BLOCKQUOTE", "FORM", "HR", "TABLE", "FIELDSET", "ADDRESS"))
	);
	
	public static final EventFilter FLOW = or(BLOCK, INLINE);
	
	// TODO: really allow A?
	// TODO: remove IMG when images workaround no longer needed
	public static final EventFilter SAFE = or(
		or(strictHtmlElements("P", "H1", "H2", "H3", "H4", "H5", "H6", "BR", "HR", "B", "I", "EM", "STRONG", "UL", "OL",
			"LI")),
		strictHtmlElement("A", "href", "target"),
		strictHtmlElement("IMG", "src", "alt")
	);
	
	// constructors -----------------------------------------------------------
	
	private HtmlFilters()
	{
		throw new AssertionError();
	}

	// private methods --------------------------------------------------------
	
	private static EventFilter htmlElement(String localName)
	{
		return element(Html.XMLNS, localName);
	}
	
	private static EventFilter[] htmlElements(String... localNames)
	{
		EventFilter[] filters = new EventFilter[localNames.length];
		
		for (int i = 0; i < localNames.length; i++)
		{
			filters[i] = htmlElement(localNames[i]);
		}
		
		return filters;
	}

	private static EventFilter strictHtmlElement(String localName, String... allowedAttributeLocalNames)
	{
		return and(
			htmlElement(localName),
			// reject if any attribute is not in allowedAttributeLocalNames
			not(anyAttribute(not(or(attributes(allowedAttributeLocalNames)))))
		);
	}

	private static EventFilter[] strictHtmlElements(String... localNames)
	{
		EventFilter[] filters = new EventFilter[localNames.length];
		
		for (int i = 0; i < localNames.length; i++)
		{
			filters[i] = strictHtmlElement(localNames[i]);
		}
		
		return filters;
	}
	
	private static EventFilter[] attributes(String... localNames)
	{
		EventFilter[] filters = new EventFilter[localNames.length];
		
		for (int i = 0; i < localNames.length; i++)
		{
			filters[i] = attribute(localNames[i]);
		}
		
		return filters;
	}
}
