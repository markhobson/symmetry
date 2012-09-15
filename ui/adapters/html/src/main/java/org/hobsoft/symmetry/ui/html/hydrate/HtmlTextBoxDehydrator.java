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
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeReadOnly;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIf;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEmpty;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

/**
 * Hydrator that dehydrates a {@code TextBox} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @see TextBox
 * @param <T>
 *            the text box type this visitor can visit
 */
public class HtmlTextBoxDehydrator<T extends TextBox> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T textBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeEmptyElement("input");
			
			writeClass(out, getTextBoxCssClass(textBox));
			writeId(context, textBox);
			writeName(context, textBox);
			out.writeAttribute("type", "text");
			
			String encodedValue = encodePropertyValue(context, textBox, TextBox.TEXT_PROPERTY, textBox.getText());
			writeAttributeIfNotEmpty(out, "value", encodedValue);
			
			out.writeAttribute("size", Integer.toString(textBox.getColumns()));
			
			int maxLength = textBox.getMaxLength();
			writeAttributeIf(out, "maxlength", Integer.toString(maxLength), maxLength < Integer.MAX_VALUE);
			
			writeEnabled(out, textBox);
			writeReadOnly(out, textBox.isReadOnly());
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getTextBoxCssClass(T textBox)
	{
		CssClassBuilder builder = new CssClassBuilder("textbox");
		
		if (textBox.hasStyle(Style.ERROR))
		{
			builder.append("textbox-error");
		}
		
		return builder;
	}
}
