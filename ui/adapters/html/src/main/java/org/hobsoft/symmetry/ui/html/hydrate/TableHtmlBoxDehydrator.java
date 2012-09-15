/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/TableHtmlBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeStyle;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * Hydrator that dehydrates a {@code Box} component to an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the box type this visitor can visit
 */
public class TableHtmlBoxDehydrator<T extends Box> extends AbstractHtmlBoxDehydrator<T>
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
				out.writeStartElement("table");
				writeClass(out, getBoxCssClass(box));
				
				if (isHorizontal(box))
				{
					out.writeStartElement("tr");
				}
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
				if (isHorizontal(box))
				{
					// tr
					out.writeEndElement();
				}
				
				// table
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
			if (!isHorizontal(box))
			{
				out.writeStartElement("tr");
			}
			
			out.writeStartElement("td");
			
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
			// td
			out.writeEndElement();
			
			if (!isHorizontal(box))
			{
				// tr
				out.writeEndElement();
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// AbstractHtmlBoxDehydrator methods --------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CssClassBuilder getBoxCssClass(Box box)
	{
		CssClassBuilder builder = super.getBoxCssClass(box);
		
		if (BoxUtils.getTotalFlex(box) > 0)
		{
			builder.append("flexed");
		}
		
		return builder;
	}
}
