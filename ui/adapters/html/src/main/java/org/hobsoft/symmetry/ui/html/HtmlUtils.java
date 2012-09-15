/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/HtmlUtils.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EventObject;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.css.CssStyleBuilder;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.support.xml.stax.EventTransformer;
import org.hobsoft.symmetry.ui.Enableable;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.common.BeanDehydrationUtils;
import org.hobsoft.symmetry.ui.html.state.HtmlEventStateCodec;
import org.hobsoft.symmetry.xml.XmlUtils;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.path;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.startElement;
import static org.hobsoft.symmetry.support.xml.stax.filter.TypeEventFilters.startDocument;
import static org.hobsoft.symmetry.support.xml.stax.transform.EventTransformers.compose;
import static org.hobsoft.symmetry.support.xml.stax.transform.EventTransformers.namespace;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class HtmlUtils
{
	// constants --------------------------------------------------------------
	
	private static final String DOCTYPE_PUBLIC_ID = "-//W3C//DTD HTML 4.01//EN";
	
	private static final String DOCTYPE_SYSTEM_ID = "http://www.w3.org/TR/html4/strict.dtd";
	
	private static final String CSS_TYPE = "text/css";
	
	private static final String JAVASCRIPT_TYPE = "text/javascript";
	
	private static final String WEB_RESOURCE_PATH = "META-INF/resources/";
	
	// constructors -----------------------------------------------------------
	
	private HtmlUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void writeDocType(XMLStreamWriter out) throws XMLStreamException
	{
		StringBuilder builder = new StringBuilder("<!DOCTYPE html PUBLIC \"");
		builder.append(DOCTYPE_PUBLIC_ID);
		builder.append("\" \"");
		builder.append(DOCTYPE_SYSTEM_ID);
		builder.append("\">");
		out.writeDTD(builder.toString());
	}
	
	public static void writeExternalCss(XMLStreamWriter out, String href) throws XMLStreamException
	{
		writeExternalStyle(out, href, CSS_TYPE);
	}
	
	public static void writeExternalStyle(XMLStreamWriter out, String href, String type) throws XMLStreamException
	{
		out.writeEmptyElement("link");
		out.writeAttribute("rel", "stylesheet");
		out.writeAttribute("type", type);
		out.writeAttribute("href", href);
	}
	
	public static void writeCss(XMLStreamWriter out, String css) throws XMLStreamException
	{
		writeStyle(out, css, CSS_TYPE);
	}
	
	public static void writeStyle(XMLStreamWriter out, String style, String type) throws XMLStreamException
	{
		out.writeStartElement("style");
		out.writeAttribute("type", type);
		out.writeCharacters(style);
		out.writeEndElement();
	}
	
	public static void writeExternalJavaScript(XMLStreamWriter out, String href) throws XMLStreamException
	{
		writeExternalScript(out, href, JAVASCRIPT_TYPE);
	}
	
	public static void writeExternalScript(XMLStreamWriter out, String href, String type) throws XMLStreamException
	{
		out.writeStartElement("script");
		out.writeAttribute("type", type);
		out.writeAttribute("src", href);
		out.writeEndElement();
	}
	
	public static void writeJavaScript(XMLStreamWriter out, String javaScript) throws XMLStreamException
	{
		writeScript(out, javaScript, JAVASCRIPT_TYPE);
	}
	
	public static void writeScript(XMLStreamWriter out, String script, String type) throws XMLStreamException
	{
		out.writeStartElement("script");
		out.writeAttribute("type", type);
		out.writeCharacters(script);
		out.writeEndElement();
	}
	
	public static void writeMeta(XMLStreamWriter out, String name, String content, boolean http)
		throws XMLStreamException
	{
		String nameAttributeName = http ? "http-equiv" : "name";
		
		out.writeEmptyElement("meta");
		out.writeAttribute(nameAttributeName, name);
		out.writeAttribute("content", content);
	}
	
	public static void writeAnchor(XMLStreamWriter out, String href, String text) throws XMLStreamException
	{
		out.writeStartElement("a");
		out.writeAttribute("href", href);
		out.writeCharacters(text);
		out.writeEndElement();
	}
	
	public static void writeHref(DehydrationContext context) throws HydrationException, XMLStreamException
	{
		DehydrationContext newContext = createContext(context, false);
		
		writeState(newContext, "href");
	}
	
	public static void writeHref(DehydrationContext context, Object bean, String propertyName, Object value)
		throws HydrationException, XMLStreamException
	{
		DehydrationContext newContext = createContext(context, false);
		
		writeState(newContext, "href", bean, propertyName, value);
	}
	
	public static void writeHref(DehydrationContext context, String eventSetName, EventObject eventObject)
		throws HydrationException, XMLStreamException
	{
		DehydrationContext newContext = createContext(context, false);
		
		writeState(newContext, "href", eventSetName, eventObject);
	}
	
	public static void writeHref(DehydrationContext context, String eventSetName, String listenerMethodName,
		EventObject eventObject) throws HydrationException, XMLStreamException
	{
		DehydrationContext newContext = createContext(context, false);
		
		writeState(newContext, "href", eventSetName, listenerMethodName, eventObject);
	}
	
	public static void writeEvent(DehydrationContext context, String attributeName) throws HydrationException,
		XMLStreamException
	{
		DehydrationContext newContext = createContext(context, true);
		
		writeState(newContext, attributeName);
	}
	
	public static void writeEvent(DehydrationContext context, String attributeName, Object bean, String propertyName,
		Object value) throws HydrationException, XMLStreamException
	{
		DehydrationContext newContext = createContext(context, true);
		
		writeState(newContext, attributeName, bean, propertyName, value);
	}
	
	public static void writeEvent(DehydrationContext context, String attributeName, String eventSetName,
		EventObject eventObject) throws HydrationException, XMLStreamException
	{
		DehydrationContext newContext = createContext(context, true);
			
		writeState(newContext, attributeName, eventSetName, eventObject);
	}
	
	public static void writeText(XMLStreamWriter out, String text, int mnemonic) throws XMLStreamException
	{
		if (text == null || text.length() == 0)
		{
			return;
		}
		
		if (mnemonic == -1)
		{
			out.writeCharacters(text);
			return;
		}
		
		int index = text.indexOf(mnemonic);
		if (index == -1)
		{
			mnemonic = Character.isUpperCase((char) mnemonic) ? Character.toLowerCase((char) mnemonic)
				: Character.toUpperCase((char) mnemonic);
			index = text.indexOf(mnemonic);
		}
		
		if (index == -1)
		{
			out.writeCharacters(text);
		}
		else
		{
			out.writeCharacters(text.substring(0, index));
			out.writeStartElement("span");
			out.writeAttribute("class", "mnemonic");
			out.writeCharacters(Character.toString((char) mnemonic));
			// span
			out.writeEndElement();
			out.writeCharacters(text.substring(index + 1));
		}
	}
	
	public static void writeImage(XMLStreamWriter out, Image image) throws XMLStreamException
	{
		if (image != null)
		{
			out.writeEmptyElement("img");
			writeImageUrl(out, "src", image);
		}
	}
	
	public static void writeImageUrl(XMLStreamWriter out, String attributeName, Image image) throws XMLStreamException
	{
		if (image == null)
		{
			return;
		}
		
		URL url = image.getUrl();
		String src;
		
		// supported web URL schemes
		if (isWebScheme(url.getProtocol()))
		{
			src = url.toString();
		}
		// web resources
		else if (isWebPath(url))
		{
			src = getWebPath(url);
		}
		else
		{
			// TODO: dehydrate as either a URI to load the image data or as a data URI
			throw new UnsupportedOperationException("Data images are not yet supported");
		}
		
		out.writeAttribute(attributeName, src);
	}
	
	public static void writeClass(XMLStreamWriter out, CssClassBuilder cssClass) throws XMLStreamException
	{
		if (cssClass != null)
		{
			XmlUtils.writeAttributeIfNotEmpty(out, "class", cssClass.toString());
		}
	}
	
	public static void writeStyle(XMLStreamWriter out, CssStyleBuilder style) throws XMLStreamException
	{
		if (style != null)
		{
			XmlUtils.writeAttributeIfNotEmpty(out, "style", style.toString());
		}
	}
	
	public static void writeFor(HydrationContext context, Object component) throws HydrationException,
		XMLStreamException
	{
		XmlUtils.writeId(context, "for", component);
	}
	
	public static void writeName(HydrationContext context, Object component) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String value = BeanDehydrationUtils.encodeComponent(context, component);
		
		XmlUtils.writeAttributeIfNotEmpty(out, "name", value);
	}
	
	public static void writeToolTip(XMLStreamWriter out, String toolTip) throws XMLStreamException
	{
		XmlUtils.writeAttributeIfNotEmpty(out, "title", toolTip);
	}
	
	public static void writeMnemonic(XMLStreamWriter out, int mnemonic) throws XMLStreamException
	{
		if (mnemonic > 0)
		{
			out.writeAttribute("accesskey", Character.toString((char) mnemonic));
		}
	}
	
	public static void writeEnabled(XMLStreamWriter out, Enableable enableable) throws XMLStreamException
	{
		XmlUtils.writeAttributeIf(out, "disabled", "disabled", !enableable.isEnabled());
	}
	
	public static void writeReadOnly(XMLStreamWriter out, boolean readOnly) throws XMLStreamException
	{
		XmlUtils.writeAttributeIf(out, "readonly", "readonly", readOnly);
	}
	
	public static void writeSelected(XMLStreamWriter out, Selectable selectable) throws XMLStreamException
	{
		XmlUtils.writeAttributeIf(out, "checked", "checked", selectable.isSelected());
	}
	
	public static boolean isHtmlFragment(String html)
	{
		if (XmlUtils.isXmlFragment(html))
		{
			return true;
		}
		
		// assume fragment if document element is not HTML
		try
		{
			return !XmlUtils.matches(html, path(startDocument(), startElement("HTML")));
		}
		catch (XMLStreamException exception)
		{
			// assume fragment if parsing error encountered
			return true;
		}
	}
	
	public static void writeHtmlFragment(XMLStreamWriter out, String html) throws XMLStreamException
	{
		writeHtmlFragment(out, html, null);
	}
	
	public static void writeHtmlFragment(XMLStreamWriter out, String html, EventTransformer transformer)
		throws XMLStreamException
	{
		if (isHtmlFragment(html))
		{
			html = "<html><body>" + html + "</body></html>";
		}
		
		out.setDefaultNamespace(Html.XMLNS);
		
		// TODO: should use HTML namespace but filtering is done before transformation
		EventFilter filter = path(startElement("HTML"), startElement("BODY"));
		
		// transform into HTML namespace
		transformer = compose(namespace(Html.XMLNS), transformer);
		
		XmlUtils.writeXmlFragment(out, html, filter, transformer);
	}
	
	// private methods --------------------------------------------------------
	
	private static DehydrationContext createContext(DehydrationContext parent, boolean event)
	{
		DehydrationContext context = new DehydrationContext(parent);
		context.getOrSet(HtmlEventStateCodec.Parameters.class, new HtmlEventStateCodec.Parameters()).setEvent(event);
		return context;
	}
	
	private static void writeState(DehydrationContext context, String attributeName) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String state = BeanDehydrationUtils.encodeState(context);
		
		out.writeAttribute(attributeName, state);
	}

	private static void writeState(DehydrationContext context, String attributeName, Object bean, String propertyName,
		Object value) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String state = BeanDehydrationUtils.encodeState(context, bean, propertyName, value);
		
		out.writeAttribute(attributeName, state);
	}
	
	private static void writeState(DehydrationContext context, String attributeName, String eventSetName,
		EventObject eventObject) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String state = BeanDehydrationUtils.encodeState(context, eventSetName, eventObject);
		
		out.writeAttribute(attributeName, state);
	}
	
	private static void writeState(DehydrationContext context, String attributeName, String eventSetName,
		String listenerMethodName, EventObject eventObject) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String state = BeanDehydrationUtils.encodeState(context, eventSetName, listenerMethodName, eventObject);
		
		out.writeAttribute(attributeName, state);
	}
	
	private static boolean isWebScheme(String scheme)
	{
		return "http".equals(scheme) || "https".equals(scheme);
	}
	
	private static boolean isWebPath(URL url)
	{
		// TODO: support web resource paths outside of a JAR?  not strictly Servlet 3.0 but occurs in exploded webapps
		
		URI uri;
		try
		{
			uri = url.toURI();
		}
		catch (URISyntaxException exception)
		{
			return false;
		}
		
		if (!"jar".equals(uri.getScheme()))
		{
			return false;
		}
		
		String part = uri.getSchemeSpecificPart();
		String jarEntry = substringAfter(part, "!/");
		return jarEntry.startsWith(WEB_RESOURCE_PATH);
	}
	
	private static String getWebPath(URL url)
	{
		if (!isWebPath(url))
		{
			throw new IllegalArgumentException("url is not a web path: " + url);
		}
		
		URI uri;
		try
		{
			uri = url.toURI();
		}
		catch (URISyntaxException exception)
		{
			throw new IllegalArgumentException("url must be a valid URI: " + url, exception);
		}
		
		String part = uri.getSchemeSpecificPart();
		return substringAfter(part, "!/" + WEB_RESOURCE_PATH);
	}
	
	private static String substringAfter(String string, String substring)
	{
		int index = string.indexOf(substring);
		
		return (index != -1) ? string.substring(index + substring.length()) : "";
	}
}
