/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlWindowDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeDocType;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeExternalScript;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeExternalStyle;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeMeta;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeScript;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeStyle;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.DehydrationParameters;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.state.PropertyState;
import uk.co.iizuka.kozo.state.State;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.html.Html;
import uk.co.iizuka.kozo.ui.html.HtmlDocument;
import uk.co.iizuka.kozo.ui.html.HtmlDocument.ExternalScript;
import uk.co.iizuka.kozo.ui.html.HtmlDocument.ExternalStyle;
import uk.co.iizuka.kozo.ui.html.HtmlDocument.MetadataName;
import uk.co.iizuka.kozo.ui.html.HtmlDocument.Script;
import uk.co.iizuka.kozo.ui.html.HtmlDocument.Style;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;
import uk.co.iizuka.kozo.ui.traversal.DelegatingContainerVisitor;
import uk.co.iizuka.kozo.util.lang.ObjectUtils;

/**
 * Hydrator that dehydrates a {@code Window} component to an HTML {@code <html/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlWindowDehydrator.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
 * @see Window
 * @param <T>
 *            the window type this visitor can visit
 */
public class HtmlWindowDehydrator<T extends Window>
	extends DelegatingContainerVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// TODO: move debug dehydrating elsewhere
	// TODO: reinstate validation dehydrating as a decorator
	// TODO: support Window.image as favicon
	
	// constructors -----------------------------------------------------------
	
	public HtmlWindowDehydrator(ContainerVisitor<Box, HydrationContext, HydrationException> boxDehydrator)
	{
		super(boxDehydrator);
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T window, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		// don't write XML declaration as it switches IE6 into quirks mode
//		out.writeStartDocument();
		
		try
		{
			writeDocType(out);
			out.writeStartElement("html");
			out.writeDefaultNamespace(Html.XMLNS);

			writeHead(window, context);
			
			out.writeStartElement("body");
			writeClass(out, getWindowCssClass(window));
			
			if (context.get(DehydrationParameters.class, new DehydrationParameters()).isDebug())
			{
				writeDebug((DehydrationContext) context);
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		super.visit(window, context);
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T window, HydrationContext context) throws HydrationException
	{
		super.endVisit(window, context);
		
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// body
			out.writeEndElement();
			// html
			out.writeEndElement();
			out.writeEndDocument();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getWindowCssClass(T window)
	{
		return new CssClassBuilder("window");
	}
	
	// private methods --------------------------------------------------------
	
	private static void writeHead(Window window, HydrationContext context) throws XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		out.writeStartElement("head");
		
		writeTitle(window, context);
		writeHeadComments(context);
		writeScripts(context);
		writeStyles(context);
		writeMetadata(context);
		
		// head
		out.writeEndElement();
	}
	
	private static void writeTitle(Window window, HydrationContext context) throws XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		String title = window.getTitle();
		
		if (title.length() > 0)
		{
			out.writeStartElement("title");
			out.writeCharacters(title);
			// title
			out.writeEndElement();
		}
	}
	
	private static void writeHeadComments(HydrationContext context) throws XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		HtmlDocument document = context.get(HtmlDocument.class, null);
		
		if (document != null)
		{
			for (String comment : document.getComments())
			{
				out.writeComment(comment);
			}
		}
	}
	
	private static void writeScripts(HydrationContext context) throws XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		HtmlDocument document = context.get(HtmlDocument.class, null);
		
		if (document != null)
		{
			for (ExternalScript externalScript : document.getExternalScripts())
			{
				writeExternalScript(out, externalScript.getUri(), externalScript.getType());
			}
			
			for (Script script : document.getScripts())
			{
				writeScript(out, script.getScript(), script.getType());
			}
		}
	}
	
	private static void writeStyles(HydrationContext context) throws XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		HtmlDocument document = context.get(HtmlDocument.class, null);
		
		if (document != null)
		{
			for (ExternalStyle externalStyle : document.getExternalStyles())
			{
				writeExternalStyle(out, externalStyle.getUri(), externalStyle.getType());
			}
			
			for (Style style : document.getStyles())
			{
				writeStyle(out, style.getStyle(), style.getType());
			}
		}
	}
	
	private static void writeMetadata(HydrationContext context) throws XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		HtmlDocument document = context.get(HtmlDocument.class, null);
		
		if (document != null)
		{
			for (MetadataName name : document.getMetadataNames())
			{
				String content = document.getMetadataValue(name);
				
				writeMeta(out, name.getName(), content, name.isHttp());
			}
		}
	}
	
	private static void writeDebug(DehydrationContext context) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		State state = context.getState();
		
		out.writeStartElement("div");
		out.writeAttribute("id", "debug");
		
		out.writeStartElement("p");
		out.writeAttribute("class", "title");
		out.writeCharacters("Debug");
		// p
		out.writeEndElement();
		
		List<PropertyState> properties = state.getProperties();
		if (properties.isEmpty())
		{
			out.writeStartElement("p");
			out.writeCharacters("None");
			// p
			out.writeEndElement();
		}
		
		for (PropertyState property : properties)
		{
			writeProperty(property, context);
		}
		
		// TODO: write events when state holds last fired event?
		
		// div
		out.writeEndElement();
	}
	
	private static void writeProperty(PropertyState property, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		out.writeStartElement("p");
		out.writeAttribute("class", "property");
		
		out.writeStartElement("span");
		out.writeAttribute("class", "bean");
		out.writeCharacters(property.getBean().toString());
		// span
		out.writeEndElement();
		out.writeCharacters(".");
		
		out.writeStartElement("span");
		out.writeAttribute("class", "name");
		out.writeCharacters(property.getDescriptor().getName());
		// span
		out.writeEndElement();
		out.writeCharacters("=");
		
		out.writeStartElement("span");
		out.writeAttribute("class", "value");
		out.writeCharacters(ObjectUtils.toString(property.getValue()));
		// span
		out.writeEndElement();
		
		// p
		out.writeEndElement();
	}
}
