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
import org.hobsoft.symmetry.ui.TextArea;
import org.hobsoft.symmetry.ui.TextBox;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeReadOnly;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

/**
 * Hydrator that dehydrates a {@code TextArea} component to an HTML {@code <textarea/>} tag.
 * 
 * @author Mark Hobson
 * @see TextArea
 * @param <T>
 *            the text area type this visitor can visit
 */
public class HtmlTextAreaDehydrator<T extends TextArea> extends HtmlTextBoxDehydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T textArea, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		int rows = textArea.getRows();
		
		try
		{
			out.writeStartElement("textarea");
			
			writeClass(out, getTextBoxCssClass(textArea));
			writeId(context, textArea);
			writeName(context, textArea);
			out.writeAttribute("cols", Integer.toString(textArea.getColumns()));
			out.writeAttribute("rows", Integer.toString(rows));
			writeEnabled(out, textArea);
			writeReadOnly(out, textArea.isReadOnly());
			
			String encodedValue = encodePropertyValue(context, textArea, TextBox.TEXT_PROPERTY, textArea.getText());
			out.writeCharacters(encodedValue);
			
			// textarea
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// HtmlTextBoxDehydrator methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CssClassBuilder getTextBoxCssClass(T textArea)
	{
		CssClassBuilder builder = new CssClassBuilder("textarea");
		
		if (textArea.hasStyle(Style.ERROR))
		{
			builder.append("textarea-error");
		}
		
		return builder;
	}
}
