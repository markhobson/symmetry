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
import org.hobsoft.symmetry.ui.PasswordBox;
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeReadOnly;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIf;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

/**
 * Hydrator that dehydrates a {@code PasswordBox} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @see PasswordBox
 * @param <T>
 *            the password box type this visitor can visit
 */
public class HtmlPasswordBoxDehydrator<T extends PasswordBox> extends NullHierarchicalComponentHydrator<T>
{
	// TODO: extend HtmlTextBoxDehydrator
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T passwordBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeEmptyElement("input");
			
			writeClass(out, getPasswordBoxCssClass(passwordBox));
			writeId(context, passwordBox);
			writeName(context, passwordBox);
			out.writeAttribute("type", "password");
			
			// don't write @value for security reasons
			
			out.writeAttribute("size", Integer.toString(passwordBox.getColumns()));
			
			int maxLength = passwordBox.getMaxLength();
			writeAttributeIf(out, "maxlength", Integer.toString(maxLength), maxLength < Integer.MAX_VALUE);
			
			writeEnabled(out, passwordBox);
			writeReadOnly(out, passwordBox.isReadOnly());
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getPasswordBoxCssClass(T passwordBox)
	{
		CssClassBuilder builder = new CssClassBuilder("passwordbox");
		
		if (passwordBox.hasStyle(Style.ERROR))
		{
			builder.append("passwordbox-error");
		}
		
		return builder;
	}
}
