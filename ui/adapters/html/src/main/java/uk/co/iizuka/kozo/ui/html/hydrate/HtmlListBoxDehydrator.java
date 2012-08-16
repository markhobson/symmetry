/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlListBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.createFilteredWriter;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttribute;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIf;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotNull;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.common.xml.stax.filter.TypeEventFilters;
import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.SelectionMode;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.traversal.NullListBoxVisitor;

/**
 * Hydrator that dehydrates a {@code ListBox} component to an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlListBoxDehydrator.java 100086 2012-03-30 10:28:05Z mark@IIZUKA.CO.UK $
 * @see ListBox
 * @param <T>
 *            the list box type this visitor can visit
 */
public class HtmlListBoxDehydrator<T extends ListBox<?>>
	extends NullListBoxVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// TODO: can we reuse HtmlComboBoxDehydrator?
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T listBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("select");
			writeClass(out, getListBoxCssClass(listBox));
			writeName(context, listBox);
			writeAttribute(out, "size", listBox.getVisibleRowCount());
			
			writeAttributeIf(out, "multiple", "multiple", listBox.getSelectionMode() == SelectionMode.MULTIPLE);
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
	public EndVisit endVisit(T listBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// select
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// ListBoxVisitor methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitItem(T listBox, HydrationContext context, int itemIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean selected = listBox.isIndexSelected(itemIndex);
		
		try
		{
			out.writeStartElement("option");
			
			String encodedValue = encodePropertyValue(context, listBox, ListBox.SELECTED_INDEXES_PROPERTY,
				new int[] {itemIndex});
			writeAttributeIfNotNull(out, "value", encodedValue);
			
			writeAttributeIf(out, "selected", "selected", selected);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		// create filtered context that only allows character events
		context.push(XMLStreamWriter.class, createFilteredWriter(out, TypeEventFilters.characters()));
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitItem(T listBox, HydrationContext context, int itemIndex) throws HydrationException
	{
		context.pop(XMLStreamWriter.class);
		
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// option
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getListBoxCssClass(T listBox)
	{
		return new CssClassBuilder("listbox");
	}
}
