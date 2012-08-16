/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlLabelDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeFor;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeImage;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeToolTip;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Image;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Style;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Hydrator that dehydrates a {@code Label} component to an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlLabelDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Label
 * @param <T>
 *            the label type this visitor can visit
 */
public class HtmlLabelDehydrator<T extends Label> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T label, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		String text = label.getText();
		Image image = label.getImage();
		
		if (text.length() > 0 || image != null)
		{
			try
			{
				out.writeStartElement(getElement(label));
				writeClass(out, getLabelCssClass(label));
				
				Component labelFor = label.getLabelFor();
				
				if (labelFor != null)
				{
					writeFor(context, labelFor);
				}
				
				writeToolTip(out, label.getToolTip());
				
				writeImage(out, image);
				
				dehydrateLabelText(label, context);
				
				// p
				out.writeEndElement();
			}
			catch (XMLStreamException exception)
			{
				throw new HydrationException(exception);
			}
		}

		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getLabelCssClass(T label)
	{
		CssClassBuilder builder = new CssClassBuilder("label");
		
		if (label.hasStyle(Style.WARNING))
		{
			builder.append("label-warning");
		}
		
		if (label.hasStyle(Style.ERROR))
		{
			builder.append("label-error");
		}
		
		return builder;
	}
	
	protected void dehydrateLabelText(T label, HydrationContext context) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String text = label.getText();
		
		out.writeCharacters(text);
	}
	
	// private methods --------------------------------------------------------
	
	private static String getElement(Label label)
	{
		String element;
		
		if (label.getLabelFor() != null)
		{
			element = "label";
		}
		else if (label.hasStyle(Style.HEADING1))
		{
			element = "h1";
		}
		else if (label.hasStyle(Style.HEADING2))
		{
			element = "h2";
		}
		else if (label.hasStyle(Style.HEADING3))
		{
			element = "h3";
		}
		else
		{
			element = "p";
		}
		
		return element;
	}
}
