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
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.event.ActionEvent;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeHref;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeMnemonic;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeText;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeToolTip;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * Hydrator that dehydrates a {@code TabBox} component to an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @see TabBox
 * @param <T>
 *            the tab box type this visitor can visit
 */
public class HtmlTabBoxDehydrator<T extends TabBox> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T tabBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("div");
			writeClass(out, getTabBoxCssClass(tabBox));
			
			out.writeStartElement("ul");
			writeClass(out, getTabsCssClass(tabBox));
			
			// TODO: remove the need for this traversal somehow
			for (Tab tab : tabBox.getTabs())
			{
				dehydrateTab(tab, context);
			}
			
			// ul
			out.writeEndElement();
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
	public EndVisit endVisit(T tabBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// div
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// protected methods ------------------------------------------------------
	
	protected void dehydrateTab(Tab tab, HydrationContext context) throws HydrationException, XMLStreamException
	{
		// TODO: move to HtmlTabHydrator somehow
		// TODO: can we reuse AnchorHtmlButtonHydrator here?
		
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasListeners = (tab.getActionListenerCount() > 0);
		int mnenomic = tab.getMnemonic();
		
		out.writeStartElement("li");
		writeClass(out, getTabCssClass(tab));
		
		if (hasListeners)
		{
			out.writeStartElement("a");
			writeHref((DehydrationContext) context, Button.ACTION_EVENT, new ActionEvent(tab));
			writeMnemonic(out, mnenomic);
			writeToolTip(out, tab.getToolTip());
		}
		
		writeText(out, tab.getText(), mnenomic);
		
		if (hasListeners)
		{
			// a
			out.writeEndElement();
		}
		
		// li
		out.writeEndElement();
	}
	
	protected CssClassBuilder getTabBoxCssClass(T tabBox)
	{
		return new CssClassBuilder("tabbox");
	}
	
	protected CssClassBuilder getTabsCssClass(T tabBox)
	{
		return new CssClassBuilder("tabs");
	}
	
	protected CssClassBuilder getTabCssClass(Tab tab)
	{
		TabBox tabBox = (TabBox) tab.getParent();
		
		CssClassBuilder builder = new CssClassBuilder("tab");
		
		if (tabBox.getSelectedComponent() == tab)
		{
			builder.append("selected");
		}
		
		return builder;
	}
}
