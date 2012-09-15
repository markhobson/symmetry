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
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeFor;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeMnemonic;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeSelected;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeText;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeToolTip;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEqual;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

/**
 * Hydrator that dehydrates a {@code CheckBox} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @see CheckBox
 * @param <T>
 *            the check box type this visitor can visit
 */
public class HtmlCheckBoxDehydrator<T extends CheckBox> extends NullHierarchicalComponentHydrator<T>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_VALUE = "on";

	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T checkBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String toolTip = checkBox.getToolTip();

		try
		{
			out.writeEmptyElement("input");
			writeClass(out, getCheckBoxCssClass(checkBox));
			
			writeId(context, checkBox);
			writeName(context, checkBox);
			
			String encodedValue = encodePropertyValue(context, checkBox, Selectable.SELECTED_PROPERTY,
				checkBox.isSelected());
			writeAttributeIfNotEqual(out, "value", encodedValue, DEFAULT_VALUE);
			
			out.writeAttribute("type", "checkbox");
			writeMnemonic(out, checkBox.getMnemonic());
			writeToolTip(out, toolTip);
			writeEnabled(out, checkBox);
			// TODO: should this be handled by writePropertyValue? 
			writeSelected(out, checkBox);
	
			// TODO: reimplement EnableClosure using property bindings
//			if (checkBox.getActionListenerCount() > 0)
//			{
//				EnableClosure enableClosure = ComponentUtils.getClosure(checkBox.getActionListeners(),
//					EnableClosure.class);
//				
//				if (enableClosure != null)
//				{
//					Enableable enableable = enableClosure.getEnableable();
//					String enableableId = XMLUtils.getId(context, enableable);
//					
//					out.writeAttribute("onchange", "document.getElementById('" + enableableId
//						+ "').disabled = !this.checked");
//				}
//			}
			
			String text = checkBox.getText();
			
			if (text.length() > 0)
			{
				out.writeStartElement("label");
				writeClass(out, getCheckBoxCssClass(checkBox));
				
				writeFor(context, checkBox);
				writeToolTip(out, toolTip);
				writeText(out, text, checkBox.getMnemonic());
				
				// label
				out.writeEndElement();
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getCheckBoxCssClass(T checkBox)
	{
		CssClassBuilder builder = new CssClassBuilder("checkbox");
		
		if (!checkBox.isEnabled())
		{
			builder.append("checkbox-disabled");
		}
		
		return builder;
	}
}
