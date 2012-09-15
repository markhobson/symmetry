/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlListBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.support.xml.stax.filter.TypeEventFilters;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.SelectionMode;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.traversal.NullListBoxVisitor;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.createFilteredWriter;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttribute;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIf;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotNull;

/**
 * Hydrator that dehydrates a {@code ListBox} component to an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
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
