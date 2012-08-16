/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComboBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeEvent;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.createFilteredWriter;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIf;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotNull;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeId;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.common.xml.stax.filter.TypeEventFilters;
import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Style;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.event.SelectionEvent;
import uk.co.iizuka.kozo.ui.traversal.NullListBoxVisitor;

/**
 * Hydrator that dehydrates a {@code ComboBox} component to an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlComboBoxDehydrator.java 100086 2012-03-30 10:28:05Z mark@IIZUKA.CO.UK $
 * @see ComboBox
 * @param <T>
 *            the combo box type this visitor can visit
 */
public class HtmlComboBoxDehydrator<T extends ComboBox<?>>
	extends NullListBoxVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T comboBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("select");
			writeClass(out, getComboBoxCssClass(comboBox));
			writeId(context, comboBox);
			writeName(context, comboBox);
			
			if (comboBox.getSelectionListenerCount() > 0)
			{
				// TODO: how to supply actual selectedIndex to listener?
				writeEvent((DehydrationContext) context, "onchange", ComboBox.SELECTION_EVENT,
					new SelectionEvent(comboBox, 0));
			}
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
	public EndVisit endVisit(T comboBox, HydrationContext context) throws HydrationException
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
	public Visit visitItem(T comboBox, HydrationContext context, int itemIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		int selectedIndex = comboBox.getSelectedIndex();
		boolean selected = (selectedIndex == itemIndex);
		
		try
		{
			out.writeStartElement("option");
			
			// TODO: need to consider how to handle combo boxes with unselected index, it currently forces a
			// persisted state
	
			String encodedValue = encodePropertyValue(context, comboBox, ComboBox.SELECTED_INDEX_PROPERTY, itemIndex);
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
	public EndVisit endVisitItem(T comboBox, HydrationContext context, int itemIndex) throws HydrationException
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
	
	protected CssClassBuilder getComboBoxCssClass(T comboBox)
	{
		CssClassBuilder builder = new CssClassBuilder("combobox");
		
		if (comboBox.hasStyle(Style.ERROR))
		{
			builder.append("combobox-error");
		}
		
		return builder;
	}
}
