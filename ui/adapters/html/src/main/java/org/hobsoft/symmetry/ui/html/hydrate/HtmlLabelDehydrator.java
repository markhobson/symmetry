/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlLabelDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeFor;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeImage;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeToolTip;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * Hydrator that dehydrates a {@code Label} component to an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
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
