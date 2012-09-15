/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/ContainerHtmlBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeStyle;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * Hydrator that dehydrates a {@code Box} component to an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @see Box
 * @param <T>
 *            the box type this visitor can visit
 */
public class ContainerHtmlBoxDehydrator<T extends Box> extends AbstractHtmlBoxDehydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T box, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		if (!isEmpty(box))
		{
			try
			{
				out.writeStartElement("div");
				writeClass(out, getBoxCssClass(box));
			}
			catch (XMLStreamException exception)
			{
				throw new HydrationException(exception);
			}
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T box, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		if (!isEmpty(box))
		{
			try
			{
				// div
				out.writeEndElement();
			}
			catch (XMLStreamException exception)
			{
				throw new HydrationException(exception);
			}
		}
				
		return VISIT_SIBLINGS;
	}
	
	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T box, HydrationContext context, int childIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement(getBoxChildElement(box));
			
			writeClass(out, getBoxChildCssClass(box, childIndex));
			writeStyle(out, getBoxChildCssStyle(box, childIndex));
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitChild(T box, HydrationContext context, int childIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// span/div
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// private methods --------------------------------------------------------
	
	private static String getBoxChildElement(Box box)
	{
		return isHorizontal(box) ? "span" : "div";
	}
}
