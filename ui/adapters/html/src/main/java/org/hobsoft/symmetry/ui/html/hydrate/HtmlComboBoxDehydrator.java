/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.support.xml.stax.filter.TypeEventFilters;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.event.SelectionEvent;
import org.hobsoft.symmetry.ui.traversal.NullListBoxVisitor;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEvent;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.createFilteredWriter;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIf;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotNull;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

/**
 * Hydrator that dehydrates a {@code ComboBox} component to an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
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